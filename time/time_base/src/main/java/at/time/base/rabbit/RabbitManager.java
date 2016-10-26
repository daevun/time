package at.time.base.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitManager {

	private static RabbitManager instance = null;
	private static Logger logger = LogManager.getLogger();

	private RabbitManager() {
	}

	private static Connection connection;
	private static Channel channel;

	public static RabbitManager getInstance() {
		if (instance == null) {
			instance = new RabbitManager();
		}
		return instance;
	}

	public void publishMessage(final String message) {
		final Channel channel = getChannel();
		try {
			channel.basicPublish(RabbitConstants.TIME_EXCHANGE, "", null, message.getBytes());
		} catch (final IOException e) {
			logger.error("Fehler beim Verschicken der Nachricht: ", message);
		}
	}

	public Channel getChannel() {
		if (channel == null) {
			final Connection connection = getConnection();
			try {
				channel = connection.createChannel();
				channel.exchangeDeclare(RabbitConstants.TIME_EXCHANGE, RabbitConstants.DIRECT, true);
				final String report = channel.queueDeclare(RabbitConstants.REPORT_QUEUE, true, false, false, null)
						.getQueue();
				final String record = channel.queueDeclare(RabbitConstants.RECORD_QUEUE, true, false, false, null)
						.getQueue();
				final String amendment = channel.queueDeclare(RabbitConstants.AMENDMENT_QUEUE, true, false, false, null)
						.getQueue();
				channel.queueBind(report, RabbitConstants.TIME_EXCHANGE, "");
				channel.queueBind(record, RabbitConstants.TIME_EXCHANGE, "");
				channel.queueBind(amendment, RabbitConstants.TIME_EXCHANGE, "");
			} catch (final IOException e) {
				logger.error("Fehler beim Erstellen des Channels für RabbitMQ", e.getMessage());
			}
		}
		return channel;
	}

	private Connection getConnection() {
		if (connection == null) {
			final ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(RabbitConstants.HOST);
			factory.setPort(RabbitConstants.PORT);
			try {
				connection = factory.newConnection();
			} catch (IOException | TimeoutException e) {
				logger.error("Fehler beim Erstellen der Connection für RabbitMQ", e.getMessage());
			}
		}
		return connection;
	}

}
