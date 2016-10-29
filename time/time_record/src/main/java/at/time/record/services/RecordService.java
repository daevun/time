package at.time.record.services;

import at.time.record.dao.RecordDao;
import at.time.record.model.Record;

public class RecordService {

	private RecordDao dao;

	public RecordService() {
		setDao(new RecordDao());
	}

	public Record createRecord() {
		return new Record();
	}

	public void saveRecord(final Record record) {
		dao.saveRecord(record);
	}

	public void setDao(final RecordDao dao) {
		this.dao = dao;
	}

}
