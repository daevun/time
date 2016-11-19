package at.time.report.startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import at.time.report.rabbit.RabbitManager;

@ApplicationScoped
@Named
public class ReportStartup {

	@Inject
	private RabbitManager rabbit;

	@PostConstruct
	private void startConsumer(@Observes @Initialized(ApplicationScoped.class) Object init) {
		rabbit.startConsumer();
	}

}
