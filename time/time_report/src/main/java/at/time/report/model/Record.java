package at.time.report.model;

import java.util.Date;

/**
 * Record Model for Report Project
 */
public class Record {

	private User user;

	private Date begin;

	private Date end;

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
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

}
