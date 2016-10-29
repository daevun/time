package at.time.report.model;

import org.joda.time.DateTime;

/**
 * Record Model for Report Project
 */
public class Record {

	private User user;

	private DateTime begin;

	private DateTime end;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DateTime getBegin() {
		return begin;
	}

	public void setBegin(DateTime begin) {
		this.begin = begin;
	}

	public DateTime getEnd() {
		return end;
	}

	public void setEnd(DateTime end) {
		this.end = end;
	}

}
