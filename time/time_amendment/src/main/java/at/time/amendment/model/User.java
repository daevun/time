package at.time.amendment.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User Model for Amendment Project
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "oid", unique = true, nullable = false)
	private Long oid;

	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "surname", length = 45)
	private String surname;

	@Column(name = "sozVers", length = 12)
	private String sozVers;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Collection<Record> records;

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

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getSozVers() {
		return sozVers;
	}

	public void setSozVers(final String sozVers) {
		this.sozVers = sozVers;
	}

	public Collection<Record> getRecords() {
		return records;
	}

	public void setRecords(final Collection<Record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return String.format("%s %s SV-Nr.: %s", getName(), getSurname(), getSozVers());
	}

	@Override
	public boolean equals(final Object arg0) {
		return (arg0 != null && getOid() != null && arg0.getClass().isAssignableFrom(getClass())
				&& getClass().isAssignableFrom(arg0.getClass())) ? getOid().equals(((User) arg0).getOid())
						: (arg0 == this);
	}

	@Override
	public int hashCode() {
		return (getOid() != null) ? (getClass().getSimpleName().hashCode() + getOid().hashCode()) : super.hashCode();
	}

}