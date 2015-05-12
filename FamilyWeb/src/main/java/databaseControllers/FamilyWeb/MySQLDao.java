package databaseControllers.FamilyWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

public class MySQLDao implements DatabaseInterface {
	private String username = "root";
	private String passwd = "wachtwoord";
	private String dbLocation = "jdbc:mysql://localhost/familyweb";
//	static final String DB_URL = "jdbc:mysql://localhost/businessrule";
	
	public Connection getConnection() {
		Connection conn=null;
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dbLocation,username,passwd);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("failed to connect to database");
		}
		return conn;
	}
	
	public void closeConnection(Connection conn){
		try{
			conn.close();
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public boolean addUser(User user){
		Connection conn = null;
		boolean b = false;
		try{
			conn=this.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into users(username,password,forename,surname,dateofbirth,postcode,street,housenumber,city,nationality,telephonenumber,mobilephonenumber,usertype,email,isactive,employeenumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			pStmt.setString(12, user.getTelephoneNumber());
			pStmt.setString(13, user.getMobilePhoneNumber());
			pStmt.setString(14, (user instanceof Administrator) ? "Administrator":"Socialworker");
			pStmt.setString(15, user.getEmail());
			pStmt.setString(16, user.isActive() ? "Y" :"N");
			pStmt.setString(17, user.getEmployeeNumber());
			pStmt.executeUpdate();
			b = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection(conn);
		}
		return b;
	}
	
	public User getUser(String username) {
		User user = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			ResultSet rSet = pStmt.executeQuery();
			if(rSet.next()) {
				if(rSet.getString("usertype").equals("administrator")){
					user = new Administrator(rSet.getString("username"), rSet.getString("password"), rSet.getString("forename"),
							rSet.getString("surname"), rSet.getDate("dateofbirth"), rSet.getString("postcode"), rSet.getString("street"),
							rSet.getString("housenuber"), rSet.getString("city"), rSet.getString("nationality"),
							rSet.getString("telephoneNumber"), rSet.getString("mobilePhoneNumber"), rSet.getString("email"),
							rSet.getString("isActive").equals("Y"), rSet.getString("employeeNumber"), new ArrayList<User>());
				}else{
					user = new Socialworker(rSet.getString("username"), rSet.getString("password"), rSet.getString("forename"),
							rSet.getString("surname"), rSet.getDate("dateofbirth"), rSet.getString("postcode"), rSet.getString("street"),
							rSet.getString("housenuber"), rSet.getString("city"), rSet.getString("nationality"),
							rSet.getString("telephoneNumber"), rSet.getString("mobilePhoneNumber"), rSet.getString("email"),
							rSet.getString("isActive").equals("Y"), rSet.getString("employeeNumber"));
				}
				user.setDbController(this);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		if(user instanceof Administrator){
			Administrator admin = (Administrator)user;
			admin.setUsers(getAllSocialworkers());
		}
		return user;
	}

	public boolean updateUser(User user){
		Connection conn = null;
		boolean b = false;
		try{
			conn=this.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("update users set password=?,forename=?,surname=?,dateofbirth=?,postcode=?,street=?,housenumber=?,city=?,nationality=?,telephonenumber=?,mobilephonenumber=?,usertype=?,email=?,isactive=?,employeenumber=? where username=?");
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
			pStmt.setString(12, (user instanceof Administrator)? "Administrator":"Socialworker");
			pStmt.setString(13, user.getEmail());
			pStmt.setString(14, user.isActive() ? "Y" :"N");
			pStmt.setString(15, user.getEmployeeNumber());
			pStmt.setString(16, user.getUsername());
			pStmt.executeUpdate();		
			b = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection(conn);
		}
		return b;
	}	
	
	public boolean authentication(String username, String password) {
		boolean auth = false;
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("dit is een test" + conn);
			PreparedStatement pStmt = conn.prepareStatement("select username, password from users where username=? AND password=?");
			System.out.println("dit is een test2" + conn);
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			ResultSet rSet = pStmt.executeQuery();			
			if(rSet.next()) {
				auth = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return auth;
	}

	public ArrayList<User> getAllSocialworkers(){
		Connection conn = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			conn = this.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("select * from users where usertype=?");
			pStmt.setString(1, "Socialworker");
			ResultSet rSet = pStmt.executeQuery();
			while(rSet.next()) {
				User user = new Socialworker(rSet.getString("username"), rSet.getString("password"), rSet.getString("forename"),
						rSet.getString("surname"), rSet.getDate("dateofbirth"), rSet.getString("postcode"), rSet.getString("street"),
						rSet.getString("housenuber"), rSet.getString("city"), rSet.getString("nationality"),
						rSet.getString("telephoneNumber"), rSet.getString("mobilePhoneNumber"), rSet.getString("email"),
						rSet.getString("isActive").equals("Y"), rSet.getString("employeeNumber"));
				user.setDbController(this);
				users.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
		}
		return users;
	}
	
}
