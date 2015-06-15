package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.Client;
import domain.FamilyWeb.User;

/**
 * Servlet implementation class FamilyInformation
 */
public class FamilyInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = (User) req.getAttribute("user");
		RequestDispatcher reqDisp = null;
		if(user != null){
			int client_id = Integer.parseInt(req.getParameter("familyID"));
			Object attribute = req.getSession().getAttribute("clients");
			ArrayList<Client> clients = null;
			if(attribute instanceof ArrayList<?>)
				clients = ((ArrayList<Client>) attribute);
			Client client = null;
			if(clients != null && !clients.isEmpty()){
				for(Client c : clients){
					if(c.getClient_id() == client_id)
						client = c;
				}
			}
			if(client == null){
				req.setAttribute("message", "Kon de Client niet goed inladen.");
				reqDisp = req.getRequestDispatcher("/socialworker/client_overview.html");
			}else{
				ArrayList<String> surveys = user.getDbController().getSurveyNames();
				if(surveys == null || surveys.isEmpty()){
					req.setAttribute("message", "Kon geen vragenlijsten vinden.");
					reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.html");
				}else{
					req.getSession().setAttribute("client", client);
					req.getSession().setAttribute("surveys", surveys);
					reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.html");
				}
			}
		}else{
			req.setAttribute("message", "Er is iets onverwachts gelopen, probeer opnieuw in te loggen.");
			reqDisp = req.getRequestDispatcher("/login.jsp");
		}		
		reqDisp.forward(req, resp);
	}

}
