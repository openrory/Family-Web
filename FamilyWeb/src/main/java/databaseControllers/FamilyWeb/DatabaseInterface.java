package databaseControllers.FamilyWeb;

import java.util.ArrayList;

import domain.FamilyWeb.Client;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

public interface DatabaseInterface {
	
//	LoginServlet
	public boolean authentication(String username, String password);
	public User getUser(String username);
	
//	SocialServlet
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public ArrayList<User> getAllSocialworkers();
	
	
	public boolean addClient(Client client, User user);
	public boolean updateClient(Client client);
	public Client getClient(int client_id);
	
	public ArrayList<Client> getAllClientsOfUser(User user);
	public ArrayList<Client> getAllClients();
	public boolean addFamilymember(Familymember famMember, Client client);
	public boolean updateFamilymember(Familymember famMember);
	public ArrayList<Familymember> getFamilymembersOfClient(Client client);
	
	public boolean addSurvey(Survey survey);
	public boolean updateSurvey(Survey survey);
	public Survey getSurvey(String surveyName);
	public ArrayList<String> getSurveyNames();
	
	public boolean addQuestion(Question question);
	public boolean updateQuestion(Question question);
	public Question getQuestion(int question_id);
	public ArrayList<User> getAllUsers();			
	
	public boolean addNetwork(Network network, int client_id, int familymember_id);
	public boolean updateNetwork(Network network);
	public ArrayList<Network> getNetworks(int client_id,int familymember_id);
}
