package at.time.amendment.startup;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import at.time.base.rabbit.RabbitConstants;
import at.time.base.rabbit.RabbitManager;

@ManagedBean(eager = true)
@ApplicationScoped
public class AmendmantStartup {

	private static Logger logger = LogManager.getLogger();

	public AmendmantStartup() {
		startRabbitConsumer();
	}

	private void startRabbitConsumer() {
		final Channel channel = new RabbitManager().getChannel();
		final boolean autoAck = false;
		try {
			channel.basicConsume(RabbitConstants.REPORT_QUEUE, autoAck, "myConsumerTag", new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					// final String routingKey = envelope.getRoutingKey();
					// final String contentType = properties.getContentType();
					final long deliveryTag = envelope.getDeliveryTag();
					// (process the message components here ...)
					final String message = new String(body, "UTF-8");
					logger.info(" [x] Received '" + message + "'");
					channel.basicAck(deliveryTag, false);
				}
			});
		} catch (final IOException e) {
			logger.error("Fehler beim Initialisieren vom RabbitMQ Consumer", e.getMessage());
		}
	}

}
