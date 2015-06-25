package servlets.FamilyWeb;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servletControllers.FamilyWeb.OverviewController;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Familymember;

/**
 * Servlet implementation class FamilyMemberServlet.
 */
public class FamilyMemberServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;   

	/**
	 * Do get.
	 *
	 * @param req the request
	 * @param resp the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }

	/**
	 * Do post.
	 *
	 * @param req the request
	 * @param resp the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher reqDisp = null;
		String option = (req.getParameter("option") != null) ? (String) req.getParameter("option") : "";
		Client client = (Client) req.getSession().getAttribute("client");
		
		if(option.equals("summary")){
			Familymember familymember = null;
			int id = Integer.valueOf(req.getParameter("currentID"));
			if(id == 0){
				req.setAttribute("familymember", null);
			}else{
				for(Familymember fm : client.getMyFamilymembers()){
					if(id == fm.getMember_id()){
						familymember = fm;
						break;
					}
				}
				req.setAttribute("familymember", familymember);
			}
			reqDisp = req.getRequestDispatcher("/socialworker/family/add_edit_family_member.jsp");
		}else if(option.equals("Aanmaken")){
			String forename = req.getParameter("forename");
			String surname = req.getParameter("surname");
			String dateOfBirthString = req.getParameter("dateofbirth");
			Date dateOfBirth = null;
			String postcode = req.getParameter("postcode");
			String street = req.getParameter("street");
			String houseNumber = req.getParameter("streetnumber");
			String city = req.getParameter("city");
			String nationality = req.getParameter("nationality");
			String telephoneNumber = req.getParameter("phonenumber");
			String mobilePhoneNumber = req.getParameter("mobile");
			String email = req.getParameter("email");
			
			Familymember fm = new Familymember(forename, surname, dateOfBirth, postcode, street, houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber, email);
			OverviewController.getInstance().getDb().addFamilymember(fm, client);
			req.setAttribute("message", "Gezinslid Aangemaakt.");
			req.setAttribute("messageType", "succes");
			reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
		}else if(option.equals("Opslaan")){
			int id = Integer.valueOf(req.getParameter("familymemberID"));
			String forename = req.getParameter("forename");
			String surname = req.getParameter("surname");
			String dateOfBirthString = req.getParameter("dateofbirth");
			Date dateOfBirth = null;
			String postcode = req.getParameter("postcode");
			String street = req.getParameter("street");
			String houseNumber = req.getParameter("streetnumber");
			String city = req.getParameter("city");
			String nationality = req.getParameter("nationality");
			String telephoneNumber = req.getParameter("phonenumber");
			String mobilePhoneNumber = req.getParameter("mobile");
			String email = req.getParameter("email");
			
			Familymember fm = new Familymember(forename, surname, dateOfBirth, postcode, street, houseNumber, city, nationality, telephoneNumber, mobilePhoneNumber, email);
			fm.setMember_id(id);
			OverviewController.getInstance().getDb().updateFamilymember(fm);
			req.setAttribute("message", "Gezinslid opgeslagen.");
			req.setAttribute("messageType", "succes");
			reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
		}else{			
			req.setAttribute("message", "Onverwachte fout opgetreden, pagina niet gevonden.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
		}
		try {
			req.getSession().setAttribute("familyJSON", OverviewController.getInstance().refreshFamilymember(client));
		} catch (JSONException e) {
			req.setAttribute("message", "Kan degegevens van "+client.getForename()+" "+client.getSurname()+" niet goed ophalen, log opnieuw in en probeer het opnieuw.");
			req.setAttribute("messageType", "error");
			reqDisp = req.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
		}
		reqDisp.forward(req, resp);
	}
}
