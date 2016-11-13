package at.time.record.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import at.time.record.dao.UserDao;
import at.time.record.model.User;

public class RabbitManager {

	private static Logger logger = LogManager.getLogger();

	private Connection connection;
	private Channel channel;

	public void publish(final Publishable publishable) {
		final Channel channel = getChannel();
		final AMQP.BasicProperties.Builder basicProperties = new AMQP.BasicProperties.Builder();
		basicProperties.contentType("user");
		try {
			channel.basicPublish(RabbitConstants.TIME_EXCHANGE, "", basicProperties.build(),
					publishable.toGson().getBytes());
		} catch (final IOException e) {
			logger.error("Fehler beim Verschicken der Nachricht: ", publishable.toGson());
		}
	}

	public void startConsumer() {
		final Channel channel = getChannel();
		final boolean autoAck = false;
		final UserDao userDao = new UserDao();
		try {
			final String record = channel.queueDeclare(RabbitConstants.RECORD_QUEUE, true, false, false, null)
					.getQueue();
			channel.queueBind(record, RabbitConstants.TIME_EXCHANGE, "");
			channel.basicConsume(RabbitConstants.RECORD_QUEUE, autoAck, "myConsumerTag", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					final String contentType = properties.getContentType();
					final String message = new String(body, "UTF-8");
					switch (contentType) {
					case RabbitConstants.CT_USER:
						logger.info(" [x] Received '" + message + "'" + " Saving new User..");
						userDao.saveUser(new Gson().fromJson(message, User.class));
						break;
					case RabbitConstants.CT_RECORD:
						break;
					default:
						break;
					}

					final long deliveryTag = envelope.getDeliveryTag();
					channel.basicAck(deliveryTag, false);
				}
			});
		} catch (final IOException e) {
			logger.error("Fehler beim Initialisieren vom RabbitMQ Consumer", e.getMessage());
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