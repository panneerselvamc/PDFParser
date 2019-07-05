package com.pdfreader.business;

import com.pdfreader.dataaccess.ReadData;

import java.util.List;

interface Email {
	public boolean PDFParse();
}

interface PrintData {
	public List<String[]> showData();
}

public class PDFReader implements Email, PrintData {

	String pop3Host = "pop.gmail.com";
	String mailStoreType = "pop3";
	final String userName = "myprojectjdbc@gmail.com";
	final String password = "jdbc1234";
	static String EMAIL_FROM = "";

	public boolean PDFParse() {

		ReceiveEmail receiveEmail = new ReceiveEmail();
		try {
			EMAIL_FROM = receiveEmail.receiveEmail(pop3Host, mailStoreType, userName, password);
		} catch (Exception e) {
			System.out.println("Unable to process" + e);
		}

		return !EMAIL_FROM.isEmpty();
	}

	public void replyMail(String invoiceNO) {

		ReplyEmail replyEmail = new ReplyEmail();
		replyEmail.sendMail(invoiceNO, EMAIL_FROM);

	}

	public List<String[]> showData() {
		return ReadData.showData();
	}

}
