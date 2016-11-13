package at.time.user.startup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class UserStartup {

	public void postConstruct(@Observes @Initialized(ApplicationScoped.class) Object init) {
		// noop
	}

}
