package at.time.record.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.GsonBuilder;

import at.time.record.gson.UserSerializer;
import at.time.record.rabbit.Publishable;
import at.time.record.rabbit.RabbitConstants;

/**
 * Record Model for Record Project
 */
@Entity
@Table(name = "record")
public class Record implements Publishable {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long oid;

	@ManyToOne(cascade = { CascadeType.ALL })
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

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
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
