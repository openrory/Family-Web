/**
 * 
 */
package domain.FamilyWeb;


import java.util.ArrayList;

import java.util.Date;

import javax.management.Notification;

import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public abstract class User {

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
	private boolean wwreset;

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
		this.setWwreset(true);
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
	
	public boolean addDB() {
		return this.dbController.addUser(this);
	}

	public boolean updateDB() {
		return this.dbController.updateUser(this);
	}

	/**
	 * @return the wwreset
	 */
	public boolean isWwreset() {
		return wwreset;
	}

	/**
	 * @param wwreset
	 *            the wwreset to set
	 */
	public void setWwreset(boolean wwreset) {
		this.wwreset = wwreset;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	public ArrayList<Client> getMyClients() {
		return myClients;
	}

	public void setMyClients(ArrayList<Client> myClients) {
		this.myClients = myClients;
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

}