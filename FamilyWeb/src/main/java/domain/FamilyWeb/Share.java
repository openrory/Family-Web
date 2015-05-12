/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Share extends Request {
	private ArrayList<Network> sharedNetworks;
	
	/**
	 * Clean constructor, with fields from Notifiaction
	 * 
	 * @param notification
	 * @param createdCreated
	 */
	public Share(String notification, Date createdCreated) {
		super(notification, createdCreated);
		this.sharedNetworks = new ArrayList<Network>();
	}
	
	/**
	 * Clean constructor, with fields from Request
	 * 
	 * @param notification
	 * @param createdCreated
	 * @param commentary
	 * @param commentaryAdmin
	 * @param approved
	 * @param toSocialworker
	 * @param fromSocialworker
	 */
	
	public Share(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
		this.sharedNetworks = new ArrayList<Network>();
	}

	
	/**
	 * @param notification
	 * @param createdCreated
	 * @param commentary
	 * @param commentaryAdmin
	 * @param approved
	 * @param toSocialworker
	 * @param fromSocialworker
	 * @param sharedNetworks
	 */
	public Share(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker, ArrayList<Network> sharedNetworks) {
		super(notification, createdCreated, commentary, commentaryAdmin,
				approved, toSocialworker, fromSocialworker);
		this.sharedNetworks = sharedNetworks;
	}

	/**
	 * @return the sharedNetworks
	 */
	public ArrayList<Network> getSharedNetworks() {
		return sharedNetworks;
	}

	/**
	 * @param sharedNetworks the sharedNetworks to set
	 */
	public void setSharedNetworks(ArrayList<Network> sharedNetworks) {
		this.sharedNetworks = sharedNetworks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+" Share = Share [SharedNetworks = " + getSharedNetworks() + "]";
	}

	

}
