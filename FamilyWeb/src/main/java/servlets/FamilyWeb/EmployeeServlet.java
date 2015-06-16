package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String option = (req.getAttribute("option") != null) ? (String) req.getAttribute("option") : "";
		
		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		// Check wich page is called, to overview users, create or update user.
		if (option.equals("create")) {
			req.getSession().removeAttribute("employee");
			this.create(message);
		} else if (option.equals("update")) {
			this.update(message);
		} else if (option.equals("summary")) {
			if (req.getSession().getAttribute("userID") != null) {
				req.getSession().removeAttribute("employee");
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
		req.getSession().setAttribute("employee", user);
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
		user = new Socialworker();
		// Give user object acces to the databaseinterface.
		user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		if (this.setValidation().equals("")) {
			user.addDB();
			message = "Employee" + user.getForename() + " " + user.getSurname() + " succesfully created.";
			req.setAttribute("message", "Information: " + message);
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
			
			User employeeUpdate = (employeeObject instanceof Administrator) ? (Administrator) employeeObject : (Socialworker) employeeObject;
			this.user = employeeUpdate;
			
			if (this.setValidation().equals("")) {
				user.updateDB();
				message = "Employee " + user.getForename() + " " + user.getSurname() + " succesfully updated.";
				req.setAttribute("message", "Information: " + message);
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

		// MOET NOG VIA CHECKBOX WORDEN AFGEVANGEN
		user.setActive(true);
		// MOET NOG VIA CHECKBOX WORDEN AFGEVANGEN
		
		if (req.getAttribute("username") != null)
		
		message += (user.setUsername((req.getAttribute("username") != null) ? (String) req.getAttribute("username") : "")) ? "" : "Username not valid. ";
		message += (user.setForename((req.getAttribute("forename") != null) ? (String) req.getAttribute("forename") : "")) ? "" : "Forename not valid. ";
		message += (user.setSurname((req.getAttribute("surname") != null) ? (String) req.getAttribute("surname") : "")) ? "" : "Surname not valid. ";
		message += (user.setPostcode((req.getAttribute("postcode") != null) ? (String) req.getAttribute("postcode") : "")) ? "" : "Postcode not valid. ";
		message += (user.setStreet((req.getAttribute("street") != null) ? (String) req.getAttribute("street") : "")) ? "" : "Street not valid. ";
		message += (user.setHouseNumber((req.getAttribute("housenumber") != null) ? (String) req.getAttribute("housenumber") : "")) ? "" : "Housenumber not valid. ";
		message += (user.setCity((req.getAttribute("city") != null) ? (String) req.getAttribute("city") : "")) ? "" : "City not valid. ";
		message += (user.setNationality((req.getAttribute("nationality") != null) ? (String) req.getAttribute("nationality") : "")) ? "" : "Nationality not valid. ";
		message += (user.setTelephoneNumber((req.getAttribute("telephonenumber") != null) ? (String) req.getAttribute("telephonenumber") : "")) ? "" : "Telephonenumber not valid. ";
		message += (user.setMobilePhoneNumber((req.getAttribute("mobilephonenumber") != null) ? (String) req.getAttribute("mobilephonenumber") : "")) ? "" : "Mobilephonenumber not valid. ";
		message += (user.setEmployeeNumber((req.getAttribute("employeenumber") != null) ? (String) req.getAttribute("employeenumber") : "")) ? "" : "Employeenumber not valid. ";

		String email1 = (req.getAttribute("email1") != null) ? (String) req.getAttribute("email1") : "";
		String email2 = (req.getAttribute("email2") != null) ? (String) req.getAttribute("email2") : "";
		
		if (!email1.equals("") || !email2.equals("") || email1.equals(email2)) {
			message += (user.setEmail(email1)) ? "" : "Email not valid. ";
		}
		
		String dateOfBirth = (req.getAttribute("dateofbirth") != null) ? (String) req.getAttribute("dateofbirth") : "";
		
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String date = parts[0]; 
			String month = parts[1]; 
			String year = parts[2];
			user.setDateOfBirth(date, month, year);
		} else {
			message += "Date of birth not valid. ";
		}

		return message;
	}

}
