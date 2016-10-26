package at.time.user.startup;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import at.time.user.database.DatabaseManager;

@ManagedBean(eager = true)
@ApplicationScoped
public class UserStartup {

	public UserStartup() {
		final DatabaseManager dbManager = new DatabaseManager();
		dbManager.initDatabase();
	}

}
