package util.FamilyWeb;

import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.FamilyWeb.User;

public class MailService {

	private User employee = null;
	private String message;
	private String subject;
	
	public MailService(User employee, String subject, String message) {
		this.employee = employee;
		this.subject = subject;
		this.message = message;
	}
	
	public boolean sendMail() {
		boolean sendStatus = false;
		Properties prop = new Properties();

		prop.put("mail.smtp.from", "info.familyweb@gmail.com");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);

	    Session mailSession = Session.getDefaultInstance(prop, new Authenticator() {

	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("info.familyweb@gmail.com", "familyweb12345");
	            }

	        });

		try {
			MimeMessage msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress("info.familyweb@gmail.com", "FamilyWeb"));
			msg.setRecipients(Message.RecipientType.TO, this.employee.getEmail());
			msg.setSubject(subject);
			msg.setSentDate(Calendar.getInstance().getTime());
			msg.setText("Beste " + employee.getForename() + ", \n\n" + message + "\n");
			Transport.send(msg);
			sendStatus = true;
		} catch (Exception e) {
			Logger.getLogger("sp.lesson5").warning("send failed: " + e.getMessage());
		}
		return sendStatus;
	}
}
