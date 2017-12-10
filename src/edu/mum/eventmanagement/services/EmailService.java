package edu.mum.eventmanagement.services;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.UserRepository;
import com.sendgrid.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class EmailService {
	private static String encodeImage(String imagePath, String type) {
		 //return Base64.encode(FileUtils.readFileToByteArray(file));
		return "";
	}

	public static void sendAdvertisement(Advertisement ad) {
		String imgType = "image/png";
		Email from = new Email("natanael.lemos@live.com");
		String subject = ad.getEvent().getName() + " coming soon...";
		Content content = new Content("text/plain", "and easy to do anywhere, even with Java");

		if (ad.getImgLocation().endsWith("png")) {
			imgType = "image/png";
		} else {
			imgType = "image/jpg";
		}

		Attachments attachments = new Attachments();
		attachments.setContent(encodeImage(ad.getImgLocation(), imgType));
		attachments.setType(imgType);

		attachments.setFilename("Img");
		attachments.setDisposition("inline");
		attachments.setContentId("Banner");

		for (User user : new UserRepository().getAll()) {
			Email to = new Email(user.getEmail());
			Mail mail = new Mail(from, subject, to, content);
			mail.addAttachments(attachments);

			SendGrid sg = new SendGrid("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");
			Request request = new Request();
			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}
}