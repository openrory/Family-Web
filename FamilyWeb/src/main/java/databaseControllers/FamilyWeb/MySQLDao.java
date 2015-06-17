package databaseControllers.FamilyWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Answer;
import domain.FamilyWeb.Client;
import domain.FamilyWeb.Contact;
import domain.FamilyWeb.Familymember;
import domain.FamilyWeb.Network;
import domain.FamilyWeb.Question;
import domain.FamilyWeb.Result;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.Survey;
import domain.FamilyWeb.User;

public class MySQLDao implements DatabaseInterface {
	private String username = "root";
	private String passwd = "wachtwoord";
	private String dbLocation = "jdbc:mysql://localhost/familyweb";

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbLocation, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to connect to database");
		}
		return conn;
	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public boolean addUser(User user) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("insert into users(username,password,forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,usertype,email,isactive,employeenumber,wwreset) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getForename());
			pStmt.setString(4, user.getSurname());
			pStmt.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
			pStmt.setString(6, user.getPostcode());
			pStmt.setString(7, user.getStreet());
			pStmt.setString(8, user.getHouseNumber());
			pStmt.setString(9, user.getCity());
			pStmt.setString(10, user.getNationality());
			pStmt.setString(11, user.getTelephoneNumber());
			pStmt.setString(12, user.getMobilePhoneNumber());
			pStmt.setString(13,
					(user instanceof Administrator) ? "Administrator"
							: "Socialworker");
			pStmt.setString(14, user.getEmail());
			pStmt.setString(15, user.isActive() ? "Y" : "N");
			pStmt.setString(16, user.getEmployeeNumber());
			pStmt.setString(17, "Y");
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	public boolean checkUsername(String username){
		boolean b = true;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next())
				b = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}
	public User getUser(String username) {
		User user = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				if (rSet.getString("usertype").equals("Administrator")) {
					user = new Administrator(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"),
							new ArrayList<User>());
				} else {
					user = new Socialworker(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"));
				}
				user.setDbController(this);
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		if (user instanceof Administrator) {
			Administrator admin = (Administrator) user;
			admin.setUsers(getAllSocialworkers());
		}
		return user;
	}

	public boolean updateUser(User user) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("update users set password=?,forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,usertype=?,email=?,isactive=?,employeenumber=?, wwreset=? where username=?");
			pStmt.setString(1, user.getPassword());
			pStmt.setString(2, user.getForename());
			pStmt.setString(3, user.getSurname());
			pStmt.setDate(4, new java.sql.Date(user.getDateOfBirth().getTime()));
			pStmt.setString(5, user.getPostcode());
			pStmt.setString(6, user.getStreet());
			pStmt.setString(7, user.getHouseNumber());
			pStmt.setString(8, user.getCity());
			pStmt.setString(9, user.getNationality());
			pStmt.setString(10, user.getTelephoneNumber());
			pStmt.setString(11, user.getMobilePhoneNumber());
			pStmt.setString(12,
					(user instanceof Administrator) ? "Administrator"
							: "Socialworker");
			pStmt.setString(13, user.getEmail());
			pStmt.setString(14, user.isActive() ? "Y" : "N");
			pStmt.setString(15, user.getEmployeeNumber());
			pStmt.setString(16, user.isWwreset() ? "Y" : "N");
			pStmt.setString(17, user.getUsername());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	public boolean authentication(String username, String password) {
		boolean auth = false;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select username, password from users where username=? AND password=?");
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				auth = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return auth;
	}

	public ArrayList<User> getAllSocialworkers() {
		Connection conn = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users where usertype=?");
			pStmt.setString(1, "Socialworker");
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				User user = new Socialworker(rSet.getString("username"),
						rSet.getString("password"), rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("isActive")
								.equals("Y"), rSet.getString("employeeNumber"));
				user.setDbController(this);
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return users;
	}

	public boolean addClient(Client client, User user) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("insert into clients(forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,email,filenumber,dateCreated, user_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pStmt.setString(3, client.getForename());
			pStmt.setString(4, client.getSurname());
			pStmt.setDate(5, new java.sql.Date(client.getDateOfBirth()
					.getTime()));
			pStmt.setString(6, client.getPostcode());
			pStmt.setString(7, client.getStreet());
			pStmt.setString(8, client.getHouseNumber());
			pStmt.setString(9, client.getCity());
			pStmt.setString(10, client.getNationality());
			pStmt.setString(12, client.getTelephoneNumber());
			pStmt.setString(13, client.getMobilePhoneNumber());
			pStmt.setString(15, client.getEmail());
			pStmt.setString(16, client.getFileNumber());
			pStmt.setDate(17, new java.sql.Date(client.getDateCreated()
					.getTime()));
			pStmt.setInt(18, user.getUser_id());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for (Familymember f : client.getMyFamilymembers())
			b &= this.addFamilymember(f, client);
		return b;
	}

	public boolean updateClient(Client client) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("update clients set forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,email=?, filenumber=?,dateCreated=? where member_id=?");
			pStmt.setString(1, client.getForename());
			pStmt.setString(2, client.getSurname());
			pStmt.setDate(3, new java.sql.Date(client.getDateOfBirth()
					.getTime()));
			pStmt.setString(4, client.getPostcode());
			pStmt.setString(5, client.getStreet());
			pStmt.setString(6, client.getHouseNumber());
			pStmt.setString(7, client.getCity());
			pStmt.setString(8, client.getNationality());
			pStmt.setString(9, client.getTelephoneNumber());
			pStmt.setString(10, client.getMobilePhoneNumber());
			pStmt.setString(11, client.getEmail());
			pStmt.setString(12, client.getFileNumber());
			pStmt.setDate(13, new java.sql.Date(client.getDateCreated()
					.getTime()));
			pStmt.setInt(14, client.getClient_id());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for (Familymember f : client.getMyFamilymembers())
			this.updateFamilymember(f);
		return b;
	}

	public ArrayList<Client> getAllClientsOfUser(User user) {
		Connection conn = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients where user_id=?");
			pStmt.setInt(1, user.getUser_id());
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Client client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("fileNumber"),
						rSet.getDate("dateCreated"));
				client.setClient_id(rSet.getInt("client_id"));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for (Client c : clients)
			c.setMyFamilymembers(this.getFamilymembersOfClient(c));
		return clients;
	}

	public boolean addFamilymember(Familymember famMember, Client client) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("insert into familymembers(forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,email,client_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pStmt.setString(1, famMember.getForename());
			pStmt.setString(2, famMember.getSurname());
			pStmt.setDate(3, new java.sql.Date(famMember.getDateOfBirth()
					.getTime()));
			pStmt.setString(4, famMember.getPostcode());
			pStmt.setString(5, famMember.getStreet());
			pStmt.setString(6, famMember.getHouseNumber());
			pStmt.setString(7, famMember.getCity());
			pStmt.setString(8, famMember.getNationality());
			pStmt.setString(9, famMember.getTelephoneNumber());
			pStmt.setString(10, famMember.getMobilePhoneNumber());
			pStmt.setString(11, famMember.getEmail());
			pStmt.setInt(12, client.getClient_id());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	public boolean updateFamilymember(Familymember famMember) {
		Connection conn = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("update familymembers set forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,email=? where member_id=?");
			pStmt.setString(1, famMember.getForename());
			pStmt.setString(2, famMember.getSurname());
			pStmt.setDate(3, new java.sql.Date(famMember.getDateOfBirth()
					.getTime()));
			pStmt.setString(4, famMember.getPostcode());
			pStmt.setString(5, famMember.getStreet());
			pStmt.setString(6, famMember.getHouseNumber());
			pStmt.setString(7, famMember.getCity());
			pStmt.setString(8, famMember.getNationality());
			pStmt.setString(9, famMember.getTelephoneNumber());
			pStmt.setString(10, famMember.getMobilePhoneNumber());
			pStmt.setString(11, famMember.getEmail());
			pStmt.setInt(12, famMember.getMember_id());
			pStmt.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return b;
	}

	public ArrayList<Familymember> getFamilymembersOfClient(Client client) {
		Connection conn = null;
		ArrayList<Familymember> members = new ArrayList<Familymember>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from familymembers where client_id=?");
			pStmt.setInt(1, client.getClient_id());
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Familymember member = new Familymember(
						rSet.getString("forename"), rSet.getString("surname"),
						rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"));
				member.setMember_id(rSet.getInt("member_id"));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return members;
	}

	public Client getClient(int client_id) {
		Connection conn = null;
		Client client = null;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients where client_id=?");
			pStmt.setInt(1, client_id);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("filenumber"),
						rSet.getDate("dateCreated"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		client.setMyFamilymembers(this.getFamilymembersOfClient(client));
		return client;
	}

	public boolean addSurvey(Survey survey) {
		Connection conn = null;
		boolean b = true;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("insert into surveys(name) values(?)");
			pStmt.setString(1, survey.getName());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		if (b) {
			try {
				conn = this.getConnection();
				PreparedStatement pStmt = conn
						.prepareStatement("select * from surveys where name=?");
				pStmt.setString(1, survey.getName());
				ResultSet rSet = pStmt.executeQuery();
				if (rSet.next()) {
					survey.setSurvey_id(rSet.getInt("survey_id"));
				} else
					b = false;
			} catch (SQLException e) {
				e.printStackTrace();
				b = false;
			} finally {
				this.closeConnection(conn);
			}
			for (Question q : survey.getQuestions()) {
				int question_id = 0;
				b &= this.addQuestion(q);
				if (b) {
					try {
						conn = this.getConnection();
						PreparedStatement pStmt = conn
								.prepareStatement("select * from questions where question=?");
						pStmt.setString(1, q.getQuestion());
						ResultSet rSet = pStmt.executeQuery();
						if (rSet.next()) {
							question_id = rSet.getInt("question_id");
						} else
							b = false;
					} catch (SQLException e) {
						e.printStackTrace();
						b = false;
					} finally {
						this.closeConnection(conn);
					}
				}
				if (b) {
					try {
						conn = this.getConnection();
						PreparedStatement pStmt = conn
								.prepareStatement("insert into surveys_questions(survey_id, question_id) values(?,?)");
						pStmt.setInt(1, survey.getSurvey_id());
						pStmt.setInt(2, question_id);
						pStmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						b = false;
					} finally {
						this.closeConnection(conn);
					}
				}
			}
		}
		return b;
	}

	public boolean updateSurvey(Survey survey) {
		Connection conn = null;
		boolean b = true;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("update surveys set name=? where survey_id=?");
			pStmt.setString(1, survey.getName());
			pStmt.setInt(2, survey.getSurvey_id());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		if (b) {
			for (Question q : survey.getQuestions()) {
				this.updateQuestion(q);
			}
		}
		return false;
	}

	public Survey getSurvey(String surveyName) {
		Survey survey = null;
		Connection conn = null;
		ArrayList<Integer> questionIDs = new ArrayList<Integer>();
		boolean b = true;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from surveys where name=?");
			pStmt.setString(1, surveyName);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				survey = new Survey(rSet.getString("name"), null);
				survey.setSurvey_id(rSet.getInt("survey_id"));
			} else
				b = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		if (b) {
			try {
				conn = this.getConnection();
				PreparedStatement pStmt = conn
						.prepareStatement("select * from surveys_questions where survey_id=?");
				pStmt.setInt(1, survey.getSurvey_id());
				ResultSet rSet = pStmt.executeQuery();
				while (rSet.next()) {
					questionIDs.add(rSet.getInt("question_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				b = false;
			} finally {
				this.closeConnection(conn);
			}
		}
		if (b) {
			ArrayList<Question> questions = new ArrayList<Question>();
			for (int i : questionIDs) {
				questions.add(getQuestion(i));
			}
			survey.setQuestions(questions);
		}
		return (b) ? survey : null;
	}

	public boolean addQuestion(Question question) {
		Connection conn = null;
		boolean b = true;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("insert into questions(question) values(?)");
			pStmt.setString(1, question.getQuestion());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		if (b) {
			for (Answer answer : question.getTheAnswers()) {
				try {
					conn = this.getConnection();
					PreparedStatement pStmt = conn
							.prepareStatement("insert into answers(answer, question_id) values(?,?)");
					pStmt.setString(1, answer.getAnswer());
					pStmt.setInt(2, question.getQuestion_id());
					pStmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					b = false;
				} finally {
					this.closeConnection(conn);
				}
			}
		}
		return b;
	}

	public boolean updateQuestion(Question question) {
		Connection conn = null;
		boolean b = true;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("update questions set question=? where question_id=?");
			pStmt.setString(1, question.getQuestion());
			pStmt.setInt(2, question.getQuestion_id());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}
		if (b) {
			for (Answer answer : question.getTheAnswers()) {
				try {
					conn = this.getConnection();
					PreparedStatement pStmt = conn
							.prepareStatement("update answers set answer=?, question_id=? where answer_id=?");
					pStmt.setString(1, answer.getAnswer());
					pStmt.setInt(2, question.getQuestion_id());
					pStmt.setInt(3, answer.getAnswerID());
					pStmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					b = false;
				} finally {
					this.closeConnection(conn);
				}
			}
		}
		return b;
	}

	public Question getQuestion(int question_id) {
		Question question = null;
		Connection conn = null;
		ArrayList<Answer> answers = new ArrayList<Answer>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from questions where question_id=?");
			pStmt.setInt(1, question_id);
			ResultSet rSet = pStmt.executeQuery();
			if (rSet.next()) {
				question = new Question(question_id,
						rSet.getString("question"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from answers where question_id=?");
			pStmt.setInt(1, question_id);
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				answers.add(new Answer(rSet.getString("answer"), rSet
						.getInt("answer_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		question.setTheAnswers(answers);
		return question;
	}

	public ArrayList<Client> getAllClients() {
		Connection conn = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from clients");
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Client client = new Client(rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"),
						rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenumber"), rSet.getString("city"),
						rSet.getString("nationality"),
						rSet.getString("telephoneNumber"),
						rSet.getString("mobilePhoneNumber"),
						rSet.getString("email"), rSet.getString("fileNumber"),
						rSet.getDate("dateCreated"));
				client.setClient_id(rSet.getInt("client_id"));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for (Client c : clients)
			c.setMyFamilymembers(this.getFamilymembersOfClient(c));
		return clients;
	}

	public ArrayList<String> getSurveyNames() {
		Connection conn = null;
		ArrayList<String> names = new ArrayList<String>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select name from surveys");
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				names.add(rSet.getString("names"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return names;
	}

	public boolean addNetwork(Network network, int client_id,
			int familymember_id) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		boolean b = false;
		try {
			conn = this.getConnection();
			//create network
			pStmt = conn.prepareStatement("insert into networks(datecreated, commentary,client_id,member_id,survey_id) values(?,?,?,?,?)");
			pStmt.setDate(1, network.getDateCreated());
			pStmt.setString(2, network.getCommentary());
			pStmt.setInt(3, client_id);
			pStmt.setInt(4, familymember_id);
			pStmt.setInt(5, network.getTheSurvey().getSurvey_id());
			pStmt.executeUpdate();
			//get auto generated id
			pStmt = conn.prepareStatement("select network_id from networks");
			ResultSet rSet = pStmt.executeQuery();
			if(rSet.next())
				network.setNetwork_id(rSet.getInt("network_id"));
			rSet.close();
			//create contacts
			//TODO
			//intitialize categories
			HashMap<String,Integer> categories = new HashMap<String,Integer>();
			categories.put("household", 1);
			categories.put("family", 2);
			categories.put("friends", 3);
			categories.put("colleagues", 4);
			categories.put("neighbours", 5);
			categories.put("acquaintance", 6);
			categories.put("education", 7);
			categories.put("club", 8);
			categories.put("religion", 9);
			categories.put("careinstitution", 10);
			categories.put("youthcare", 11);
			categories.put("bureauhalt", 12);
			categories.put("justice", 13);
			for(Contact c : network.getContacts()){
				pStmt = conn.prepareStatement("insert into contacts(`fullname`, `role`, `age`, `commentary`, `category_id`, `network_id`) values(?,?,?,?,?,?)");
				pStmt.setString(1, c.getFullname());
				pStmt.setString(2, network.getCommentary());
				pStmt.setInt(3, client_id);
				pStmt.setInt(4, familymember_id);
				pStmt.setInt(5, network.getTheSurvey().getSurvey_id());
				pStmt.executeUpdate();
			}
			//get contact_id's
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		} finally {
			this.closeConnection(conn);
		}		
		return b;
	}

	public ArrayList<Network> getNetworks(int client_id, int familymember_id) {
		Connection conn = null;
		ArrayList<Network> networks = new ArrayList<Network>();
		PreparedStatement pStmt = null;
		try {
			conn = this.getConnection();
			if (client_id == 0) {
				pStmt = conn
						.prepareStatement("select * from networks INNER JOIN surveys ON networks.survey_id=surveys.survey_id where client_id=?");
				pStmt.setInt(1, client_id);
			} else if (familymember_id == 0) {
				pStmt = conn
						.prepareStatement("select * from networks INNER JOIN surveys ON networks.survey_id=surveys.survey_id where member_id=?");
				pStmt.setInt(1, familymember_id);

			} else
				return networks;
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Network network = new Network(rSet.getDate("datecreated"),
						rSet.getString("commentary"));
				network.setNetwork_id(rSet.getInt("network_id"));
				network.setTheSurvey(new Survey(rSet.getString("name"),
						new ArrayList<Question>()));
				networks.add(network);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for (Network n : networks) {
			n.setTheSurvey(this.getSurvey(n.getTheSurvey().getName()));
			this.setResultsContacts(n);
		}
		return networks;
	}

	private ArrayList<Contact> setResultsContacts(Network n) {
		Connection conn = null;
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			conn = this.getConnection();			
			for (Contact c : this.getContacts(n)) {
				ArrayList<Result> results = new ArrayList<Result>();
				PreparedStatement pStmt = conn
						.prepareStatement("select * from results where contact_id=?");
				pStmt.setInt(1, c.getContact_id());
				ResultSet rSet = pStmt.executeQuery();				
				while (rSet.next()) {
					Answer answer=null;
					Question question=null;
					for(Question q : n.getTheSurvey().getQuestions())
						if(rSet.getInt("question_id")==q.getQuestion_id())
							question = q;							
					for(Answer a : question.getTheAnswers())
						if(rSet.getInt("answer_id")==a.getAnswerID())
							answer = a;	
					results.add(new Result(question,answer));				
				}
				c.setMyResults(results);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return contacts;
	}

	private ArrayList<Contact> getContacts(Network n) {
		Connection conn = null;
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			conn = this.getConnection();

			PreparedStatement pStmt = conn
					.prepareStatement("select * from contacts INNER JOIN categories ON contacts.category_id=category.category_id where network_id=?");
			pStmt.setInt(1, n.getNetwork_id());
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Contact c = new Contact(rSet.getString("fullname"),
						rSet.getString("commentary"), rSet.getString("role"),
						rSet.getInt("age"), rSet.getString("name"));
				c.setContact_id(rSet.getInt("contact_id"));
				contacts.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return contacts;
	}

	public ArrayList<User> getAllUsers() {
		User user = null;
		Connection conn = null;
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn
					.prepareStatement("select * from users");
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			while(rSet.next()) {
				if (rSet.getString("usertype").equals("Administrator")) {
					user = new Administrator(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"),
							new ArrayList<User>());
				} else {
					user = new Socialworker(rSet.getString("username"),
							rSet.getString("password"),
							rSet.getString("forename"),
							rSet.getString("surname"),
							rSet.getDate("dateofbirth"),
							rSet.getString("postcode"),
							rSet.getString("street"),
							rSet.getString("housenumber"),
							rSet.getString("city"),
							rSet.getString("nationality"),
							rSet.getString("telephoneNumber"),
							rSet.getString("mobilePhoneNumber"),
							rSet.getString("email"), rSet.getString("isActive")
									.equals("Y"),
							rSet.getString("employeeNumber"));
				}
				user.setDbController(this);
				user.setWwreset(rSet.getString("wwreset")
						.equals("Y"));
				allUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		for(User u : allUsers){
			if (u instanceof Administrator) {
				Administrator admin = (Administrator) u;
				admin.setUsers(getAllSocialworkers());
			}		
		}		
		return allUsers;
	}
}