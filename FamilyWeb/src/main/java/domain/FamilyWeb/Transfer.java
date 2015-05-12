/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Transfer extends Request {
	private Client theClient;
	/**
	 * Clean construct, with fields from Request
	 * @param notification
	 * @param createdCreated
	 * @param commentary
	 * @param commentaryAdmin
	 * @param approved
	 * @param toSocialworker
	 * @param fromSocialworker
	 */
	public Transfer(String notification, Date createdCreated,
			String commentary, String commentaryAdmin, boolean approved,
			User toSocialworker, User fromSocialworker) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
	}

	/**
	 * Clean constructor, with fields from Notification
	 * @param notification
	 * @param createdCreated
	 */
	public Transfer(String notification, Date createdCreated) {
		super(notification, createdCreated);
	}

	/**
	 * @return the theClient
	 */
	public Client getTheClient() {
		return theClient;
	}

	/**
	 * @param theClient the theClient to set
	 */
	public void setTheClient(Client theClient) {
		this.theClient = theClient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Transfer = Transfer [TheClient = " + getTheClient() + "]";
	}

}
