package com.asu.alliancebank.email;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	private static final Logger logger = LoggerFactory
			.getLogger(EmailSender.class);

	@Autowired
	private List<JavaMailSender> mailSenderList;


	public void sendNotificationEmail(String emailaddress, String subject, String msgText) {    	
		try {
			JavaMailSender javaMailSender = getJavaMailSender();
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(new InternetAddress(emailaddress));
			helper.setSubject(subject);			
			helper.setText(msgText);
			javaMailSender.send(message);
		} catch (MessagingException ex) {
			logger.error("Notification email could not be sent.", ex);
		}catch(RuntimeException ex){
			logger.error("Notification email could not be sent.", ex);
		}
	}

	public void sendNotificationEmail(String emailaddress, String subject, String msgText, String attachments[]){
		try {
			
			JavaMailSender javaMailSender = getJavaMailSender();
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(new InternetAddress(emailaddress));
			helper.setSubject(subject);			
			helper.setText(msgText);


			Multipart multipart = new MimeMultipart();

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(msgText);
			multipart.addBodyPart(messageBodyPart);
			
			
			messageBodyPart = new MimeBodyPart();
			File file = new File(attachments[0]);
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(file.getName());
			multipart.addBodyPart(messageBodyPart);
			
			
			messageBodyPart = new MimeBodyPart();
			file = new File(attachments[1]);
			source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(file.getName());
			multipart.addBodyPart(messageBodyPart,1);

			message.setContent(multipart);
			javaMailSender.send(message);
		} catch (MessagingException ex) {
			logger.error("Notification email could not be sent.", ex);
		}catch(RuntimeException ex){
			logger.error("Notification email could not be sent.", ex);
		}
	}
	
	private JavaMailSender getJavaMailSender(){
		int index =getRandom(mailSenderList.size());
		return mailSenderList.get(index);
	}
	
	private int getRandom(int limit){
		Random r = new Random();
		
		return r.nextInt(limit);
	}
}
