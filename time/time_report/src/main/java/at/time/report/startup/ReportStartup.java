package at.time.report.startup;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import at.time.report.dao.UserDao;
import at.time.report.model.User;
import at.time.report.rabbit.RabbitConstants;
import at.time.report.rabbit.RabbitManager;

@ApplicationScoped
public class ReportStartup {

	private static Logger logger = LogManager.getLogger();

	@PostConstruct
	public void postConstruct() {
		startRabbitConsumer();
	}

	private void startRabbitConsumer() {
		final Channel channel = new RabbitManager().getChannel();
		final boolean autoAck = false;
		final UserDao userDao = new UserDao();
		try {
			channel.basicConsume(RabbitConstants.REPORT_QUEUE, autoAck, "myConsumerTag", new DefaultConsumer(channel) {
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

}
