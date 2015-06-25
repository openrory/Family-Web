package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import util.FamilyWeb.MailService;
import util.FamilyWeb.Validation;
import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class EmployeeServlet extends HttpServlet {

	private final String MESSAGE_SUCCESS = "success";
	private final String MESSAGE_ERROR = "error";
	
	private final String PAGE_EMPLOYEE_OVERVIEW = "/administrator/employee_overview.jsp";
	private final String PAGE_EMPLOYEE_ADD_EDIT = "/administrator/add_edit_employee.jsp";
	
	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private User user = null;
	private User currentUser = null;
	private Validation validation = Validation.getInstance();
	
	private String option = "";

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.req = req;
		option = (req.getParameter("option") != null) ? (String) req.getParameter("option") : "";

		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		// Check wich page is called, to overview users, create or update user.
		if (option.equals("create")) {
			this.create();
			req.setAttribute("option", "create");
		} else if (option.equals("update")) {
			this.update();

		} else if (option.equals("summary")) {

			if (req.getParameter("currentID") != null) {
				int userID = Integer.valueOf((String) req.getParameter("currentID"));
				this.summary(userID);
			} else {
				this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, werknemer niet gevonden.");
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
			}
			
		} else {
			this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, pagina niet gevonden.");
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
		}
		reqDisp.forward(req, resp);
	}
	
	/**
	 * Method to create new employee for add_socialworker page.
	 */
	private void create() {

		String message = "";
		user = (req.getParameter("is_administrator") != null) ? new Administrator() : new Socialworker();
		
		// Give user object acces to the databaseinterface.
		user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		message = this.setValidation();
		
		if (message.equals("")) {
			
			user.addDB();
			
			String mailSubject = "Welkom bij FamilyWeb!"; 
			String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een account aangemaakt op FamilyWeb met uw e-mailadres.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + this.user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + this.user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
			MailService mailService = new MailService(this.user, mailSubject, mailMessage);
			message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator om het wachtwoord te resetten.";
			
			if (message.equals("")) {
				message += "Gebruiker " + user.getForename() + " " + user.getSurname() + " succesvol toegevoegd.";
				this.setMessage(MESSAGE_SUCCESS, message);
			} else {
				this.setMessage(MESSAGE_ERROR, message);
			}
			
			try {
				req.getSession().setAttribute("usersJSON", OverviewController.getInstance().RefreshOverviewUsers(user));
			} catch (JSONException e) {
				//e.printStackTrace();
				message += " Overzicht door een onbekende fout niet herladen.";
				this.setMessage(MESSAGE_ERROR, message);
			}
			
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
			
		} else {
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
		}
	}

	/**
	 * Method to update user for update_socialworker page.
	 */
	private void update() {
		
		if (req.getParameter("userID") != null) {
			try {
				System.out.println("TEST1111111111111");
				int userID = Integer.valueOf(req.getParameter("userID"));
				this.summary(userID);
				System.out.println("TEST1111111111111222222");
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			}
		}
		
		String message = "";
		Object employeeObject = req.getAttribute("employee");	
		
		if (employeeObject != null) {
			
			this.user = (employeeObject instanceof Administrator) ? (Administrator) employeeObject : (Socialworker) employeeObject;
			message = this.setValidation();
			
			if (message.equals("")) {
				
				if (req.getParameter("reset_password") != null) {
					user.setWwreset(true);
					String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
					user.setPassword(password);
					
					String mailSubject = "Welkom bij FamilyWeb!"; 
					String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>" + user.getForename() + "</span>,</p><p>Er is een wachtwoord reset aangevraagd.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + this.user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + this.user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
					MailService mailService = new MailService(this.user, mailSubject, mailMessage);
					message += (mailService.sendMail()) ? message : message + " Mailservice fout de mail is niet verzonden, Raadpleeg de administrator om het wachtwoord te resetten.";
				}
				
				user.updateDB();
				req.removeAttribute("employee");
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesvol bijgewerkt.";
				this.setMessage(MESSAGE_SUCCESS, message);
				
				try {
					req.getSession().setAttribute("usersJSON", OverviewController.getInstance().RefreshOverviewUsers(user));
				} catch (JSONException e) {
					//e.printStackTrace();
					message += " Overzicht door een onbekende fout niet herladen.";
					this.setMessage(MESSAGE_ERROR, message);
				}
				
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
			} else {
				this.setMessage(MESSAGE_ERROR, message);
				reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
			}
			
		} else {
			message = "Onverwachte fout opgetreden, werknemer niet gevonden.";
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_OVERVIEW);
		}
	}
	
	private boolean employeeNumberExist(String employeenumber) {
		for (User u : ((Administrator) currentUser).getUsers()) {
			if (u.getEmployeeNumber().equals(employeenumber)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean usernameExist(String username) {
		for (User u : ((Administrator) currentUser).getUsers()) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 */
	private String setValidation() {

		String message = "";
		
		String forename = validation.validateForename(req.getParameter("forename"));
		String surname = validation.validateSurname(req.getParameter("surname"));
		String dateOfBirth = req.getParameter("dateofbirth");
		String nationality = validation.validateNationality(req.getParameter("nationality"));
		String street = validation.validateStreet(req.getParameter("street"));
		String housenumber = validation.validateHouseNumber(req.getParameter("streetnumber"));
		String postcode = validation.validatePostcode(req.getParameter("postcode"));
		String city = validation.validateCity(req.getParameter("city"));
		String phonenumber = validation.validateTelephoneNumber(req.getParameter("phonenumber"));
		String mobile = validation.validateMobilePhoneNumber(req.getParameter("mobile"));
		String email = validation.validateEmail(req.getParameter("email"), req.getParameter("email_confirmation"));
		
		if (forename != null) { user.setForename(forename); } else { message += "Voornaam, "; }
		if (surname != null) { user.setSurname(surname); } else { message += "Achternaam, "; }
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];
			Date dateofbirth = validation.validateDateOfBirth(date, month, year);
			if (dateofbirth != null) {
				user.setDateOfBirth(dateofbirth);
			} else {
				message += "Geboortedatum, ";
			}
		} else {
			message += "Geboortedatum, ";
		}
		if (nationality != null) { user.setNationality(nationality); } else { message += "Nationaliteit, "; }
		if (street != null) { user.setStreet(street); } else { message += "Straat, "; }
		if (housenumber != null) { user.setHouseNumber(housenumber); } else { message += "Huisnummer, "; }
		if (postcode != null) { user.setPostcode(postcode); } else { message += "Postcode, "; }
		if (city != null) { user.setCity(city); } else { message += "Woonplaats, "; }
		if (phonenumber != null) { user.setTelephoneNumber(phonenumber); } else { message += "Telefoonnummer, "; }
		if (mobile != null) { user.setMobilePhoneNumber(mobile); } else { message += "Mobielnummer, "; }
		if (email != null) { user.setEmail(email); } else { message += "Email, "; }

		user.setActive((req.getParameter("is_active") != null ? true : false));
		
		if (!option.equals("update")) {
		
		String EmployeeNumber = validation.validateEmployeeNumber((req.getParameter("employeenumber")));
		String username = validation.validateUsername(req.getParameter("username"));
		String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		if (username != null) { user.setUsername(username); } else { message += "Gebruikersnaam, "; }
		if (EmployeeNumber != null) { user.setEmployeeNumber(EmployeeNumber); } else { message += "Personeelsnummer, "; }
		
		user.setPassword(password);
		
		message += (!message.equals("")) ? "niet correct ingevuld." : "";
		
		message += (!this.employeeNumberExist((EmployeeNumber != null) ? EmployeeNumber : "") ? "" : " Personeelsnummer bestaat al.");
		message += (!this.usernameExist((username != null) ? username : "") ? "" : " Gebruikersnaam bestaat al.");
			if (!message.equals("")) {
				req.setAttribute("employee", user);
				req.setAttribute("option", "create");
			}
		} else {
			message += (!message.equals("")) ? "niet correct ingevuld." : "";
		}
		
		return message;
	}

	/**
	 * Method to summary a client.
	 * @param clientID the client id from the client to summary.
	 */
	private void summary(int userID) {
		this.user = null;
		for (User u : this.getUsers()) {
			if (userID == u.getUser_id()) {
				this.user = u;
				break;
			}
		}
		req.setAttribute("employee", user);
		reqDisp = req.getRequestDispatcher(PAGE_EMPLOYEE_ADD_EDIT);
	}

	/**
	 * Method to get all users depending on the current user type
	 * @return ArrayList of users
	 */
	private ArrayList<User> getUsers() {
		return (currentUser instanceof Administrator) ? currentUser
				.getDbController().getAllUsers() : currentUser
				.getDbController().getAllSocialworkers();
	}
	
	
	private void setMessage(String messageType, String message) {
		req.setAttribute("messageType", messageType);
		req.setAttribute("message", message);
	}
}