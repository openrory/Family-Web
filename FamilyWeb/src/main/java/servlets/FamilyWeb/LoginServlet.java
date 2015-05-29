package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if (controller.authentication(username, password)) {
			
			Object user = controller.getUser(username);
			req.getSession().setAttribute("user", user);
			
			if (controller.isAdministrator(user)) {
				reqDisp = req.getRequestDispatcher("/administrator/startscreen_administrator.html");
			} else {
				reqDisp = req.getRequestDispatcher("/zorgprofessional/startscreen_socialworker.html");
			}
		} else {
			req.setAttribute("message", "Gebruikersnaam of wachtwoord onjuist.");
			reqDisp = req.getRequestDispatcher("/login.html");
		}
		reqDisp.forward(req, resp);
	}
}
