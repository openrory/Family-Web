package servlets.FamilyWeb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.FamilyWeb.Client;
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
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		user = (User) req.getSession().getAttribute("user");
		RequestDispatcher reqDisp = null;
		boolean b = false;
		ArrayList<Contact> contacts = null;
		if (user != null) {
			b = true;
			String[] contactgroups = { "household", "family", "friends",
					"colleagues", "neighbours", "acquaintance", "education",
					"club", "religion", "careinstitution", "youthcare",
					"bureauhalt", "justice" };
			contacts = new ArrayList<Contact>();
			int j =0;
			int id = 1;
			for (String group : contactgroups) {
				j++;
				int contactsInGroup = Integer.valueOf(req.getParameter(
						"counter" + group));
				for (int i = 1; i <= contactsInGroup; i++) {
					String validate = req.getParameter(group + "validate" + i)
							.trim();
					if (validate.equals("true")) {
						String name = req.getParameter(group + "name" + i)
								.trim();
						String commentary = "";
						String com = req.getParameter(group + "comment" + i).toString();
						if(com != null && !com.trim().equals(""))
							commentary = com.trim();
						String role = req.getParameter(group + "role" + i)
								.trim();
						String ageS = req.getParameter(group + "age" + i)
								.trim();
						int age = 0;
						try {
							age = Integer.valueOf(ageS);
						} catch (NumberFormatException e) {
						}
						if (checkContact(name, role, age)){
							Contact c = new Contact(name, commentary, role,
									age, group,j);
							c.setContact_id(id);
							id++;
							contacts.add(c);							
						}else
							b = false;
					}
				}
			}
		}if(contacts.isEmpty()){
			b = false;
		}
		if (b) {
			String interviewee = req.getParameter("interviewee");
			String[] parts = interviewee.split(":");
			String nameInterviewee = parts[0];
			int IDInterviewee = Integer.parseInt(parts[1]);
			Client client = (Client) req.getSession().getAttribute("client");
			if (client.getClient_id() == IDInterviewee
					&& client.getForename().equals(nameInterviewee)) {
				req.getSession().setAttribute("intervieweeC", IDInterviewee);
				req.getSession().setAttribute("intervieweeF", 0);
			} else {
				req.getSession().setAttribute("intervieweeF", IDInterviewee);
				req.getSession().setAttribute("intervieweeC", 0);
			}
			String surveyName = req.getParameter("survey");
			Survey survey = user.getDbController().getSurvey(surveyName);
			if (!b && survey == null) {
				req.setAttribute("message", "Kon de vragenlijst niet inladen.");
				req.setAttribute("messageType", "error");
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/new_network_contacts.jsp");
			} else if (b) {
				req.getSession().setAttribute("survey", survey);				
				reqDisp = req
						.getRequestDispatcher("/socialworker/family/new_network_questions.jsp");
			}
			req.getSession().setAttribute("contacts", contacts);
		} else if(contacts.isEmpty()){
			req.setAttribute("message",
					"Voer minimaal één contact in voor de vragenlijst.");
			req.setAttribute("messageType", "warning");
			reqDisp = req
					.getRequestDispatcher("/socialworker/family/new_network_contacts.jsp");
		}else {
			req.setAttribute("message",
					"Gegevens kloppen niet van één of meerdere contacten.");
			req.setAttribute("messageType", "warning");
			reqDisp = req
					.getRequestDispatcher("/socialworker/family/new_network_contacts.jsp");

		}
		reqDisp.forward(req, resp);
	}

	private boolean checkContact(String name, String role, int age) {
		boolean check = true;
		check &= (name != null);
		check &= (!name.equals(""));
		check &= (role != null);
		check &= (!role.equals(""));
		check &= !(age <= 0);
		return check;
	}
}
