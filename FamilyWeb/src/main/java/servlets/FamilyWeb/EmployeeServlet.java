package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FamilyWeb.MailService;
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

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.req = req;
		String option = (req.getParameter("option") != null) ? (String) req.getParameter("option") : "";
		
		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;
		
		// Check wich page is called, to overview users, create or update user.
		if (option.equals("create")) {
			this.create();

		} else if (option.equals("update")) {
			this.update();

		} else if (option.equals("summary")) {

			if (req.getParameter("userID") != null) {
				int userID = Integer.valueOf((String) req.getParameter("userID"));
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
	
	private void setMessage(String messageType, String message) {
		req.setAttribute("messageType", messageType);
		req.setAttribute("message", message);
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
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesvol toegevoegd.";
				this.setMessage(MESSAGE_SUCCESS, message);
			} else {
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
		
		String message = "";
		Object employeeObject = req.getAttribute("employee");	
		
		if (employeeObject != null) {
			
			this.user = (employeeObject instanceof Administrator) ? (Administrator) employeeObject : (Socialworker) employeeObject;
			message = this.setValidation();
			
			if (message.equals("")) {
				user.updateDB();
				req.removeAttribute("employee");
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesvol bijgewerkt.";
				this.setMessage(MESSAGE_SUCCESS, message);
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

	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 * @return information string if the string is not empty the validation failed.
	 */
	private String setValidation() {

		String message = "";
		
		message += (user.setEmployeeNumber((req.getParameter("employeenumber") != null) ? (String) req.getParameter("employeenumber") : "")) ? "" : "Personeelsnummer, ";
		message += (user.setForename((req.getParameter("forename") != null) ? (String) req.getParameter("forename") : "")) ? "" : "Voornaam, ";
		message += (user.setSurname((req.getParameter("surname") != null) ? (String) req.getParameter("surname") : "")) ? "" : "Achternaam, ";
		
		String dateOfBirth = (req.getParameter("dateofbirth") != null) ? (String) req.getParameter("dateofbirth") : "";
		
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];
			user.setDateOfBirth(date, month, year);
		} else {
			message += "Geboortedatum, ";
		}
		
		message += (user.setNationality((req.getParameter("nationality") != null) ? (String) req.getParameter("nationality") : "")) ? "" : "Nationaliteit, ";
		message += (user.setStreet((req.getParameter("street") != null) ? (String) req.getParameter("street") : "")) ? "" : "Straat, ";
		message += (user.setHouseNumber((req.getParameter("housenumber") != null) ? (String) req.getParameter("housenumber") : "")) ? "" : "Huisnummer, ";
		message += (user.setPostcode((req.getParameter("postcode") != null) ? (String) req.getParameter("postcode") : "")) ? "" : "Postcode, ";
		message += (user.setCity((req.getParameter("city") != null) ? (String) req.getParameter("city") : "")) ? "" : "Stad, ";
		message += (user.setTelephoneNumber((req.getParameter("phonenumber") != null) ? (String) req.getParameter("phonenumber") : "")) ? "" : "Telefoonnummer, ";
		message += (user.setMobilePhoneNumber((req.getParameter("mobile") != null) ? (String) req.getParameter("mobile") : "")) ? "" : "Mobiel nummer, ";
		
		String email1 = (req.getParameter("email") != null) ? (String) req.getParameter("email") : "";
		String email2 = (req.getParameter("email_confirmation") != null) ? (String) req.getParameter("email_confirmation") : "";
		
		if (!email1.equals("") || !email2.equals("") || email1.equals(email2)) {
			message += (user.setEmail(email1)) ? "" : "Email, ";
		}
		
		message += (user.setUsername((req.getParameter("username") != null) ? (String) req.getParameter("username") : "")) ? "" : "Gebruikersnaam, ";

		user.setActive((req.getParameter("is_active") != null ? true : false));

		String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		user.setPassword(password);
		
		message += (!message.equals("")) ? "niet correct ingevuld" : "";
		return message;
	}
}