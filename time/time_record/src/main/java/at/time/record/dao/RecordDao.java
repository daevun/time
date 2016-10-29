package at.time.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import at.time.record.database.DatabaseManager;
import at.time.record.model.Record;

public class RecordDao {

	private final DatabaseManager dbManager = new DatabaseManager();

	public void saveRecord(final Record record) {
		try (Connection connection = dbManager.getConnection();
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO record.record (user, begin, end) values(?, ?, ?)")) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ps.setString(1, record.getUser().getOid());
			ps.setString(2, sdf.format(record.getBegin()));
			ps.setString(3, sdf.format(record.getEnd()));
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
