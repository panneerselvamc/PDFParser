package com.pdfreader.business;

import com.pdfreader.dataaccess.ReadData;

import java.util.List;

interface Email {
	public String PDFParse();
}

interface PrintData {
	public List<String[]> showData();
}

public class PDFReader implements Email, PrintData {

	final static String pop3Host = "pop.gmail.com";
	final static String mailStoreType = "pop3";
	final String userName = "myprojectjdbc@gmail.com";
	final String password = "jdbc1234";
	 String EMAIL_FROM = "";

	public String PDFParse() {

		ReceiveEmail receiveEmail = new ReceiveEmail();
		try {
			EMAIL_FROM = receiveEmail.receiveEmail(pop3Host, mailStoreType, userName, password);
		} catch (Exception e) {
			System.out.println("Unable to process" + e);
		}

		if (!EMAIL_FROM.isEmpty()) {
			return EMAIL_FROM;
		} else {
			return "";
		}
	}

	public void replyMail(String invoiceNO,String emailFrom) {

		ReplyEmail replyEmail = new ReplyEmail();
		replyEmail.sendMail(invoiceNO, emailFrom);

	}

	public List<String[]> showData() {
		return ReadData.showData();
	}

}
