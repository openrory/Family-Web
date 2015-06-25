/**
 * 
 */
package domain.FamilyWeb;

import java.util.Date;
import java.util.ArrayList;

import servletControllers.FamilyWeb.OverviewController;
import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public class Client {

	private int client_id;
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
	private String fileNumber;
	private Date dateCreated;

	private ArrayList<Familymember> myFamilymembers;
	private ArrayList<Network> myNetworks;
	private DatabaseInterface dbController;

	/**
	 * Constructor with parameters
	 * 
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
	 * @param fileNumber
	 * @param dateCreated
	 * @param myFamilymembers
	 * @param myNetworks
	 */
	public Client(String forename, String surname, Date dateOfBirth,
			String postcode, String street, String houseNumber, String city,
			String nationality, String telephoneNumber,
			String mobilePhoneNumber, String email, String fileNumber,
			Date dateCreated) {
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
		this.fileNumber = fileNumber;
		this.dateCreated = dateCreated;

		this.myFamilymembers = new ArrayList<Familymember>();
		this.myNetworks = new ArrayList<Network>();
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
	public Client() {
		this.myFamilymembers = new ArrayList<Familymember>();
		this.myNetworks = new ArrayList<Network>();
	}
	
	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public ArrayList<Familymember> getMyFamilymembers() {
		return myFamilymembers;
	}

	public void setMyFamilymembers(ArrayList<Familymember> myFamilymembers) {
		this.myFamilymembers = myFamilymembers;
	}

	public ArrayList<Network> getMyNetworks() {
		return myNetworks;
	}

	public void setMyNetworks(ArrayList<Network> myNetworks) {
		this.myNetworks = myNetworks;
	}

	public boolean addDB(int userID) {
		return OverviewController.getInstance().getDb().addClient(this, userID);
	}

	// REALISEREN CLIENT KUNNEN KOPPELEN OVERDRAGEN AAN ANDERE USER
	public boolean updateDB(int user_id) {
		return this.dbController.updateClient(this, user_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [Client_id = " + getClient_id() + ", Forename = "
				+ getForename() + ", Surname = " + getSurname()
				+ ", DateOfBirth = " + getDateOfBirth() + ", Postcode = "
				+ getPostcode() + ", Street = " + getStreet()
				+ ", HouseNumber =" + getHouseNumber() + ", City = "
				+ getCity() + ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + ", FileNumber = "
				+ getFileNumber() + ", DateCreated = " + getDateCreated() + "]";
	}

}