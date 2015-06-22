/**
 * 
 */
package domain.FamilyWeb;

import java.util.ArrayList;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-21
 */
public class Contact {
	private String fullname;
	private String commentary;	
	private String role;
	private int age;
	private ArrayList<Category> categories;
	private ArrayList<Result> myResults;
	private int contact_id;
	
	/**
	 * Constructor with fields
	 * 
	 * @param fullname
	 * @param commentary
	 */
	public Contact(String fullname, String commentary, String role, int age, String group,int group_id) {			
		this.fullname = fullname;
		this.commentary = commentary;
		this.role = role;
		this.age = age;
		this.categories = new ArrayList<Category>();
		this.getCategories().add(new Category(group,group_id));
		this.myResults = new ArrayList<Result>();
	}

	/**
	 * clean constructor
	 */
	public Contact() {
		this.categories = new ArrayList<Category>();
		this.myResults = new ArrayList<Result>();
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	 * @return the categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the myResults
	 */
	public ArrayList<Result> getMyResults() {
		return myResults;
	}

	/**
	 * @param myResults the myResults to set
	 */
	public void setMyResults(ArrayList<Result> myResults) {
		this.myResults = myResults;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [Fullname = " + getFullname() + ", Commentary = "
				+ getCommentary() +", category ="+getCategories() + ", results = "+getMyResults()+"]";
	}
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return the contact_id
	 */
	public int getContact_id() {
		return contact_id;
	}
	
	/**
	* @param contact_id the contact_id to set
	*/
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
}
