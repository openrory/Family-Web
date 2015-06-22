package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.User;
import servletControllers.FamilyWeb.LoginController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import domain.FamilyWeb.Client;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private LoginController controller = LoginController.getInstance();
	private RequestDispatcher reqDisp = null;
	
	private final String PAGE_STARTSCREEN_ADMINISTRATOR = "/administrator/startscreen_administrator.jsp";
	private final String PAGE_STARTSCREEN_SOCIALWORKER = "/socialworker/startscreen_socialworker.jsp";
	private final String PAGE_PASSWORD_RESET = "/password_reset.jsp";
	private final String PAGE_LOGIN = "/login.jsp";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		if (req.getParameter("username") == null && req.getParameter("username") == null) {
			req.setAttribute("message", "Gebruikersnaam of wachtwoord niet ingevuld.");
			req.setAttribute("messageType", "warning");
			reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
		} else {
			
			//Get username and password from form
			String username = req.getParameter("username").trim();
			String password = req.getParameter("password").trim();
			
			if (!username.equals("") && !password.equals("")) {
				
				//check if username/password exists in db
				if (controller.authentication(username, password)) {
					
					// get the user account
					User user = (User) controller.getUser(username);
					req.getSession().setAttribute("user", user);
					
					//check if administrator
					if (controller.isAdministrator(user) && user.isActive() && !user.isWwreset()) {
						ArrayList<User> users = user.getDbController().getAllUsers();
						
						try {
							JSONArray usersJSON = createJSONUsers(users);
							req.getSession().setAttribute("usersJSON", usersJSON);
							reqDisp = req.getRequestDispatcher(PAGE_STARTSCREEN_ADMINISTRATOR);
						} catch (JSONException e) {
							req.setAttribute("message", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
							req.setAttribute("messageType", "error");
							reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
						}						
					//check if active && password must reset first
					}else if (!controller.isAdministrator(user) && user.isActive() && !user.isWwreset()){
						ArrayList<Client> clients = user.getDbController().getAllClientsOfUser(user);				
						for(Client c : clients)
							System.out.println(c);
						try {
							JSONArray clientsJSON = createJSON(clients);
							req.getSession().setAttribute("clientsJSON", clientsJSON);
							req.getSession().setAttribute("clients", clients);
							reqDisp = req.getRequestDispatcher(PAGE_STARTSCREEN_SOCIALWORKER);
						} catch (JSONException e) {
							req.setAttribute("message", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
							req.setAttribute("messageType", "error");
							reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
						}						
					//check if password must reset first
					} else if (user.isActive() && user.isWwreset()) {
						req.setAttribute("message", "Wachtwoord reset aangevraagd, verander het wachtwoord.");
						req.setAttribute("messageType", "warning");
						reqDisp = req.getRequestDispatcher(PAGE_PASSWORD_RESET);
					//not active user
					} else {
						req.setAttribute("message", "Je kunt niet inloggen, je account is niet actief.");
						req.setAttribute("messageType", "warning");
						reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
					}
				} 
				// not valid username/password
				else {
					req.setAttribute("message", "Gebruikersnaam of wachtwoord onjuist.");
					req.setAttribute("messageType", "error");
					reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
				}
			// not valid username/password
			} else {
				req.setAttribute("message", "Gebruikersnaam of wachtwoord niet ingevuld.");
				req.setAttribute("messageType", "warning");
				reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
			}
		}
		reqDisp.forward(req, resp);
	}
	private JSONArray createJSONUsers(ArrayList<User> users) throws JSONException {
		JSONArray returns = new JSONArray();
		for(User u : users){
			JSONObject userJSON = new JSONObject();
			userJSON.put("forename", u.getForename());
			userJSON.put("surname", u.getSurname());
			userJSON.put("username", u.getUsername());
			userJSON.put("dateOfBirth", u.getDateOfBirth());
            userJSON.put("isActive", u.isActive());
            userJSON.put("postcode", u.getPostcode());
			userJSON.put("street", u.getStreet());
			userJSON.put("houseNumber", u.getHouseNumber());
			userJSON.put("city", u.getCity());
			userJSON.put("nationality", u.getNationality());
			userJSON.put("telephoneNumber", u.getTelephoneNumber());
			userJSON.put("mobilePhoneNumber", u.getMobilePhoneNumber());
			userJSON.put("email", u.getEmail());
			userJSON.put("employeeNumber", u.getEmployeeNumber());
			returns.put(userJSON);
		}
		return returns;
	}
	private JSONArray createJSON(ArrayList<Client> clients) throws JSONException {
				JSONArray returns = new JSONArray();
				for(Client c : clients){			
					JSONObject clientJSON = new JSONObject();
					clientJSON.put("forename", c.getForename());
					clientJSON.put("surname", c.getSurname());
					clientJSON.put("dateOfBirth", c.getDateOfBirth());
					clientJSON.put("postcode", c.getPostcode());
					clientJSON.put("street", c.getStreet());
					clientJSON.put("houseNumber", c.getHouseNumber());
					clientJSON.put("city", c.getCity());
					clientJSON.put("nationality", c.getNationality());
					clientJSON.put("telephoneNumber", c.getTelephoneNumber());
					clientJSON.put("mobilePhoneNumber", c.getMobilePhoneNumber());
					clientJSON.put("email", c.getEmail());
					clientJSON.put("fileNumber", c.getClient_id());            
					returns.put(clientJSON);
				}		
				return returns;
			}
}
