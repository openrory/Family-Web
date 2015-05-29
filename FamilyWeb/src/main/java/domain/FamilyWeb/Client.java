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

	/**
	 * Clean constructor
	 */
	public Client() {
		this.myFamilymembers = new ArrayList<Familymember>();
		this.myNetworks = new ArrayList<Network>();
	}

	/**
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * @param forename the forename to set
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fileNumber
	 */
	public String getFileNumber() {
		return fileNumber;
	}

	/**
	 * @param fileNumber the fileNumber to set
	 */
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
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
	 * @return the myFamilymembers
	 */
	public ArrayList<Familymember> getMyFamilymembers() {
		return myFamilymembers;
	}

	/**
	 * @param myFamilymembers the myFamilymembers to set
	 */
	public void setMyFamilymembers(ArrayList<Familymember> myFamilymembers) {
		this.myFamilymembers = myFamilymembers;
	}

	/**
	 * @return the myNetworks
	 */
	public ArrayList<Network> getMyNetworks() {
		return myNetworks;
	}

	/**
	 * @param myNetworks the myNetworks to set
	 */
	public void setMyNetworks(ArrayList<Network> myNetworks) {
		this.myNetworks = myNetworks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [Client_id = " + getClient_id() + ", Forename = " + getForename() + ", Surname = "
				+ getSurname() + ", DateOfBirth = " + getDateOfBirth()
				+ ", Postcode = " + getPostcode() + ", Street = "
				+ getStreet() + ", HouseNumber =" + getHouseNumber()
				+ ", City = " + getCity() + ", Nationality = "
				+ getNationality() + ", TelephoneNumber = "
				+ getTelephoneNumber() + ", MobilePhoneNumber = "
				+ getMobilePhoneNumber() + ", Email = " + getEmail()
				+ ", FileNumber = " + getFileNumber()
				+ ", DateCreated = " + getDateCreated() + "]";
	}
}
