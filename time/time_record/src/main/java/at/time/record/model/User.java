package at.time.record.model;

/**
 * User Model for Record Project
 */
public class User {

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
		return String.format("%s %s SV-Nr.%s", getName(), getSurname(), getOid());
	}

}
