package at.time.record.services;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.record.dao.RecordDao;
import at.time.record.model.Record;
import at.time.record.rabbit.RabbitManager;

@Named
public class RecordService {

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
