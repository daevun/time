package at.time.report.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.report.dao.RecordDao;
import at.time.report.model.Record;
import at.time.report.model.User;

@Named
public class RecordService {

	@Inject
	private RecordDao dao;

	public List<Record> getRecords(final User user, final Date begin, final Date end) {
		return dao.getBy(user, begin, end);
	}

}
