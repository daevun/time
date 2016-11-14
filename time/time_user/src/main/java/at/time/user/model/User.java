package at.time.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import at.time.user.rabbit.Publishable;
import at.time.user.rabbit.RabbitConstants;

/**
 * User Model for User Project
 */
@Entity
@Table(name = "user")
public class User implements Publishable {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long oid;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "sozVers", length = 12)
	private String sozVers;

	public Long getOid() {
		return oid;
	}

	public void setOid(final Long oid) {
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

	public String getSozVers() {
		return sozVers;
	}

	public void setSozVers(String sozVers) {
		this.sozVers = sozVers;
	}

	@Override
	public String toString() {
		return String.format("%s %s SV-Nr.: %s", getName(), getSurname(), getSozVers());
	}

	@Override
	public String contentType() {
		return RabbitConstants.CT_USER;
	}

}
