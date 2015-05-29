package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class SocialworkerServlet extends HttpServlet {

	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private User user = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.req = req;
		String message = "";
		String option = req.getAttribute("option").toString();
		
		// Give user object acces to the databaseinterface.
		user.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));

		// Check wich page is called, to overview users, create or update user.
		if (option.equals("create")) {
			this.create(message);
		} else if (option.equals("update")) {
			this.update(message);
		} else {
			this.overview(message);
		}
		
		reqDisp.forward(req, resp);
	}
	
	/**
	 * Method to put users arraylist in session, for socialworker_overview page.
	 * @param message information string if users was not found.
	 */
	private void overview(String message) {
		ArrayList<User> users = user.getDbController().getAllSocialworkers();
		if (users != null) {
			req.setAttribute("users", users);
			reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
		} else {
			message = "No users was found.";
			req.setAttribute("message", "Error occurred: " + message);
			reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
		}
	}
	
	/**
	 * Method to create new socialworker for add_socialworker page.
	 * @param message information string if validation was not valid or user succesfully created.
	 */
	private void create(String message) {
		user = new Socialworker();
		if (this.setValidation().equals("")) {
			user.addDB();
			message = "User " + user.getForename() + " " + user.getSurname() + " succesfully created.";
			req.setAttribute("message", "Information: " + message);
			reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
		} else {
			req.setAttribute("message", "Error occurred: " + message);
			reqDisp = req.getRequestDispatcher("/administrator/add_socialworker.html");
		}
	}
	
	/**
	 * Method to update user for update_socialworker page.
	 * @param message information string if validation was not valid or user succesfully updated.
	 */
	private void update(String message) {
		
		String username = (String) req.getAttribute("username");

		// Actor select a user to update
		// if the selected user to update was not found return to overview.
		if (username == null || username.equals("")) {
			message = "No user was found";
			req.setAttribute("message", "Error occurred: " + message);
			reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
			
		// get the selected user object to update.
		} else {
			// Actor select a user to update
			// if the selected user object not exist in session.
			// get the selected user object from the session arraylist from overview.
			if (req.getAttribute("employee") == null) {
				if (req.getAttribute("users") != null && req.getAttribute("users") instanceof ArrayList<?>) {
					@SuppressWarnings("unchecked")
					ArrayList<User> users = (ArrayList<User>) req.getAttribute("users");
					for (User u : users) {
						if (u.getUsername().equals(username)) {
							user = u;
							break;
						}
					}
					req.setAttribute("employee", user);
					reqDisp = req.getRequestDispatcher("/administrator/update_socialworker.html");
				}

			// Actor clicked to update user
			// if the selected user object already exist in session.
			} else {
				user = (User) req.getAttribute("employee");
				message = this.setValidation();
				if (message.equals("")) {
					user.updateDB();
					message = "User " + user.getForename() + " " + user.getSurname() + " succesfully updated.";
					req.setAttribute("message", "Information: " + message);
					req.removeAttribute("employee");
					reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
				} else {
					req.setAttribute("message", "Error occurred: " + message);
					reqDisp = req.getRequestDispatcher("/administrator/update_socialworker.html");
				}
			}
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
		
		message += (user.setUsername((String) req.getAttribute("username"))) ? "" : "Username not valid. ";
		message += (user.setForename((String) req.getAttribute("forename"))) ? "" : "Forename not valid. ";
		message += (user.setSurname((String) req.getAttribute("surname"))) ? "" : "Surname not valid. ";
		message += (user.setPostcode((String) req.getAttribute("postcode"))) ? "" : "Postcode not valid. ";
		message += (user.setStreet((String) req.getAttribute("street"))) ? "" : "Street not valid. ";
		message += (user.setHouseNumber((String) req.getAttribute("housenumber"))) ? "" : "Housenumber not valid. ";
		message += (user.setCity((String) req.getAttribute("city"))) ? "" : "City not valid. ";
		message += (user.setNationality((String) req.getAttribute("nationality"))) ? "" : "Nationality not valid. ";
		message += (user.setTelephoneNumber((String) req.getAttribute("telephonenumber"))) ? "" : "Telephonenumber not valid. ";
		message += (user.setMobilePhoneNumber((String) req.getAttribute("mobilephonenumber"))) ? "" : "Mobilephonenumber not valid. ";
		message += (user.setEmployeeNumber((String) req.getAttribute("employeenumber"))) ? "" : "Employeenumber not valid. ";

		String email1 = (String) req.getAttribute("email1");
		String email2 = (String) req.getAttribute("email2");
		message += (email1.equals(email2) && user.setEmail(email1)) ? "" : "Email not valid. ";

		String date = (String) req.getAttribute("date");
		String month = (String) req.getAttribute("month");
		String year = (String) req.getAttribute("year");
		message += (user.setDateOfBirth(date, month, year)) ? "" : "Date of birth not valid. ";

		return message;
	}

}
