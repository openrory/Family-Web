package servletControllers.FamilyWeb;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import domain.FamilyWeb.Administrator;

public class LoginController {

	private static LoginController lc;
	private DatabaseInterface db = null;
	
	private LoginController(){
		this.db = new MySQLDao();
		lc = this;
	}
	
	public LoginController(DatabaseInterface db) {
		this.db = db;
		lc = this;
	}
	
	public static LoginController getInstance() {
		if(lc == null){
			lc = new LoginController();
		}
		return lc;
	}
	
	public boolean authentication(String username, String password) {
		return db.authentication(username, password);
	}
	
	public boolean isAdministrator(Object userObject) {
		return (userObject instanceof Administrator) ? true : false; 
	}
	
	public Object getUser(String username) {
		return db.getUser(username);
	}
	
}
