package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

/**
 * Servlet implementation class ContactServlet
 */
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		user = (User) req.getAttribute("user");
		RequestDispatcher reqDisp = null;
		boolean b = false;
		ArrayList<Contact> contacts = null;
		if(user != null){
			b = true;
		String[] contactgroups = { "household", "family", "friends",
				"colleagues", "neighbours", "acquaintance", "education",
				"club", "religion", "careinstitution", "youthcare",
				"bureauhalt", "justice" };
		contacts = new ArrayList<Contact>();
		for (String group : contactgroups) {
			int contactsInGroup = Integer.parseInt(req.getParameter(
					"counter" + group).trim());
			for (int i = 1; i <= contactsInGroup; i++) {
				String validate = req.getParameter(group + "validate" + i)
						.trim();
				if (validate.equals("true")) {
					String name = req.getParameter(group + "name" + i).trim();
					String commentary = "";
					String role = req.getParameter(group + "role" + i).trim();
					String ageS = req.getParameter(group + "age" + i).trim();
					int age = 0;
					try {
						age = Integer.parseInt(ageS);
					} catch (NumberFormatException e) {	}
					if (checkContact(name, role, age))
						contacts.add(new Contact(name, commentary, role, age,
								group));
					else
						b = false;
				}
			}
		}
		
		}if(b) {
			String surveyName = "test";
			Survey survey = user.getDbController().getSurvey(surveyName);
			if (!b && survey == null) {
				req.setAttribute("message", "Kon de vragenlijst niet inladen.");
				req.setAttribute("messageType", "error");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/new_network_contacts.jsp");
			} else if (b) {
				req.setAttribute("survey", survey);
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/new_network_questions.jsp");
			}
			req.setAttribute("contacts", contacts);
		}else{
			req.setAttribute("message", "Gegevens kloppen niet van één of meerdere contacten.");
			req.setAttribute("messageType", "warning");
			reqDisp = req
					.getRequestDispatcher("/socialworker/family/new_network_contacts.jsp");
		
		}			
		reqDisp.forward(req, resp);
	}
	private boolean checkContact(String name, String role, int age){
		boolean check = true;
		check &= (name != null);
		check &= (!name.equals(""));
		check &= (role != null);
		check &= (!role.equals(""));
		check &= (age <= 0);		
		return check;
	}
}
