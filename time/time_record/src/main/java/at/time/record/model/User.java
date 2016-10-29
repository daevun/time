package at.time.record.model;

/**
 * User Model for Record Project
 */
public class User {

	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(final String oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return String.format("User: %", getOid());
	}

}
