package at.time.report.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Lists;

import at.time.report.model.Record;
import at.time.report.model.User;
import at.time.report.service.RecordService;
import at.time.report.service.UserService;

@RequestScoped
@Named
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 4415574393568154183L;

	@Inject
	private UserService userService;

	@Inject
	private RecordService recordService;

	private Date begin;
	private Date end;
	private User user;
	private List<Record> records;

	public List<User> getAllUsers() {
		return userService.getAllUser();
	}

	public void populateRecords() {
		setRecords(getUser() == null ? Lists.newArrayList() : recordService.getRecords(getUser(), begin, end));
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(final Date begin) {
		this.begin = begin;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(final List<Record> records) {
		this.records = records;
	}

}
