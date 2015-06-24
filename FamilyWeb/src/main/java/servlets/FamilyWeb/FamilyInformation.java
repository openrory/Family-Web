package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import servletControllers.FamilyWeb.OverviewController;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = (User) req.getSession().getAttribute("user");
		RequestDispatcher reqDisp = null;		
		if(user != null){
			int client_id = Integer.parseInt((req.getParameter("familyID").trim()));
			ArrayList<Client> clients = (ArrayList<Client>) req.getSession().getAttribute("clients");			
			Client client = null;
			if(clients != null && !clients.isEmpty()){
				for(Client c : clients){
					if(c.getClient_id() == client_id)
						client = c;
				}
			}
			if(client == null){
				req.setAttribute("messageType", "error");
				req.setAttribute("message", "Kon de Client niet goed inladen.");
				reqDisp = req.getRequestDispatcher("/socialworker/client_overview.jsp");
			}else{
				ArrayList<String> surveys = user.getDbController().getSurveyNames();
				if(surveys == null || surveys.isEmpty()){
					req.setAttribute("message", "Kon geen vragenlijsten vinden.");
					req.setAttribute("messageType", "error");
					reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
				}else{
					req.getSession().setAttribute("client", client);					
					req.getSession().setAttribute("surveys", surveys);	
					try {
						JSONObject[] networks = OverviewController.getInstance().createJSONNetworks(client);
						req.getSession().setAttribute("familyJSON", OverviewController.getInstance().refreshFamilymember(client));
						req.getSession().setAttribute("nodesNetwork", networks[0]);
						req.getSession().setAttribute("linksNetwork", networks[1]);
						reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
					} catch (JSONException e) {
						req.setAttribute("message", "Kan degegevens van "+client.getForename()+" "+client.getSurname()+" niet goed ophalen, log opnieuw in en probeer het opnieuw.");
						req.setAttribute("messageType", "error");
						reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");						
					}				
					
				}
			}
		}else{
			req.setAttribute("message", "Er is iets onverwachts gelopen, probeer opnieuw in te loggen.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/login.jsp");
		}		
		reqDisp.forward(req, resp);
	}

}
