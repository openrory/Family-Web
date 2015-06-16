package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {

	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private Client client = null;
	private User currentUser = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.req = req;
		this.client = null;
		String message = "";
		String option = req.getAttribute("option").toString();
		req.getSession().removeAttribute("message");

		// Get current user
		Object cUser = req.getSession().getAttribute("user");
		currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;

		// Check wich page is called, to overview clients, create, summary or update client.
		if (option.equals("create")) {
			this.create(message);
			
		} else if (option.equals("update")) {
			this.update(message);
			
		} else if (option.equals("summary")) {
			int clientID = Integer.valueOf((String) req.getSession().getAttribute("clientID"));
			this.summary(clientID);
		} else {
			this.overview(message);
			
		}

		reqDisp.forward(req, resp);
	}

	/**
	 * Method to create new client.
	 * @param message information string if validation failed.
	 */
	private void create(String message) {
		if (this.setValidation().equals("")) {
			client = new Client();
			client.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
			client.setDateCreated(new Date());
			client.addDB(currentUser);
			message = "Client " + client.getForename() + " " + client.getSurname() + " succesfully created.";
			req.setAttribute("message", "Information: " + message);
			// reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
		} else {
			req.setAttribute("message", "Error occurred: " + message);
			// reqDisp = req.getRequestDispatcher("/administrator/add_socialworker.html");
		}
	}

	/**
	 *  Method is used by create or update method, to set the attributes and validate the input.
	 * @return information string if the string is not empty the validation failed.
	 */
	private String setValidation() {

		String message = "";

		message += (client.setForename((String) req.getAttribute("forename"))) ? "" : "Forename not valid. ";
		message += (client.setSurname((String) req.getAttribute("surname"))) ? "" : "Surname not valid. ";
		message += (client.setPostcode((String) req.getAttribute("postcode"))) ? "" : "Postcode not valid. ";
		message += (client.setStreet((String) req.getAttribute("street"))) ? "" : "Street not valid. ";
		message += (client.setHouseNumber((String) req.getAttribute("housenumber"))) ? "" : "Housenumber not valid. ";
		message += (client.setCity((String) req.getAttribute("city"))) ? "" : "City not valid. ";
		message += (client.setNationality((String) req.getAttribute("nationality"))) ? "" : "Nationality not valid. ";
		message += (client.setTelephoneNumber((String) req.getAttribute("telephonenumber"))) ? "" : "Telephonenumber not valid. ";
		message += (client.setMobilePhoneNumber((String) req.getAttribute("mobilephonenumber"))) ? "" : "Mobilephonenumber not valid. ";

		String email1 = (String) req.getAttribute("email1");
		message += (client.setEmail(email1)) ? "" : "Email not valid. ";

		// ** NOG REALISEREN **
		// client.setFileNumber(fileNumber);

		String date = (String) req.getAttribute("date");
		String month = (String) req.getAttribute("month");
		String year = (String) req.getAttribute("year");
		message += (client.setDateOfBirth(date, month, year)) ? "" : "Date of birth not valid. ";

		return message;
	}

	/**
	 *  Method to update client data.
	 * @return information string wich client has been updated.
	 */
	private void update(String message) {
		this.client = (Client) req.getSession().getAttribute("Client");
		this.setValidation();
		this.client.updateDB();
		message = "Client " + this.client.getForename() + " " + this.client.getSurname() + " updated";
		req.setAttribute("message", "Information: " + message);
		// req.getRequestDispatcher("/administrator/socialworker_overview.html");
	}

	/**
	 * Method to summary a client.
	 * @param clientID the client id from the client to summary.
	 */
	private void summary(int clientID) {
		this.client = null;
		for (Client c : this.getClients()) {
			if (clientID == c.getClient_id()) {
				this.client = c;
				break;
			}
		}
		req.getSession().setAttribute("Client", client);
	}

	/**
	 * Method to get all clients depending on the current user type
	 * @return ArrayList of clients
	 */
	private ArrayList<Client> getClients() {
		return (currentUser instanceof Administrator) ? currentUser
				.getDbController().getAllClients() : currentUser
				.getDbController().getAllClientsOfUser(currentUser);
	}

	/**
	 * Method to put all clients in session depending on the current user type
	 * @param message
	 */
	private void overview(String message) {
		ArrayList<Client> clients = this.getClients();
		if (clients != null) {
			req.setAttribute("clients", clients);
			// reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
		} else {
			message = "No clients were found.";
			req.setAttribute("message", "Error occurred: " + message);
			// reqDisp = reqgetRequestDispatcher("/administrator/socialworker_overview.html");
		}
	}
}