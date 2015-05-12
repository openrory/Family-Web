package databaseControllers.FamilyWeb;

import java.util.ArrayList;

import domain.FamilyWeb.User;

public interface DatabaseInterface {
	
//	LoginServlet
	public boolean authentication(String username, String password);
	public User getUser(String username);
	
//	SocialServlet
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public ArrayList<User> getAllSocialworkers();
	
}
