package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.Client;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {

private RequestDispatcher reqDisp = null;
private HttpServletRequest req = null;

	private void create() {
		
	}

	private void update() {
		
	}
	
	private void overview() {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.req = req;
		String message = "";
		Client client = new Client();
		
		// controle wat te doen
		
		//this.create(message); 
		
		reqDisp.forward(req, resp);
		
		
//		String message = "";
//		User newUser = new Socialworker();
//	
//		newUser.setDbController((DatabaseInterface) this.getServletContext().getAttribute("dbController"));
//		newUser.setActive(true);
//		
//		message += (newUser.setUsername((String) req.getAttribute("username"))) ? "" : "Username not valid. ";
//		message += (newUser.setForename((String) req.getAttribute("forename"))) ? "" : "Forename not valid. ";
//		message += (newUser.setSurname((String) req.getAttribute("surname"))) ? "" : "Surname not valid. ";
//		message += (newUser.setPostcode((String) req.getAttribute("postcode"))) ? "" : "Postcode not valid. ";
//		message += (newUser.setStreet((String) req.getAttribute("street"))) ? "" : "Street not valid. ";
//		message += (newUser.setHouseNumber((String) req.getAttribute("housenumber"))) ? "" : "Housenumber not valid. ";
//		message += (newUser.setCity((String) req.getAttribute("city"))) ? "" : "City not valid. ";
//		message += (newUser.setNationality((String) req.getAttribute("nationality"))) ? "" : "Nationality not valid. ";
//		message += (newUser.setTelephoneNumber((String) req.getAttribute("telephonenumber"))) ? "" : "Telephonenumber not valid. ";
//		message += (newUser.setMobilePhoneNumber((String) req.getAttribute("mobilephonenumber"))) ? "" : "Mobilephonenumber not valid. ";
//		message += (newUser.setEmployeeNumber((String) req.getAttribute("employeenumber"))) ? "" : "Employeenumber not valid. ";
//		
//		String email1 = (String) req.getAttribute("email1");
//		String email2 = (String) req.getAttribute("email2");
//		message += (email1.equals(email2) && newUser.setEmail(email1)) ? "" : "Email not valid. ";
//		
//		String date = (String) req.getAttribute("date");
//		String month = (String) req.getAttribute("month");
//		String year = (String) req.getAttribute("year");
//		message += (newUser.setDateOfBirth(date, month, year)) ? "" : "Date of birth not valid. ";
//		
//		if (message.equals("")) {
//			newUser.addDB();
//			reqDisp = req.getRequestDispatcher("/administrator/socialworker_overview.html");
//		} else {
//			req.setAttribute("message", "Error occurred: " + message);
//			reqDisp = req.getRequestDispatcher("/administrator/add_socialworker.html");
//		}
//		
//		reqDisp.forward(req, resp);
	}
	
}
