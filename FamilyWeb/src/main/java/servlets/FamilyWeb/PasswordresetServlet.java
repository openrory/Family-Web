package servlets.FamilyWeb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

@SuppressWarnings("serial")
public class PasswordresetServlet extends HttpServlet { 

	private final String PAGE_LOGIN = "/login.jsp";
	private final String PAGE_PASSWORD_RESET = "/password_reset.jsp";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		Object cUser = req.getSession().getAttribute("user");
		User currentUser = (cUser instanceof Administrator) ? (Administrator) cUser : (Socialworker) cUser;		
		
		if (req.getParameter("old_password") != null && req.getParameter("new_password") != null && req.getParameter("new_password_confirmation") != null) {
			
			String oldpassword = req.getParameter("old_password").trim();
			String newpassword = req.getParameter("new_password").trim();
			String newpasswordConfirm = req.getParameter("new_password_confirmation").trim();
			
			if (oldpassword.equals(currentUser.getPassword()) && newpassword.equals(newpasswordConfirm)) {
				currentUser.setPassword(newpassword);
				currentUser.setWwreset(false);
				currentUser.updateDB();
				
				req.getSession().invalidate();
				req.setAttribute("message", "Succesvol wachtwoord veranderd.");
				req.setAttribute("messageType", "succes");
				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
				
			// input not valid.
			} else {
				req.setAttribute("message", "Wachtwoord onjuist of wachtwoorden komen niet overeeen.");
				req.setAttribute("messageType", "warning");		
				req.getRequestDispatcher(PAGE_PASSWORD_RESET).forward(req, resp);
			}
		
		// input not valid.
		} else {
			req.setAttribute("message", "Alle velden dienen ingevuld te worden.");
			req.setAttribute("messageType", "warning");
			req.getRequestDispatcher(PAGE_PASSWORD_RESET).forward(req, resp);
		}
	}
}
