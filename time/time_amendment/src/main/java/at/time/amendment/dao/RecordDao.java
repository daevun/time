package at.time.amendment.dao;

import org.hibernate.Session;

import at.time.amendment.hibernate.HibernateUtil;
import at.time.amendment.model.Record;

public class RecordDao {

	public void saveRecord(final Record record) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(record);
			session.getTransaction().commit();
		}
	}

}
