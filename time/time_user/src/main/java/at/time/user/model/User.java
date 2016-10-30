package at.time.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * User Model for User Project
 */
@Entity
@Table(name = "user")
public class User implements Publishable {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	private String oid;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	public String getOid() {
		return oid;
	}

	public void setOid(final String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return String.format("User: %s %s %s", getOid(), getName(), getSurname());
	}

	@Override
	public String contentType() {
		return RabbitConstants.CT_USER;
	}

}
