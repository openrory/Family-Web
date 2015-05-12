/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public class Administrator extends User {
	protected ArrayList<User> users;
	
	/**
	 * Clean constructor
	 */
	public Administrator() {
		super();
		this.users = new ArrayList<User>();
	}

	/**
	 * Constructor with all parameters
	 * 
	 * @param username
	 * @param password
	 * @param forename
	 * @param surname
	 * @param dateOfBirth
	 * @param postcode
	 * @param street
	 * @param houseNumber
	 * @param city
	 * @param nationality
	 * @param telephoneNumber
	 * @param mobilePhoneNumber
	 * @param email
	 * @param isActive
	 * @param employeeNumber
	 */
	public Administrator(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber, ArrayList<User> users) {
		super(username, password, forename, surname, dateOfBirth, postcode, street,
				houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber,
				email, isActive, employeeNumber);
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Administrator [userInfo = " + super.toString() + ", users = "
				+ this.users + "]";
	}

	public void setUsers(ArrayList<User> allSocialworkers) {
		this.users = allSocialworkers;
	}
}
