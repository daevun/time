package at.time.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.time.report.database.DatabaseManager;
import at.time.report.model.User;

public class UserDao {

	private final DatabaseManager dbManager = new DatabaseManager();

	public User getByOid(final String oid) {
		ResultSet result = null;
		User user = null;
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM report.user WHERE OID = ?")) {
			ps.setString(1, oid);
			result = ps.executeQuery();
			if (result.first()) {
				user = new User();
				user.setOid(result.getString("oid"));
				user.setName(result.getString("name"));
				user.setSurname(result.getString("surname"));
			}
			if (result.next()) {
				throw new RuntimeException("More than one Object found: " + oid);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return user;
	}

	public void saveUser(final User user) {
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO report.user (oid, name, surname) values(?, ?, ?)")) {
			ps.setString(1, user.getOid());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
