package servletControllers.FamilyWeb;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Result;
import domain.FamilyWeb.User;

public class OverviewController {
	private static OverviewController oc;
	private DatabaseInterface db = null;

	private OverviewController() {
		this.db = new MySQLDao();
		oc = this;
	}

	public OverviewController(DatabaseInterface db) {
		this.db = db;
		oc = this;
	}

	public static OverviewController getInstance() {
		if (oc == null) {
			oc = new OverviewController();
		}
		return oc;
	}

	public JSONArray createJSONUsers() throws JSONException {
		JSONArray returns = new JSONArray();
		for (User u : db.getAllUsers()) {
			JSONObject userJSON = new JSONObject();
			userJSON.put("forename", u.getForename());
			userJSON.put("surname", u.getSurname());
			userJSON.put("username", u.getUsername());
			userJSON.put("dateOfBirth", u.getDateOfBirth());
			userJSON.put("isActive", u.isActive());
			userJSON.put("postcode", u.getPostcode());
			userJSON.put("street", u.getStreet());
			userJSON.put("houseNumber", u.getHouseNumber());
			userJSON.put("city", u.getCity());
			userJSON.put("nationality", u.getNationality());
			userJSON.put("telephoneNumber", u.getTelephoneNumber());
			userJSON.put("mobilePhoneNumber", u.getMobilePhoneNumber());
			userJSON.put("email", u.getEmail());
			userJSON.put("employeeNumber", u.getEmployeeNumber());
			returns.put(userJSON);
		}
		return returns;
	}

	public JSONArray createJSONClientsOfUser(User user) throws JSONException {
		JSONArray returns = new JSONArray();
		for (Client c : db.getAllClientsOfUser(user)) {
			JSONObject clientJSON = new JSONObject();
			clientJSON.put("forename", c.getForename());
			clientJSON.put("surname", c.getSurname());
			clientJSON.put("dateOfBirth", c.getDateOfBirth());
			clientJSON.put("postcode", c.getPostcode());
			clientJSON.put("street", c.getStreet());
			clientJSON.put("houseNumber", c.getHouseNumber());
			clientJSON.put("city", c.getCity());
			clientJSON.put("nationality", c.getNationality());
			clientJSON.put("telephoneNumber", c.getTelephoneNumber());
			clientJSON.put("mobilePhoneNumber", c.getMobilePhoneNumber());
			clientJSON.put("email", c.getEmail());
			clientJSON.put("fileNumber", c.getClient_id());
			returns.put(clientJSON);
		}
		return returns;
	}

	public JSONObject[] createJSONNetworks(Client client) throws JSONException {
		// client.getForename() + " " + client.getSurname()
		// fm.getForename() + " " + fm.getSurname()
		JSONArray netwerkNodes = new JSONArray();		
		JSONArray netwerkLinks = new JSONArray();
		JSONArray netwerks1 = new JSONArray();
		JSONArray netwerks2 = new JSONArray();
		
		JSONObject nodesPerson = new JSONObject();
		JSONObject linksPerson = new JSONObject();
		JSONObject netwerkPerson = new JSONObject();
		JSONObject netwerkLink = new JSONObject();
		JSONObject netwerkN = new JSONObject();
		JSONObject netwerkL = new JSONObject();
		ArrayList<Network> clientNetworks = db.getNetworks(
				client.getClient_id(), 0);
		for (Network n : clientNetworks) {
			JSONArray contacts = new JSONArray();
			JSONArray contactsLinks = new JSONArray();
			int i = 0;
			for (Contact c : n.getContacts()) {
				i++;
				JSONObject contact = new JSONObject();
				contact.put("name", c.getFullname());
				contact.put("group", c.getCategories().get(0).getGroup_id());
				contacts.put(contact);
				JSONObject link = createLink(c.getMyResults());
				link.put("group", c.getCategories().get(0).getGroup_id());
				link.put("source", i);
				link.put("target", 0);
				contactsLinks.put(link);
			}
			if (i != 0) {
				nodesPerson.put(n.getDateCreated().toString(), contacts);
				linksPerson.put(n.getDateCreated().toString(), contactsLinks);
			}
		}
		if (!clientNetworks.isEmpty()) {
			netwerkNodes.put(nodesPerson);
			netwerkLinks.put(linksPerson);
			netwerkPerson.put(client.getForename() + " " + client.getSurname(), netwerkNodes);
			netwerkLink.put(client.getForename() + " " + client.getSurname(), netwerkLinks);
			netwerks1.put(netwerkPerson);
			netwerks2.put(netwerkLink);
		}
		for (Familymember fm : client.getMyFamilymembers()) {
			nodesPerson = new JSONObject();
			linksPerson = new JSONObject();
			ArrayList<Network> familyNetworks = db.getNetworks(0,
					fm.getMember_id());
			for (Network n : familyNetworks) {
				JSONArray contacts = new JSONArray();
				JSONArray contactsLinks = new JSONArray();
				int i = 0;
				for (Contact c : n.getContacts()) {
					i++;
					JSONObject contact = new JSONObject();
					contact.put("name", c.getFullname());
					contact.put("group", c.getCategories().get(0).getGroup_id());
					contacts.put(contact);
					JSONObject link = createLink(c.getMyResults());
					link.put("group", c.getCategories().get(0).getGroup_id());
					link.put("source", i);
					link.put("target", 0);
					contactsLinks.put(link);
				}
				if (i != 0) {
					nodesPerson.put(n.getDateCreated().toString(), contacts);
					linksPerson.put(n.getDateCreated().toString(),
							contactsLinks);
				}
			}
			if (!familyNetworks.isEmpty()) {
				netwerkNodes.put(nodesPerson);
				netwerkLinks.put(linksPerson);
				netwerkPerson.put(fm.getForename() + " " + fm.getSurname(), netwerkNodes);
				netwerkLink.put(fm.getForename() + " " + fm.getSurname(), netwerkLinks);
				netwerks1.put(netwerkPerson);
				netwerks2.put(netwerkLink);
			}
		}
		netwerkN.put("allNetworks", netwerks1);
		netwerkL.put("allNetworks", netwerks2);
		JSONObject[] network = { netwerkN, netwerkL };
		return network;
	}

	private JSONObject createLink(ArrayList<Result> myResults)
			throws JSONException {
		JSONObject link = new JSONObject();
		for (Result r : myResults) {
			switch (r.getMyAnswer().getAnswer_id()) {
			case 1:
				link.put("type", 1);
				break;
			case 2:
				link.put("type", 2);
				break;
			case 3:
				link.put("type", 3);
				break;
			case 4:
				link.put("type", 4);
				break;
			case 5:
				link.put("type", 5);
				break;
			case 6:
				link.put("type", 6);
				break;
			case 7:
				link.put("strength", 1);
				break;
			case 8:
				link.put("strength", 2);
				break;
			case 9:
				link.put("strength", 3);
				break;
			case 10:
				link.put("strength", 4);
				break;
			case 11:
				link.put("strength", 5);
				break;
			case 12:
				link.put("distance", 5);
				break;
			case 13:
				link.put("distance", 4);
				break;
			case 14:
				link.put("distance", 3);
				break;
			case 15:
				link.put("distance", 2);
				break;
			case 16:
				link.put("distance", 1);
				break;
			}
		}
		return link;
	}
}