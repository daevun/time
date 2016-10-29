package at.time.record.model;

import java.util.Date;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * Record Model for Record Project
 */
public class Record implements Publishable {

	private User user;

	private Date begin;

	private Date end;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String getContentType() {
		return RabbitConstants.CT_RECORD;
	}

}
