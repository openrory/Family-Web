package servlets.FamilyWeb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import databaseControllers.FamilyWeb.DatabaseInterface;
import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {

	private final String MESSAGE_SUCCESS = "success";
	private final String MESSAGE_ERROR = "error";
	
	private final String PAGE_CLIENT_OVERVIEW = "/administrator/client_overview.jsp";
	private final String PAGE_CLIENT_ADD_EDIT = "/administrator/add_edit_client.jsp";
	
	private RequestDispatcher reqDisp = null;
	private HttpServletRequest req = null;
	private Client client = null;
	private User currentUser = null;
	
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

		} else if (option.equals("update")) {
			this.update();

		} else if (option.equals("summary")) {

			if (req.getParameter("clientID") != null) {
				int clientID = Integer.valueOf((String) req.getParameter("clientID"));
				this.summary(clientID);
			} else {
				this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, client niet gevonden.");
				reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
			}
			
		} else {
			this.setMessage(MESSAGE_ERROR, "Onverwachte fout opgetreden, pagina niet gevonden.");
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
		}
		reqDisp.forward(req, resp);
	}
	
	/**
	 * Method to create new employee for add_socialworker page.
	 */
	private void create() {

		String message = "";
		client = new Client();
		
		// Give client object acces to the databaseinterface.
		client.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
		message = this.setValidation();
		
		int userID = 0;
		if (this.currentUser instanceof Socialworker) {
			userID = this.currentUser.getUser_id();
		} else {
			try {
				userID = Integer.valueOf(req.getParameter("socialworker_id"));
			} catch (NumberFormatException e) {
				message += "Zorgprofessional niet gevonden."; //e.printStackTrace();
			}
		}
		
		if (message.equals("")) {
			
			client.setDateCreated(new Date());
			client.addDB(userID);

			message += "Client " + client.getForename() + " " + client.getSurname() + " succesvol toegevoegd.";
			this.setMessage(MESSAGE_SUCCESS, message);
			
			try {
				req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(this.currentUser));
			} catch (JSONException e) {
				//e.printStackTrace();
				message += " Overzicht door een onbekende fout niet herlanden, overzicht is niet up-to-date met de database.";
				this.setMessage(MESSAGE_ERROR, message);
			}
			
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
			
		} else {
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
		}
	}

	/**
	 * Method to update user for update_socialworker page.
	 */
	private void update() {
		
		if (req.getParameter("clientID") != null) {
			try {
				int clientID = Integer.valueOf(req.getParameter("clientID"));
				this.summary(clientID);
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			}
		}
		
		String message = "";
		Object clientObject = req.getAttribute("client");	
		
		if (clientObject != null) {
			
			client = (Client) clientObject;
			message = this.setValidation();
			int socialworkerID = 0;
						if(currentUser instanceof Administrator) {
							try {
								socialworkerID = Integer.valueOf(req.getParameter("socialworker_id"));
							} catch (NumberFormatException e) {
								message += "Zorgprofessional niet gevonden."; //e.printStackTrace();
							}
						} 
			if (message.equals("")) {
				
				client.updateDB(socialworkerID);
				req.removeAttribute("client");
				message = "Client " + client.getForename() + " " + client.getSurname() + " succesvol bijgewerkt.";
				this.setMessage(MESSAGE_SUCCESS, message);
				
				try {
					req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().RefreshOverviewClients(this.currentUser));
				} catch (JSONException e) {
					//e.printStackTrace();
					message += " Overzicht door een onbekende fout niet herlanden, overzicht is niet up-to-date met de database.";
					this.setMessage(MESSAGE_ERROR, message);
				}
				
				reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
			} else {
				this.setMessage(MESSAGE_ERROR, message);
				reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
			}
			
		} else {
			message = "Onverwachte fout opgetreden, client niet gevonden.";
			this.setMessage(MESSAGE_ERROR, message);
			reqDisp = req.getRequestDispatcher(PAGE_CLIENT_OVERVIEW);
		}
	}

	/**
	 * Method is used by create or update method, to set the attributes and validate the input.
	 */
	private String setValidation() {

		String message = "";
		
		message += (client.setFileNumber((req.getParameter("filenumber") != null) ? (String) req.getParameter("filenumber") : "")) ? "" : "Dossiernummer, ";
		message += (client.setForename((req.getParameter("forename") != null) ? (String) req.getParameter("forename") : "")) ? "" : "Voornaam, ";
		message += (client.setSurname((req.getParameter("surname") != null) ? (String) req.getParameter("surname") : "")) ? "" : "Achternaam, ";
	
		String dateOfBirth = (req.getParameter("dateofbirth") != null) ? (String) req.getParameter("dateofbirth") : "";
		
		if (!dateOfBirth.equals("")) {
			String[] parts = dateOfBirth.split("-");
			String year = parts[0]; 
			String month = parts[1]; 
			String date = parts[2];
			client.setDateOfBirth(date, month, year);
		} else {
			message += "Geboortedatum, ";
		}
		
		message += (client.setNationality((req.getParameter("nationality") != null) ? (String) req.getParameter("nationality") : "")) ? "" : "Nationaliteit, ";
		message += (client.setStreet((req.getParameter("street") != null) ? (String) req.getParameter("street") : "")) ? "" : "Straat, ";
		message += (client.setHouseNumber((req.getParameter("streetnumber") != null) ? (String) req.getParameter("streetnumber") : "")) ? "" : "Huisnummer, ";
		message += (client.setPostcode((req.getParameter("postcode") != null) ? (String) req.getParameter("postcode") : "")) ? "" : "Postcode, ";
		message += (client.setCity((req.getParameter("city") != null) ? (String) req.getParameter("city") : "")) ? "" : "Stad, ";
		message += (client.setTelephoneNumber((req.getParameter("phonenumber") != null) ? (String) req.getParameter("phonenumber") : "")) ? "" : "Telefoonnummer, ";
		message += (client.setMobilePhoneNumber((req.getParameter("mobile") != null) ? (String) req.getParameter("mobile") : "")) ? "" : "Mobiel nummer, ";
		
		String email1 = (req.getParameter("email") != null) ? (String) req.getParameter("email") : "";
		String email2 = (req.getParameter("email_confirmation") != null) ? (String) req.getParameter("email_confirmation") : "";
		
		if (!email1.equals("") || !email2.equals("") || email1.equals(email2)) {
			message += (client.setEmail(email1)) ? "" : "Email, ";
		}
		message += (!message.equals("")) ? "niet correct ingevuld" : "";
		return message;
	}

	/**
	 * Method to summary a client.
	 * @param clientID the client id from the client to summary.
	 */
	private void summary(int clientID) {
		this.client = null;
		for (Client c : this.currentUser.getMyClients()) {
			if (clientID == c.getClient_id()) {
				this.client = c;
				break;
			}
		}
		req.setAttribute("client", client);
		reqDisp = req.getRequestDispatcher(PAGE_CLIENT_ADD_EDIT);
	}
	
	private void setMessage(String messageType, String message) {
		req.setAttribute("messageType", messageType);
		req.setAttribute("message", message);
	}
}