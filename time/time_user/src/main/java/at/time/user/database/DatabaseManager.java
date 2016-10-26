package at.time.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private static final String USER = "user";
	private static final String PASSWORD = "timeuser";
	private static final String URL = "jdbc:mysql://localhost:3306/user";

	public void initDatabase() {
		// Context context = null;
		// DataSource datasource = null;
		final Connection connect = null;
		// Statement statement = null;

		try {
			final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
