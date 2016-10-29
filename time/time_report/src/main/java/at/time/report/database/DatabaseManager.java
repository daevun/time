package at.time.report.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseManager {

	private static Logger logger = LogManager.getLogger();

	private static final String USER = "report";
	private static final String PASSWORD = "timereport";
	private static final String URL = "jdbc:mysql://localhost:3306/report";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (final SQLException e) {
			logger.error("Fehler bei der Verbindung zur Datenbank", e.getMessage());
		}
		return null;
	}

}
