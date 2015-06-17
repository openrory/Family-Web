package util.FamilyWeb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import databaseControllers.FamilyWeb.DatabaseInterface;
import databaseControllers.FamilyWeb.MySQLDao;
import servletControllers.FamilyWeb.LoginController;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {	
		
		DatabaseInterface dbController = new MySQLDao();
		
		new LoginController(dbController);
		
		sce.getServletContext().setAttribute("dbController", dbController);		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
