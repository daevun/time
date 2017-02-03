package at.time.amendment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.GsonBuilder;

import at.time.amendment.gson.UserSerializer;
import at.time.amendment.rabbit.Publishable;
import at.time.amendment.rabbit.RabbitConstants;

/**
 * Record Model for Amendment Project
 */
@Entity
@Table(name = "record")
public class Record implements Publishable {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	private Long oid;

	@ManyToOne
	private User user;

	@Column(name = "begin", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date begin;

	@Column(name = "end", columnDefinition = "DATETIME")
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

	@Override
	public String toString() {
		return getUser().toString() + "from: " + getBegin().toString() + "to: " + getEnd().toString();
	}

	@Override
	public String toGson() {
		return new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).setPrettyPrinting().create()
				.toJson(this);
	}

	@Override
	public String contentType() {
		return RabbitConstants.CT_RECORD;
	}

}
