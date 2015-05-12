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
public abstract class Request extends Notification{	
	private String commentary;
	private String commentaryAdmin;
	private boolean approved;
	
	private User toSocialworker;
	private User fromSocialworker;
		
	
	/**
	 * Constructor with fields
	 * 
	 * @param notification
	 * @param createdCreated
	 * @param commentary
	 * @param commentaryAdmin
	 * @param approved
	 * @param toSocialworker
	 * @param fromSocialworker
	 */
	public Request(String notification, Date createdCreated, String commentary,
			String commentaryAdmin, boolean approved, User toSocialworker,
			User fromSocialworker) {
		super(notification, createdCreated);
		this.commentary = commentary;
		this.commentaryAdmin = commentaryAdmin;
		this.approved = approved;
		this.toSocialworker = toSocialworker;
		this.fromSocialworker = fromSocialworker;
	}


	/**
	 * Clean constructor, fields from the Notification
	 * @param notification
	 * @param createdCreated
	 */
	public Request(String notification, Date createdCreated) {
		super(notification, createdCreated);
	}


	/**
	 * @return the commentary
	 */
	public String getCommentary() {
		return commentary;
	}


	/**
	 * @param commentary the commentary to set
	 */
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}


	/**
	 * @return the commentaryAdmin
	 */
	public String getCommentaryAdmin() {
		return commentaryAdmin;
	}


	/**
	 * @param commentaryAdmin the commentaryAdmin to set
	 */
	public void setCommentaryAdmin(String commentaryAdmin) {
		this.commentaryAdmin = commentaryAdmin;
	}


	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}


	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	/**
	 * @return the toSocialworker
	 */
	public User getToSocialworker() {
		return toSocialworker;
	}


	/**
	 * @param toSocialworker the toSocialworker to set
	 */
	public void setToSocialworker(User toSocialworker) {
		this.toSocialworker = toSocialworker;
	}


	/**
	 * @return the fromSocialworker
	 */
	public User getFromSocialworker() {
		return fromSocialworker;
	}


	/**
	 * @param fromSocialworker the fromSocialworker to set
	 */
	public void setFromSocialworker(User fromSocialworker) {
		this.fromSocialworker = fromSocialworker;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Request = Request [Commentary = " + getCommentary()
				+ ", CommentaryAdmin = " + getCommentaryAdmin()
				+ ", isApproved = " + isApproved() + ", ToSocialworker = "
				+ getToSocialworker() + ", FromSocialworker = "
				+ getFromSocialworker() + "]";
	}
	
	
}
