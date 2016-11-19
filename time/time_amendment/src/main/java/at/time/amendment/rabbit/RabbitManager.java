package at.time.amendment.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import at.time.amendment.dao.RecordDao;
import at.time.amendment.dao.UserDao;
import at.time.amendment.gson.UserDeserializer;
import at.time.amendment.model.Record;
import at.time.amendment.model.User;

public class RabbitManager {

	private static Logger logger = LogManager.getLogger();

	@Inject
	private UserDao userDao;

	@Inject
	private RecordDao recordDao;

	@Inject
	private UserDeserializer userDeserializer;

	private Connection connection;
	private Channel channel;

	public void startConsumer() {
		final Channel channel = getChannel();
		final boolean autoAck = false;
		try {
			final String record = channel.queueDeclare(RabbitConstants.AMENDMENT_QUEUE, true, false, false, null)
					.getQueue();
			channel.queueBind(record, RabbitConstants.TIME_EXCHANGE, "");
			channel.basicConsume(RabbitConstants.AMENDMENT_QUEUE, autoAck, "myConsumerTag", new DefaultConsumer(channel) {
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
						logger.info(" [x] Received '" + message + "'" + " Saving new Record..");
						recordDao.saveRecord(new GsonBuilder().registerTypeAdapter(User.class, userDeserializer)
								.setPrettyPrinting().create().fromJson(message, Record.class));
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