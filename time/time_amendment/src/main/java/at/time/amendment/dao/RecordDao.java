package at.time.amendment.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.google.common.collect.Lists;

import at.time.amendment.hibernate.HibernateUtil;
import at.time.amendment.model.Record;
import at.time.amendment.model.User;

public class RecordDao {

	public void saveRecord(final Record record) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(record);
			session.getTransaction().commit();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Record> getBy(final User user, final Date begin, final Date end) {
		List<Record> records = Lists.newArrayList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			final Criteria criteria = session.createCriteria(Record.class).add(Restrictions.eq("user", user))
					.add(Restrictions.ge("begin", begin)).add(Restrictions.le("end", end));

			records = criteria.list();
		}
		return records;
	}

	@SuppressWarnings("unchecked")
	public List<Record> getAll() {
		List<Record> records = Lists.newArrayList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			records = session.createCriteria(Record.class).list();
		}
		return records;
	}

}
