package servletControllers.FamilyWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Client;
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

	public JSONArray createJSONUsers()
			throws JSONException {
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

	public JSONArray createJSONClientsOfUser(User user)
			throws JSONException {
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
}
