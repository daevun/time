package at.time.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.Lists;

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

	public List<User> getAll() {
		ResultSet result = null;
		final List<User> users = Lists.newArrayList();
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM record.user")) {
			result = ps.executeQuery();
			while (result.next()) {
				final User user = new User();
				user.setOid(result.getString("oid"));
				user.setName(result.getString("name"));
				user.setSurname(result.getString("surname"));
				users.add(user);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return users;
	}

	public void saveUser(final User user) {
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO record.user (oid, name, surname) values(?, ?, ?)")) {
			ps.setString(1, user.getOid());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
