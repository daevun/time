package at.time.report.dao;

import org.hibernate.Session;

import at.time.report.hibernate.HibernateUtil;
import at.time.report.model.Record;

public class RecordDao {

	public void saveRecord(final Record record) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(record);
			session.getTransaction().commit();
		}
	}

}
