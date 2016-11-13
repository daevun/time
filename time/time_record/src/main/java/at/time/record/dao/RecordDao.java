package at.time.record.dao;

import org.hibernate.Session;

import at.time.record.hibernate.HibernateUtil;
import at.time.record.model.Record;

public class RecordDao {

	public void saveRecord(final Record record) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(record);
			session.getTransaction().commit();
		}
	}

}
