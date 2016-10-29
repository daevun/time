package at.time.amendment.model;

import org.joda.time.DateTime;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * Record Model for Amendment Project
 */
public class Record implements Publishable {

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

	@Override
	public String getContentType() {
		return RabbitConstants.CT_RECORD;
	}

}
