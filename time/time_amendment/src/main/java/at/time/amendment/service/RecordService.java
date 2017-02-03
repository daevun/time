package at.time.amendment.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.amendment.dao.RecordDao;
import at.time.amendment.model.Record;
import at.time.amendment.model.User;

@Named
public class RecordService {

	@Inject
	private RecordDao dao;

	public List<Record> getRecords(final User user, final Date begin, final Date end) {
		return dao.getBy(user, begin, end);
	}

	public List<Record> getAll() {
		return dao.getAll();
	}

}
