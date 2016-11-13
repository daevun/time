package at.time.user.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitManager {

	private static Logger logger = LogManager.getLogger();

	private Connection connection;
	private Channel channel;

	public void publish(final Publishable publishable) {
		final Channel channel = getChannel();
		final AMQP.BasicProperties.Builder basicProperties = new AMQP.BasicProperties.Builder();
		basicProperties.contentType(publishable.contentType());
		try {
			channel.basicPublish(RabbitConstants.TIME_EXCHANGE, "", basicProperties.build(),
					publishable.toGson().getBytes());
		} catch (final IOException e) {
			logger.error("Fehler beim Verschicken der Nachricht: ", publishable.toGson());
		}
	}

	private Channel getChannel() {
		if (channel == null) {
			final Connection connection = getConnection();
			try {
				channel = connection.createChannel();
				channel.exchangeDeclare(RabbitConstants.TIME_EXCHANGE, RabbitConstants.DIRECT, true);
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
			} catch (final IOException | TimeoutException e) {
				logger.error("Fehler beim Erstellen der Connection für RabbitMQ", e.getMessage());
			}
		}
		return connection;
	}
}