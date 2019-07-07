package com.pdfreader.business;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.pdfreader.constants.InvoiceAddress;
import com.pdfreader.constants.InvoiceData;
import com.pdfreader.dataaccess.InvoiceAddressDB;
import com.pdfreader.dataaccess.InvoiceDataDB;

public class ReceiveEmail {
	 String FILE_NAME;
	 String EMAIL_FROM = "";

	public String receiveEmail(String pop3Host, String mailStoreType, String userName, String password)
			throws Exception {

		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true");

		// Get the Session object.
		Session session = Session.getInstance(props);

		Store store = session.getStore("pop3s");
		store.connect(pop3Host, userName, password);

		// Create the folder object and open it in your mailbox.
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);

		// Retrieve the messages from the folder object.
		Message[] messages = emailFolder.getMessages();
		System.out.println("Total Message" + messages.length);

		// Iterate the messages
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			
			System.out.println("---------------------------------");
			System.out.println("Details of Email Message " + (i + 1) + " :");
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			EMAIL_FROM = String.valueOf(message.getFrom()[0]);
			EMAIL_FROM = EMAIL_FROM.substring(EMAIL_FROM.indexOf("<") + 1, EMAIL_FROM.lastIndexOf(">"));

			// Iterate multiparts
			Multipart multipart = (Multipart) message.getContent();

			for (int k = 0; k < multipart.getCount(); k++) {

				BodyPart bodyPart = multipart.getBodyPart(k);
				if (bodyPart.getDisposition() != null && bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
					FILE_NAME = bodyPart.getFileName();
					System.out.println("--" + FILE_NAME);
					System.out.println("file name " + bodyPart.getFileName());
					System.out.println("size " + bodyPart.getSize());
					System.out.println("content type " + bodyPart.getContentType());
					InputStream stream = (InputStream) bodyPart.getInputStream();
					File targetFile = new File("c:\\users\\pselvam6400\\pdf\\" + bodyPart.getFileName());
					java.nio.file.Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					PDFDataProcess(FILE_NAME);

				}
			}
		}
		emailFolder.close(false);
		store.close();
		return EMAIL_FROM;
	}

	public static void PDFDataProcess(String FILE_NAME) throws Exception {
		ArrayList<InvoiceData> invoiceDataList = new ArrayList<>();
		ArrayList<InvoiceAddress> invoiceAddressList = new ArrayList<>();

		if (FILE_NAME != null) {
			System.out.println("Filename" + FILE_NAME);
			PDDocument document = PDDocument.load(new File("c:/users/pselvam6400/pdf/" + FILE_NAME));

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);

				String lines[] = pdfFileInText.split("\\r?\\n");

				String address;
				int index;

				String shiftTo = "";
				String soldTo = "";
				String remitTo = "";

				String invoiceDate = "";
				String customerPO = "";
				String amount = "";
				String invoiceNum = "";

				for (int i = 0; i < lines.length; i++) {

					if (lines[i].equals("Invoice No"))
						invoiceNum = lines[i + 1];

					else if (lines[i].equals("Invoice Date"))
						invoiceDate = lines[i + 1];

					else if (lines[i].equals("Customer P.O."))
						customerPO = lines[i + 1];

					else if (lines[i].equals("Total Invoice"))
						amount = lines[i + 1];

					else if (lines[i].equals("Sold To")) {
						index = i + 1;
						address = lines[index];
						while (!(address.equals("Ship To"))) {
							index++;
							soldTo += address + "\n";
							address = lines[index];
						}
					}

					else if (lines[i].equals("Ship To")) {
						index = i + 1;
						address = lines[index];
						while (!(address.equals("Remit To"))) {
							index++;
							shiftTo += address + "\n";
							address = lines[index];
						}
					}

					else if (lines[i].equals("Remit To")) {

						index = i + 1;
						address = lines[index];
						while (!(address.equals("Payment Terms"))) {
							index++;
							remitTo += address + "\n";
							address = lines[index];
						}
					}

					if (!invoiceDate.isEmpty() && !customerPO.isEmpty() && !amount.isEmpty() && !invoiceNum.isEmpty()
							&& !shiftTo.isEmpty() && !soldTo.isEmpty() && !remitTo.isEmpty()) {

						invoiceDataList.add(new InvoiceData(invoiceDate, customerPO, amount, invoiceNum));
						invoiceAddressList.add(new InvoiceAddress(soldTo, shiftTo, remitTo, invoiceNum));

						shiftTo = soldTo = remitTo = "";
						customerPO = amount = invoiceDate = invoiceNum = "";
					}

				}

			}

		}

		InvoiceAddressDB invoiceAddressDB = new InvoiceAddressDB();
		invoiceAddressDB.insertData(invoiceAddressList);
		InvoiceDataDB invoiceDataDB = new InvoiceDataDB();
		invoiceDataDB.insertData(invoiceDataList);
	}
}
