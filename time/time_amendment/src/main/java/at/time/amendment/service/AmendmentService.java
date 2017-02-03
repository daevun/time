package at.time.amendment.service;

import javax.inject.Inject;

import at.time.amendment.dao.RecordDao;
import at.time.amendment.model.Record;
import at.time.amendment.rabbit.RabbitManager;

public class AmendmentService {

	@Inject
	private RecordDao dao;

	@Inject
	private RabbitManager rabbit;

	public Record createRecord() {
		return new Record();
	}

	public void saveRecord(final Record record) {
		dao.saveRecord(record);
		rabbit.publish(record);
	}

}
