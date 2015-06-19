package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

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

	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private User user = null;
	private User currentUser = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.req = req;
		String message = "";
		String option = (req.getParameter("option") != null) ? (String) req.getParameter("option") : "";
		
		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		System.out.println("dit is current user: " + currentUser.getForename());
		
		// Check wich page is called, to overview users, create or update user.
		if (option.equals("create")) {
			this.create(message);
		} if (option.equals("update")) {
			this.update(message);
		} else if (option.equals("summary")) {
			if (req.getSession().getAttribute("userID") != null) {
				int userID = Integer.valueOf((String) req.getSession().getAttribute("userID"));
				this.summary(userID);
				reqDisp = req.getRequestDispatcher("/administrator/add_edit_employee.jsp");
			} else {
				message = "Error: userID not found, unexpected error.";
			}
		} else {
			message = "Error: page not found, unexpected error.";
			reqDisp = req.getRequestDispatcher("/administrator/employee_overview.jsp");
		}
		reqDisp.forward(req, resp);
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
	 * Method to create new socialworker for add_socialworker page.
	 * @param message information string if validation was not valid or user succesfully created.
	 */
	private void create(String message) {

		user = (req.getParameter("is_administrator") != null) ? new Administrator() : new Socialworker();
		
		// Give user object acces to the databaseinterface.
		user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		if (this.setValidation().equals("")) {
			user.addDB();
			String mailSubject = "Welkom bij FamilyWeb!"; 
			String mailMessage = "<div class='text'><p>Beste <span class='bold_text'>Wouter</span>,</p><p>Er is een account aangemaakt op FamilyWeb met uw e-mailadres.</p><p>U kunt nu inloggen op <a href='familyweb.balans.nl'>Familyweb</a> met de volgende gegevens:</p></div><div class='information'><table class='custom_table'><tr class='row'><td class='data'>Gebruikersnaam</td><td class='data'>" + this.user.getUsername() + "</td></tr><tr class='row'><td class='data'>Wachtwoord</td><td class='data'>" + this.user.getPassword() + "</td></tr></table></div><div class='text'><p>Mochten er zich problemen voordoen met het inloggen of met het gebruik van de applicatie dan kunt u contact opnemen met de <a href='mailto:info@familyweb.nl'>administrator.</a></p><p>Wij hopen dat u een fijne ervaring heeft met de applicatie.</p><p>FamilyWeb</p></div>";
			MailService mailService = new MailService(this.user, mailSubject, mailMessage);
			message += (mailService.sendMail()) ? "" : "Mailservice error";
			
			message = "Employee " + user.getForename() + " " + user.getSurname() + " succesfully created.";
			req.setAttribute("message", message);
			reqDisp = req.getRequestDispatcher("/administrator/employee_overview.jsp");
		} else {
			req.setAttribute("message", "Error occurred: " + message);
			reqDisp = req.getRequestDispatcher("/administrator/add_edit_employee.jsp");
		}
	}
	
	/**
	 * Method to update user for update_socialworker page.
	 * @param message information string if validation was not valid or user succesfully updated.
	 */
	private void update(String message) {
		
		Object employeeObject = req.getSession().getAttribute("employee");	
		if (employeeObject != null) {
			
			this.user = (employeeObject instanceof Administrator) ? (Administrator) employeeObject : (Socialworker) employeeObject;
			
			if (this.setValidation().equals("")) {
				user.updateDB();
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesfully updated.";
				req.setAttribute("message", "Information: " + message);
				req.removeAttribute("employee");
				reqDisp = req.getRequestDispatcher("/administrator/employee_overview.jsp");
			} else {
				//req.getSession().setAttribute("employee", user);
				req.setAttribute("message", "Error occurred: " + message);
				reqDisp = req.getRequestDispatcher("/administrator/add_edit_employee.jsp");
			}
		} else {
			req.setAttribute("message", "Error occurred: update not possible employee not found, unexpected error.");
			reqDisp = req.getRequestDispatcher("/administrator/employee_overview.jsp");
		}
	}

	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 * @return information string if the string is not empty the validation failed.
	 */
	private String setValidation() {

		String message = "";

		user.setActive((req.getParameter("is_active") != null ? true : false));

		user.setPassword("password");
		
		message += (user.setUsername((req.getParameter("username") != null) ? (String) req.getParameter("username") : "")) ? "" : "Username not valid. ";
		message += (user.setForename((req.getParameter("forename") != null) ? (String) req.getParameter("forename") : "")) ? "" : "Forename not valid. ";
		message += (user.setSurname((req.getParameter("surname") != null) ? (String) req.getParameter("surname") : "")) ? "" : "Surname not valid. ";
		message += (user.setPostcode((req.getParameter("postcode") != null) ? (String) req.getParameter("postcode") : "")) ? "" : "Postcode not valid. ";
		message += (user.setStreet((req.getParameter("street") != null) ? (String) req.getParameter("street") : "")) ? "" : "Street not valid. ";
		message += (user.setHouseNumber((req.getParameter("housenumber") != null) ? (String) req.getParameter("housenumber") : "")) ? "" : "Housenumber not valid. ";
		message += (user.setCity((req.getParameter("city") != null) ? (String) req.getParameter("city") : "")) ? "" : "City not valid. ";
		message += (user.setNationality((req.getParameter("nationality") != null) ? (String) req.getParameter("nationality") : "")) ? "" : "Nationality not valid. ";
		message += (user.setTelephoneNumber((req.getParameter("phonenumber") != null) ? (String) req.getParameter("phonenumber") : "")) ? "" : "Telephonenumber not valid. ";
		message += (user.setMobilePhoneNumber((req.getParameter("mobile") != null) ? (String) req.getParameter("mobile") : "")) ? "" : "Mobilephonenumber not valid. ";
		message += (user.setEmployeeNumber((req.getParameter("employeenumber") != null) ? (String) req.getParameter("employeenumber") : "")) ? "" : "Employeenumber not valid. ";
		
		String email1 = (req.getParameter("email") != null) ? (String) req.getParameter("email") : "";
		String email2 = (req.getParameter("email_confirmation") != null) ? (String) req.getParameter("email_confirmation") : "";
		
		if (!email1.equals("") || !email2.equals("") || email1.equals(email2)) {
			message += (user.setEmail(email1)) ? "" : "Email not valid. ";
		}
		
		String dateOfBirth = (req.getParameter("dateofbirth") != null) ? (String) req.getParameter("dateofbirth") : "";
		
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];
			user.setDateOfBirth(date, month, year);
		} else {
			message += "Date of birth not valid. ";
		}
		return message;
	}
}
