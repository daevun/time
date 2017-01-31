package at.time.report.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Record Model for Report Project
 */
@Entity
@Table(name = "record")
public class Record {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	private Long oid;

	@ManyToOne(cascade = { CascadeType.ALL })
	private User user;

	@Column(name = "begin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date begin;

	@Column(name = "end")
	@Temporal(TemporalType.TIMESTAMP)
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

	public Long getOid() {
		return oid;
	}

	public void setOid(final Long oid) {
		this.oid = oid;
	}

}
