package com.training.service;

import java.util.List;
import java.util.Scanner;

import com.pdfreader.business.PDFReader;

public class InvoiceReader {
	static PDFReader pdfreader = new PDFReader();
	static Scanner scan = new Scanner(System.in);
	static boolean flag;

	public static void main(String[] args) throws Exception {

		while (true) {

			System.out.println(
					"Press \n 1 to read a Invoice\n 2 to Print all data in Invoice Data\n 3 to Approve a Invoice");
			int choice = scan.nextInt();
			choiceProcess(choice);

		}

	}

	public static void choiceProcess(int choice) {

		switch (choice) {
		case 1: {

			flag = pdfreader.PDFParse();

			break;

		}

		case 2: {

			List<String[]> dataArr = pdfreader.showData();

			System.out.println(dataArr);

			for (String[] eachArr : dataArr) {

				System.out.println("-------------------------------------------------------------------");

				for (String eachValue : eachArr) {

					System.out.println(eachValue);
				}
			}

			break;
		}

		case 3: {

			if (flag) {
				System.out.println("Please enter a Invoice ID: ");
				String value = scan.next();
				pdfreader.replyMail(value);
			} else {
				System.out.println("Please Read a invoice before approving by Pressing 1 ");
			}

			break;
		}
		}
	}

}
