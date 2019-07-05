package com.pdfreader.business;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pdfreader.dataaccess.ReadData;

interface InvoiceApprove {
	public void sendMail(String invoiceNO, String EMAIL_FROM);
}

public class ReplyMail implements InvoiceApprove {
	static boolean flag;
	static String content;;
	
	public void sendMail(String invoiceNO, String EMAIL_FROM) {
		
		ReadData readData = new ReadData();
		
		try {
			flag=readData.invoiceApproval(invoiceNO);
			if(flag)
				content="\n\nCongratulations, your Invoice is Approved :  ";
			else
			content="\n\nPlease enter a valid Invoice Number, The Invoice number you entered is:";
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		
		Session session;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("myprojectjdbc@gmail.com", "jdbc1234");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("myprojectjdbc@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_FROM + "," + EMAIL_FROM));
			message.setSubject("UPDATED INVOICE DETAILS");
			message.setText("Hi," + content + invoiceNO);

			Transport.send(message);

			System.out.println("Email Sent Sucessfully");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
