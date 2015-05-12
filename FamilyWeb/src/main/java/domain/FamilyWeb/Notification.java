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
public abstract class Notification {
	private String notification;
	private Date createdCreated;
	
	/**
	 * Constructor with fields
	 * 
	 * @param notification
	 * @param createdCreated
	 */
	public Notification(String notification, Date createdCreated) {
		super();
		this.notification = notification;
		this.createdCreated = createdCreated;
	}

	/**
	 * @return the notification
	 */
	public String getNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(String notification) {
		this.notification = notification;
	}

	/**
	 * @return the createdCreated
	 */
	public Date getCreatedCreated() {
		return createdCreated;
	}

	/**
	 * @param createdCreated the createdCreated to set
	 */
	public void setCreatedCreated(Date createdCreated) {
		this.createdCreated = createdCreated;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [Notification = " + getNotification()
				+ ", CreatedCreated = " + getCreatedCreated() + "]";
	}
}
