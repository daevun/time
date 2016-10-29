package at.time.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import at.time.record.database.DatabaseManager;
import at.time.record.model.User;

public class UserDao {

	private final DatabaseManager dbManager = new DatabaseManager();

	public User getByOid(final String oid) {
		ResultSet result = null;
		User user = null;
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM record.user WHERE OID = ?")) {
			ps.setString(1, oid);
			result = ps.executeQuery();
			if (result.first()) {
				user = new User();
				user.setOid(result.getString("oid"));
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
						.prepareStatement("INSERT INTO record.user (oid, name, surname) values(?, ?, ?)")) {
			ps.setString(1, user.getOid());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

}
