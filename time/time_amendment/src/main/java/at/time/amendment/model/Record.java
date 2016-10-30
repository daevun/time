package at.time.amendment.model;

import java.util.Date;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * Record Model for Amendment Project
 */
public class Record implements Publishable {

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

	@Override
	public String contentType() {
		return RabbitConstants.CT_RECORD;
	}

}
