package at.time.report.model;

import at.time.base.rabbit.Publishable;
import at.time.base.rabbit.RabbitConstants;

/**
 * User Model for Report Project
 */
public class User implements Publishable {

	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(final String oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return String.format("User: %s", getOid());
	}

	@Override
	public String getContentType() {
		return RabbitConstants.CT_USER;
	}

}
