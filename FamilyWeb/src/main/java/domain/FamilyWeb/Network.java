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
public class Network {
	private Date dateCreated;
	private String commentary;
	private ArrayList<Contact> contacts;
	private Survey theSurvey;
	
	/**
	 * Constructor with fields
	 * 
	 * @param dateCreated
	 * @param commentary
	 */
	public Network(Date dateCreated, String commentary) {
		this.dateCreated = dateCreated;
		this.commentary = commentary;
		this.contacts = new ArrayList<Contact>();
	}


	/**
	 * Clean constructor
	 */
	public Network() {
		this.contacts = new ArrayList<Contact>();
	}


	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}


	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	 * @return the theSurvey
	 */
	public Survey getTheSurvey() {
		return theSurvey;
	}


	/**
	 * @param theSurvey the theSurvey to set
	 */
	public void setTheSurvey(Survey theSurvey) {
		this.theSurvey = theSurvey;
	}


	/**
	 * @return the contacts
	 */
	public ArrayList<Contact> getContacts() {
		return contacts;
	}


	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Network [DateCreated = " + getDateCreated()
				+ ", Commentary = " + getCommentary() + "]";
	}
}
