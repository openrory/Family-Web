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
public class Familymember {
	private int member_id;
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
	 */
	public Familymember(String forename, String surname, Date dateOfBirth,
			String postcode, String street, String houseNumber, String city,
			String nationality, String telephoneNumber,
			String mobilePhoneNumber, String email) {
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

		this.myNetworks = new ArrayList<Network>();
	}
	
	/**
	 * Clean constructor
	 */
	public Familymember() {
		this.myNetworks = new ArrayList<Network>();
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

	/**
	 * @return the member_id
	 */
	public int getMember_id() {
		return member_id;
	}

	/**
	 * @param member_id the member_id to set
	 */
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Familymember [Forename = " + getForename()
				+ ", Surname = " + getSurname() + ", DateOfBirth = "
				+ getDateOfBirth() + ", Postcode = " + getPostcode()
				+ ", Street = " + getStreet() + ", HouseNumber = "
				+ getHouseNumber() + ", City = " + getCity()
				+ ", Nationality = " + getNationality()
				+ ", TelephoneNumber = " + getTelephoneNumber()
				+ ", MobilePhoneNumber = " + getMobilePhoneNumber()
				+ ", Email = " + getEmail() + "]";
	}

}
