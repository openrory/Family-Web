/**
 * 
 */
package domain.FamilyWeb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import servletControllers.FamilyWeb.OverviewController;
import databaseControllers.FamilyWeb.DatabaseInterface;

/**
 * @author Joery
 * @version 0.1
 * @since 2015-04-20
 */
public class Client {

	// Regular Expression Pattern
	private static final String FORENAME_PATTERN = "[A-Z][a-zA-Z]*";
	private static final String SURNAME_PATTERN = "[a-zA-z]+([ '-][a-zA-Z]+)*";
	private static final String NL_POSTCODE_PATTERN = "^[1-9][0-9]{3}[\\s]?[A-Za-z]{2}$";
	private static final String LETTER_PATTERN = "([a-zA-Z]*(\\s)*)*";
	private static final String NL_PHONENUMBER = "^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$";
	private static final String NL_MOBILEPHONENUMBER = "^(((\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$";
	private static final String LETTER_NUMBER_WHITESPACE = "([ ]*+[0-9A-Za-z]++[ ]*+)+";

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

	/**
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id
	 *            the client_id to set
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
	 * @param forename
	 *            the forename to set
	 */
	public boolean setForename(String input) {
		if (!input.trim().equals("")) {
			String forename = input.trim().substring(0, 1).toUpperCase() + input.toLowerCase().trim().substring(1);
			if (forename.matches(FORENAME_PATTERN) && forename.length() <= 35) {
				this.forename = forename;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setSurname(String input) {
		if (!input.trim().equals("")) {
			String surname = input.trim();
			if (surname.matches(SURNAME_PATTERN) && surname.length() <= 35) {
				this.surname = surname;
				return true;
			} else {
				return false;
			}
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
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public boolean setDateOfBirth(String inputDay, String inputMonth, String inputYear) {
		
		if (!inputDay.trim().equals("") && !inputMonth.trim().equals("")
				&& !inputYear.trim().equals("")) {
			try {
				int day = Integer.valueOf(inputDay);
				int month = Integer.valueOf(inputMonth);
				int year = Integer.valueOf(inputYear);

				if (year > 0 && month > 0 && day > 0) {
					
					Calendar cal = Calendar.getInstance();
					cal.set(year, month - 1, day, 0, 0, 0);
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					if (this.getDateOfBirth() != null && this.getDateOfBirth().toString().equals(dateFormat.format(cal.getTime()))) {

						System.out.println("teeest1");
						return true;
					} else if (cal.before(Calendar.getInstance())) {

						System.out.println("teeest2");
						this.dateOfBirth = cal.getTime();
						return true;
					} else {

						System.out.println("teeest3");
						return false;
					}
					
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
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
	 */
	public boolean setPostcode(String input) {
		if (!input.trim().equals("")) {
			String postcode = input.trim();
			if (postcode.matches(NL_POSTCODE_PATTERN)) {
				this.postcode = postcode;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setStreet(String input) {
		if (!input.trim().equals("")) {
			String street = input.trim();
			if (street.matches(LETTER_PATTERN) && street.length() <= 35) {
				this.street = street;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setHouseNumber(String input) {
		if (!input.trim().equals("")) {
			String houseNumber = input.trim();
			if (houseNumber.matches(LETTER_NUMBER_WHITESPACE)
					&& houseNumber.length() <= 10) {
				this.houseNumber = houseNumber;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setCity(String input) {
		if (!input.trim().equals("")) {
			String city = input.trim();
			if (city.matches(LETTER_PATTERN) && city.length() <= 50) {
				this.city = city;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setNationality(String input) {
		if (!input.trim().equals("")) {
			String nationality = input.trim();
			if (nationality.matches(LETTER_PATTERN)
					&& nationality.length() <= 50) {
				this.nationality = nationality;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setTelephoneNumber(String input) {
		if (!input.trim().equals("")) {
			String telephoneNumber = input.trim();
			if (telephoneNumber.matches(NL_PHONENUMBER)) {
				this.telephoneNumber = telephoneNumber;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setMobilePhoneNumber(String input) {
		if (!input.trim().equals("")) {
			String mobilePhoneNumber = input.trim();
			if (mobilePhoneNumber.matches(NL_MOBILEPHONENUMBER)) {
				this.mobilePhoneNumber = mobilePhoneNumber;
				return true;
			} else {
				return false;
			}
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
	 */
	public boolean setEmail(String input) {
		if (!input.trim().equals("")) {
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
		} else {
			return false;
		}
	}

	/**
	 * @return the fileNumber
	 */
	public String getFileNumber() {
		return fileNumber;
	}

	/**
	 * @param fileNumber
	 *            the fileNumber to set
	 * @return 
	 */
	public boolean setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
		return true;
		// MOET NOG GEMAAKT WORDEN
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
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
	 * @param myFamilymembers
	 *            the myFamilymembers to set
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
	 * @param myNetworks
	 *            the myNetworks to set
	 */
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

	public void addDB() {
		// TODO Auto-generated method stub

	}
}