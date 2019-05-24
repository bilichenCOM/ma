package service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailService {

	private static final String LOGIN = "bilichenko.mykhailo@gmail.com";
	private static final String PASSWORD = "112358fibo";

	private static final Logger LOGGER = Logger.getLogger(MailService.class);

	public static void sendMessage(String email, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(LOGIN, PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Cabinet purchase verification code");
			message.setText(text);
			Transport.send(message);
			LOGGER.debug("verification email successfully sent! text: " + text);
		} catch (AddressException e) {
			LOGGER.debug("wrong email address, sending failed...", e);
		} catch (MessagingException e) {
			LOGGER.debug("problems by sending email..." + e.getMessage());
		}
	}
}