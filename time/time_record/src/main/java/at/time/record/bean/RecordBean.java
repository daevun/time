package at.time.record.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import at.time.record.model.Record;
import at.time.record.model.User;
import at.time.record.services.RecordService;
import at.time.record.services.UserService;

@RequestScoped
@Named
public class RecordBean implements Serializable {

	private static final long serialVersionUID = -5016902876834657330L;

	@Inject
	private UserService userService;

	@Inject
	private RecordService recordService;

	private Date begin;
	private Date end;
	private User user;

	public List<User> getAllUsers() {
		return userService.getAllUser();
	}

	public void addRecord() {
		final Record record = recordService.createRecord();
		record.setUser(getUser());
		record.setBegin(getBegin());
		record.setEnd(getEnd());
		recordService.saveRecord(record);
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(final Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
