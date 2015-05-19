/**
 * 
 */
package domain.FamilyWeb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.management.Notification;

import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public abstract class User {

	// Regular Expression Pattern
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	private static final String LETTER_PATTERN = "([a-zA-Z]*(\\s)*)*";
	private static final String NL_POSTCODE_PATTERN = "^[1-9][0-9]{3}[\\s]?[A-Za-z]{2}$";
	private static final String NL_PHONENUMBER = "^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$";
	private static final String NL_MOBILEPHONENUMBER = "^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$";
	private static final String LETTER_NUMBER_WHITESPACE = "([ ]*+[0-9A-Za-z]++[ ]*+)+";

	private String username;
	private String password;
	private String forename;
	private String surname;
	private Date dateOfBirth;
	private String postcode;
	private String street;
	private String houseNumber;
	private String city;
	private String nationality;
	private String telephoneNumber;
	private String mobilePhoneNumber;
	private String email;
	private boolean isActive;
	private String employeeNumber;
	private int user_id;

	private ArrayList<Notification> notifications;
	private ArrayList<Client> myClients;
	private DatabaseInterface dbController;

	/**
	 * Constructor with fields
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
	protected User(String username, String password, String forename,
			String surname, Date dateOfBirth, String postcode, String street,
			String houseNumber, String city, String nationality,
			String telephoneNumber, String mobilePhoneNumber, String email,
			boolean isActive, String employeeNumber) {
		this.username = username;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.postcode = postcode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.nationality = nationality;
		this.telephoneNumber = telephoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.email = email;
		this.isActive = isActive;
		this.employeeNumber = employeeNumber;
		this.notifications = new ArrayList<Notification>();
		this.myClients = new ArrayList<Client>();
	}

	public DatabaseInterface getDbController() {
		return dbController;
	}

	public void setDbController(DatabaseInterface dbController) {
		this.dbController = dbController;
	}

	/**
	 * Clean constructor
	 */
	protected User() {
		this.notifications = new ArrayList<Notification>();
		this.myClients = new ArrayList<Client>();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 * @return
	 */
	public boolean setUsername(String input) {

		String username = input.trim();
		if (username.matches(USERNAME_PATTERN)
				&& this.dbController.getUser(username) == null) {
			this.username = username;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * @param forename
	 *            the forename to set
	 * @return
	 */
	public boolean setForename(String input) {
		String forename = input.trim();
		if (forename.matches(LETTER_PATTERN) && forename.length() <= 35) {
			this.forename = forename;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 * @return
	 */
	public boolean setSurname(String input) {
		String surname = input.trim();
		if (surname.matches(LETTER_PATTERN) && surname.length() <= 35) {
			this.surname = surname;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 * @return
	 */
	public boolean setPostcode(String input) {
		String postcode = input.trim();
		if (postcode.matches(NL_POSTCODE_PATTERN)) {
			this.postcode = postcode;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 * @return
	 */
	public boolean setStreet(String input) {
		String street = input.trim();
		if (street.matches(LETTER_PATTERN) && street.length() <= 35) {
			this.street = street;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 * @return
	 */
	public boolean setHouseNumber(String input) {
		String houseNumber = input.trim();
		if (houseNumber.matches(LETTER_NUMBER_WHITESPACE)
				&& houseNumber.length() <= 10) {
			this.houseNumber = houseNumber;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 * @return
	 */
	public boolean setCity(String input) {
		String city = input.trim();
		if (city.matches(LETTER_PATTERN) && city.length() <= 50) {
			this.city = city;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 * @return
	 */
	public boolean setNationality(String input) {
		String nationality = input.trim();
		if (nationality.matches(LETTER_PATTERN) && nationality.length() <= 50) {
			this.nationality = nationality;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 * @return
	 */
	public boolean setTelephoneNumber(String input) {
		String telephoneNumber = input.trim();
		if (telephoneNumber.matches(NL_PHONENUMBER)) {
			this.telephoneNumber = telephoneNumber;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber
	 *            the mobilePhoneNumber to set
	 * @return
	 */
	public boolean setMobilePhoneNumber(String input) {
		String mobilePhoneNumber = input.trim();
		if (mobilePhoneNumber.matches(NL_MOBILEPHONENUMBER)) {
			this.mobilePhoneNumber = mobilePhoneNumber;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 * @return
	 */
	public boolean setEmail(String input) {
		boolean result = true;
		String email = input.trim();
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
			this.email = email;
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber
	 *            the employeeNumber to set
	 * @return
	 */
	public boolean setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
		return true;
	}

	/**
	 * @return the notifications
	 */
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * @param notifications
	 *            the notifications to set
	 */
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * @return the myClients
	 */
	public ArrayList<Client> getMyClients() {
		return myClients;
	}

	/**
	 * @param myClients
	 *            the myClients to set
	 */
	public void setMyClients(ArrayList<Client> myClients) {
		this.myClients = myClients;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public boolean addDB() {
		return this.dbController.addUser(this);
	}

	public boolean updateDB() {
		return this.dbController.updateUser(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [Username = " + getUsername() + ", Password = "
				+ getPassword() + ", Forename = " + getForename()
				+ ", Surname = " + getSurname() + ", DateOfBirth = "
				+ getDateOfBirth() + ", Postcode = " + getPostcode()
				+ ", Street = " + getStreet() + ", HouseNumber = "
				+ getHouseNumber() + ", City = " + getCity()
				+ ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + ", isActive = " + isActive()
				+ ", EmployeeNumber = " + getEmployeeNumber() + "]";
	}

	public boolean setDateOfBirth(String inputDay, String inputMonth, String inputYear) {
		try {
			int day = Integer.valueOf(inputDay);
			int month = Integer.valueOf(inputMonth);
			int year = Integer.valueOf(inputYear);

			if (year > 0 && month > 0 && day > 0) {
				Calendar cal = Calendar.getInstance();
				cal.set(year, month - 1, day, 0, 0, 0);
				this.dateOfBirth = cal.getTime();
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

}