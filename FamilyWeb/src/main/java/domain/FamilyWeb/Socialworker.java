/**
 * 
 */
package domain.FamilyWeb;

import java.sql.Date;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public class Socialworker extends User {
	
	/**
	 * Clean constructor
	 */
	public Socialworker() {
		super();
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
	public Socialworker(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber) {
		super(username, password, forename, surname, dateOfBirth, postcode, street,
				houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber,
				email, isActive, employeeNumber);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Socialworker [userInfo = " + super.toString() + "]";
	}
}
