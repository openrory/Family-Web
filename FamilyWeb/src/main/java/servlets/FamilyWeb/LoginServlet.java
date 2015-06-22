package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.User;
import servletControllers.FamilyWeb.LoginController;
import servletControllers.FamilyWeb.OverviewController;

import org.json.JSONArray;
import org.json.JSONException;

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
						try {
							JSONArray usersJSON = OverviewController.getInstance().createJSONUsers();
							req.getSession().setAttribute("usersJSON", usersJSON);
							reqDisp = req.getRequestDispatcher(PAGE_STARTSCREEN_ADMINISTRATOR);
						} catch (JSONException e) {
							req.setAttribute("message", "Kon de gegevens niet goed inladen, probeer opnieuw in te loggen.");
							req.setAttribute("messageType", "error");
							reqDisp = req.getRequestDispatcher(PAGE_LOGIN);
						}						
					//check if active && password must reset first
					}else if (!controller.isAdministrator(user) && user.isActive() && !user.isWwreset()){						
						try {
							req.getSession().setAttribute("clientsJSON", OverviewController.getInstance().createJSONClientsOfUser(user));
							req.getSession().setAttribute("clients", user.getDbController().getAllClientsOfUser(user));
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
	
}
