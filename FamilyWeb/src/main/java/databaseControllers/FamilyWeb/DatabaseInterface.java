package databaseControllers.FamilyWeb;

import java.util.ArrayList;

import domain.FamilyWeb.Client;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface DatabaseInterface.
 */
public interface DatabaseInterface {
	
/**
 * Authentication.
 *
 * @param username the username
 * @param password the password
 * @return true, if successful
 */
	public boolean authentication(String username, String password);
	
	/**
	 * Gets the user.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User getUser(String username);
	
/**
 * Adds the user.
 *
 * @param user the user
 * @return true, if successful
 */
	public boolean addUser(User user);
	
	/**
	 * Update an user.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean updateUser(User user);
	
	/**
	 * Gets the all socialworkers.
	 *
	 * @return the all socialworkers
	 */
	public ArrayList<User> getAllSocialworkers();
	
	
	/**
	 * Adds the client to the database.
	 *
	 * @param client the client
	 * @param userID the user id of an Socialworker
	 * @return true, if successful
	 */
	public boolean addClient(Client client, int userID);
	
	/**
	 * Update client in the database.
	 *
	 * @param client the client
	 * @param userID the user id
	 * @return true, if successful
	 */
	public boolean updateClient(Client client, int userID);
	
	/**
	 * Gets the client.
	 *
	 * @param client_id the client_id
	 * @return the client
	 */
	public Client getClient(int client_id);
	
	/**
	 * Gets the all clients of user.
	 *
	 * @param user the user
	 * @return the all clients of user
	 */
	public ArrayList<Client> getAllClientsOfUser(User user);
	
	/**
	 * Gets the all clients.
	 *
	 * @return the all clients
	 */
	public ArrayList<Client> getAllClients();
	
	/**
	 * Adds the familymember.
	 *
	 * @param famMember the fam member
	 * @param client the client
	 * @return true, if successful
	 */
	public boolean addFamilymember(Familymember famMember, Client client);
	
	/**
	 * Update familymember.
	 *
	 * @param famMember the fam member
	 * @return true, if successful
	 */
	public boolean updateFamilymember(Familymember famMember);
	
	/**
	 * Gets the familymembers of client.
	 *
	 * @param client the client
	 * @return the familymembers of client
	 */
	public ArrayList<Familymember> getFamilymembersOfClient(Client client);
	
	/**
	 * Adds the survey.
	 *
	 * @param survey the survey
	 * @return true, if successful
	 */
	public boolean addSurvey(Survey survey);
	
	/**
	 * Update survey.
	 *
	 * @param survey the survey
	 * @return true, if successful
	 */
	public boolean updateSurvey(Survey survey);
	
	/**
	 * Gets the survey.
	 *
	 * @param surveyName the survey name
	 * @return the survey
	 */
	public Survey getSurvey(String surveyName);
	
	/**
	 * Gets the survey names.
	 *
	 * @return the survey names
	 */
	public ArrayList<String> getSurveyNames();
	
	/**
	 * Adds the question.
	 *
	 * @param question the question
	 * @return true, if successful
	 */
	public boolean addQuestion(Question question);
	
	/**
	 * Update question.
	 *
	 * @param question the question
	 * @return true, if successful
	 */
	public boolean updateQuestion(Question question);
	
	/**
	 * Gets the question.
	 *
	 * @param question_id the question_id
	 * @return the question
	 */
	public Question getQuestion(int question_id);
	
	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public ArrayList<User> getAllUsers();			
	
	/**
	 * Adds the network.
	 *
	 * @param network the network
	 * @param client_id the client_id
	 * @param familymember_id the familymember_id
	 * @return true, if successful
	 */
	public boolean addNetwork(Network network, int client_id, int familymember_id);
	
	/**
	 * Gets the networks.
	 *
	 * @param client_id the client_id
	 * @param familymember_id the familymember_id
	 * @return the networks
	 */
	public ArrayList<Network> getNetworks(int client_id,int familymember_id);
}
