package edu.mum.eventmanagement.services;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.UserRepository;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.*;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.Recipient;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

public class EmailService {
	private static String smtp = "smtp.gmail.com";
	
	private static int port = 587;
		
	public static void sendAdvertisement(Advertisement ad) {
		
		String msgTitle = ad.getEvent().getName() + " coming soon...";
		String mailMsg = 			
			"<h1>This amazing event is going to happen near you</h1><br/>" +
			"<h2>" + ad.getEvent().getName() + "</h2><br />" +
			"<p>" + ad.getEvent().getDate().toString() + "</p>" +
			"<p>at " + ad.getEvent().getLocationName() + " (" + ad.getEvent().getLocation().getAddress() + ")</p>";
		
		for(User user : new UserRepository().getAll()) {
			sendEmail(user.getUsername(), user.getEmail(), msgTitle, mailMsg, ad.getImgLocation());
		}
	}
	
	public static void inviteGuest(Event evt, String email) {
		String mailMsg = 			
			"<h1>This amazing event is going to happen near you</h1><br/>"+
					"<h2>" + evt.getName() + "</h2><br />" +
					"<p>" + evt.getDate().toString() + "</p>" +
					"<p>at " +evt.getLocationName() + " (" + evt.getLocation().getAddress() + ")</p>"
					+"<h1><font color=\"blue\">Please register to become Guest in the event and VOTE for the best performance.<font/></h1><br/>"
					;
			sendEmail(email, email, "Hello new guest", mailMsg, "");
	}
	
	private static void sendEmail(String name, String emailTo, String title, String msg, String imgPath) {
		if(!emailTo.equals("natanael.lemos@live.com")) {
			return;
		}
		
		Email email = new Email();

		email.setFromAddress("Event Manager", emailFrom);
		email.setReplyToAddress("Event Manager", emailFrom);
		email.addNamedToRecipients(name, emailTo);
		email.setReturnReceiptTo(new Recipient("Event Manager", emailFrom, RecipientType.TO));
		email.setSubject(title);
		email.setTextHTML(msg);
		
		if(imgPath != null && imgPath.length() > 0) {
			String mimeType = "image/jpeg";
			String extension = ".jpg";
			
			if(imgPath.endsWith("png")) {
				mimeType = "image/png";
				extension = ".png";
			}
			
			email.addAttachment("Image" + extension, getImageBytes(imgPath), mimeType);
		}
		
		email.addHeader("X-Priority", 5);
		email.setUseReturnReceiptTo(true);

		new Mailer(
		    new ServerConfig(smtp, port, emailFrom, password),
		    TransportStrategy.SMTP_TLS
		).sendMail(email);
	}
	
	private static byte[] getImageBytes(String imagePath) {
		try {
			File file = new File(imagePath);
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void sendExpiredDueDate(Event event) {
		String msg = 
			"<h1>The following event is waiting for your approval</h1>" +
			"<h2>" + event.getName() + "</h2>" + 
			"<p>" + event.getDate().toString() + "</p>" +
			"<p>at " + event.getLocationName() + " (" + event.getLocation().getAddress() + ")</p>";
		
		for(User user : new UserRepository().getApprovers()) {			
			sendEmail(user.getUsername(), user.getEmail(), "Event pending for approval", msg, null);
		}
	}
}