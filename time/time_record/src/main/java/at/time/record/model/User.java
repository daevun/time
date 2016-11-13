package at.time.record.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Model for Record Project
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	private String oid;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "sozVers", length = 12)
	private String sozVers;

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

	public String getSozVers() {
		return sozVers;
	}

	public void setSozVers(String sozVers) {
		this.sozVers = sozVers;
	}

	@Override
	public String toString() {
		return String.format("%s %s SV-Nr.%s", getName(), getSurname(), getOid());
	}

	@Override
	public boolean equals(Object arg0) {
		return (arg0 != null && getOid() != null && arg0.getClass().isAssignableFrom(getClass())
				&& getClass().isAssignableFrom(arg0.getClass())) ? getOid().equals(((User) arg0).getOid())
						: (arg0 == this);
	}

	@Override
	public int hashCode() {
		return (getOid() != null) ? (getClass().getSimpleName().hashCode() + getOid().hashCode()) : super.hashCode();
	}

}
