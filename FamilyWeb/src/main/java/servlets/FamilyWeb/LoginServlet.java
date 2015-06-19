package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.User;
import servletControllers.FamilyWeb.LoginController;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private LoginController controller = LoginController.getInstance();
	private RequestDispatcher reqDisp = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		//Get username and password from form
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		
		//check if username/password exists in db
		if (controller.authentication(username, password)) {
			
			// get the user account
			User user = (User) controller.getUser(username);
			req.getSession().setAttribute("user", user);
			
			//check if administrator
			if (controller.isAdministrator(user)) {
				
				// dispatch to admin screen
				reqDisp = req.getRequestDispatcher("/administrator/startscreen_administrator.jsp");
			} else {
				
				// get all clients from socialworker and dispatch to socialworker screen
				req.getSession().setAttribute("clients", user.getDbController().getAllClientsOfUser(user));
				reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
			}
			
		} 
		// not valid username/password
		else {
			//set error message and dispatch to login page
			req.setAttribute("message", "Gebruikersnaam of wachtwoord onjuist.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/login.jsp");
		}
		//dispatch to the next page
		reqDisp.forward(req, resp);
	}
}
