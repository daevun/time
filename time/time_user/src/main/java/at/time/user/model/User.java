package at.time.user.model;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * User Model for User Project
 */
public class User implements Publishable {

	private String oid;

	private String name;

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
	public String getContentType() {
		return RabbitConstants.CT_USER;
	}

}
