package at.time.record.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import at.time.record.rabbit.Publishable;
import at.time.record.rabbit.RabbitConstants;

/**
 * Record Model for Record Project
 */
@Entity
@Table(name = "record")
public class Record implements Publishable {

	@Column(name = "user")
	private User user;

	@Column(name = "begin")
	private Date begin;

	@Column(name = "end")
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
	public String contentType() {
		return RabbitConstants.CT_RECORD;
	}

}
