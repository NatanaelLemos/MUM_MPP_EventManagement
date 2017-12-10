package edu.mum.eventmanagement.services;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.UserRepository;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import java.io.File;
import java.io.IOException;
import java.security.Security;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.*;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.Recipient;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.simplejavamail.util.ConfigLoader;

public class EmailService {
	private static String encodeImage(String imagePath, String type) {
		try {
		File file = new File(imagePath);
			return Base64.encodeBase64String(FileUtils.readFileToByteArray(file));
		}catch(Exception ex) {
			System.out.println(ex);
			return "";
		}
	}

	public static void sendAdvertisement(Advertisement ad) {
		ConfigLoader.loadProperties("simplejavamail.properties", false); // optional default
		ConfigLoader.loadProperties("overrides.properties", false); // optional extra

		Email email = new Email();

		email.setFromAddress("lollypop", "superdupertest1234567@hotmail.com");
		email.setReplyToAddress("lollypop", "superdupertest1234567@hotmail.com");
		//email.addNamedToRecipients("lollypop", "superdupertest1234567@hotmail.com");
		email.addNamedToRecipients("C. Cane", "natanael.lemos@live.com");
		email.setReturnReceiptTo(new Recipient("My test", "superdupertest1234567@hotmail.com", RecipientType.TO));
		email.setSubject(ad.getEvent().getName() + " coming soon...");
		email.setText("We should meet up! ;)");
		email.setTextHTML("&lt;img src=&#39;cid:wink1&#39;&gt;&lt;b&gt;We should meet up!&lt;/b&gt;&lt;img src=&#39;cid:wink2&#39;&gt;");

		email.addHeader("X-Priority", 5);
		email.setUseReturnReceiptTo(true);

		new Mailer(
		    new ServerConfig("smtp.live.com", 587, "superdupertest1234567@hotmail.com", "MySuperPasswordGoesHere123"),
		    TransportStrategy.SMTP_TLS
		).sendMail(email);
	}
}