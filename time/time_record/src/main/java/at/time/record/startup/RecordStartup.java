package at.time.record.startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import at.time.record.rabbit.RabbitManager;

@ApplicationScoped
@Named
public class RecordStartup {

	@Inject
	private RabbitManager rabbit;

	@PostConstruct
	private void startConsumer(@Observes @Initialized(ApplicationScoped.class) Object init) {
		rabbit.startConsumer();
	}

}
