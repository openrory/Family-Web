package servlets.FamilyWeb;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import servletControllers.FamilyWeb.OverviewController;
import util.FamilyWeb.Validation;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Familymember;

/**
 * Servlet implementation class FamilyMemberServlet.
 */
public class FamilyMemberServlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Do post.
	 *
	 * @param req
	 *            the request
	 * @param resp
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher reqDisp = null;
		// get the status from the option
		String option = (req.getParameter("option") != null) ? (String) req
				.getParameter("option") : "";
		// get the client from the session
		Client client = (Client) req.getSession().getAttribute("client");
		// set date format
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		// if status was overview
		if (option.equals("summary")) {
			Familymember familymember = null;			
			String button = req.getParameter("new");
			// check if the button for an new familymember was pushed
			if(button == null){
				// if not, get the familymeber that wants to edit
				int id = Integer.valueOf(req.getParameter("currentID"));
				if (id != 0) {
					// check for each member if it is equal to the id
					for (Familymember fm : client.getMyFamilymembers()) {
						if (id == fm.getMember_id()) {
							familymember = fm;
							break;
						}
					}				
				}
			}			
			// send the user to the add or edit page and give family with it, for add this value is null otherwise the familymember is the member that was selected
			req.setAttribute("familymember", familymember);
			reqDisp = req
					.getRequestDispatcher("/socialworker/family/add_edit_family_member.jsp");
		} 
		// if create was selected, validate the input
		else if (option.equals("create")) {
			// valdate forename
			String forename = Validation.getInstance().validateForename(
					req.getParameter("forename"));
			// validat surname
			String surname = Validation.getInstance().validateSurname(
					req.getParameter("surname"));
			// if validation fail
			if (forename == null || surname == null) {
				// set error message and return user 
				req.setAttribute("message",
						"Voornaam en/of achternaam zijn onjuist.");
				req.setAttribute("messageType", "error");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/add_edit_family_member.jsp");
			} else {
				String dateOfBirthString = req.getParameter("dateofbirth");
				Date dateOfBirth = null;
				try {
					java.util.Date date = sdf1.parse(dateOfBirthString);
					dateOfBirth = new java.sql.Date(date.getTime());
				} catch (ParseException e) {
					dateOfBirth = null;
				}
				String postcode = req.getParameter("postcode");
				String street = req.getParameter("street");
				String houseNumber = req.getParameter("streetnumber");
				String city = req.getParameter("city");
				String nationality = req.getParameter("nationality");
				String telephoneNumber = req.getParameter("phonenumber");
				String mobilePhoneNumber = req.getParameter("mobile");
				String email = req.getParameter("email");

				Familymember fm = new Familymember(forename, surname,
						dateOfBirth, postcode, street, houseNumber, city,
						nationality, telephoneNumber, mobilePhoneNumber, email);
				OverviewController.getInstance().getDb()
						.addFamilymember(fm, client);
				ArrayList<Familymember> members= client.getMyFamilymembers();
				members.add(fm);
				client.setMyFamilymembers(members);
				req.getSession().setAttribute("client", client);
				req.setAttribute("message", "Gezinslid Aangemaakt.");
				req.setAttribute("messageType", "succes");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
			}
		} else if (option.equals("update")) {
			int id = Integer.valueOf(req.getParameter("familymemberID"));
			String forename = Validation.getInstance().validateForename(
					req.getParameter("forename"));
			String surname = Validation.getInstance().validateSurname(
					req.getParameter("surname"));
			if (forename == null || surname == null) {
				req.setAttribute("message",
						"Voornaam en/of achternaam zijn onjuist.");
				req.setAttribute("messageType", "error");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/add_edit_family_member.jsp");
			} else {
				String dateOfBirthString = req.getParameter("dateofbirth");
				Date dateOfBirth = null;
				try {
					java.util.Date date = sdf1.parse(dateOfBirthString);
					dateOfBirth = new java.sql.Date(date.getTime());
				} catch (ParseException e) {
					dateOfBirth = null;
				}
				String postcode = req.getParameter("postcode");
				String street = req.getParameter("street");
				String houseNumber = req.getParameter("streetnumber");
				String city = req.getParameter("city");
				String nationality = req.getParameter("nationality");
				String telephoneNumber = req.getParameter("phonenumber");
				String mobilePhoneNumber = req.getParameter("mobile");
				String email = req.getParameter("email");
				Familymember fm = new Familymember(forename, surname,
						dateOfBirth, postcode, street, houseNumber, city,
						nationality, telephoneNumber, mobilePhoneNumber, email);
				fm.setMember_id(id);
				OverviewController.getInstance().getDb().updateFamilymember(fm);
				ArrayList<Familymember> members= new ArrayList<Familymember>();
				for(Familymember f : client.getMyFamilymembers()){
					if(f.getMember_id() == fm.getMember_id())
						members.add(fm);
					else
						members.add(f);
				}
				client.setMyFamilymembers(members);
				req.getSession().setAttribute("client", client);
				req.setAttribute("message", "Gezinslid opgeslagen.");
				req.setAttribute("messageType", "succes");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
			}
		} else {
			req.setAttribute("message",
					"Onverwachte fout opgetreden, pagina niet gevonden.");
			req.setAttribute("messageType", "error");
			reqDisp = req
					.getRequestDispatcher("/socialworker/family/family_members_overview.jsp");
		}
		try {
			req.getSession().setAttribute(
					"familyJSON",
					OverviewController.getInstance()
							.refreshFamilymember(client));
		} catch (JSONException e) {
			req.setAttribute(
					"message",
					"Kan de gegevens van "
							+ client.getForename()
							+ " "
							+ client.getSurname()
							+ " niet goed ophalen, log opnieuw in en probeer het opnieuw.");
			req.setAttribute("messageType", "error");
			reqDisp = req
					.getRequestDispatcher("/socialworker/startscreen_socialworker.jsp");
		}
		JSONObject[] networks;
		try {
			networks = OverviewController.getInstance().createJSONNetworks(client);
			req.getSession().setAttribute("familyJSON", OverviewController.getInstance().refreshFamilymember(client));
			req.getSession().setAttribute("nodesNetwork", networks[0]);
			req.getSession().setAttribute("linksNetwork", networks[1]);
		} catch (JSONException e) {	}		
		reqDisp.forward(req, resp);
	}
}
