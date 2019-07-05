package com.pdfreader.service;

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

			
			for (String[] eachArr : dataArr) {
				System.out.println("-----------------------------------------------------------");
				for(int eachValue=0;eachValue<eachArr.length;eachValue++){
					
				
					if(eachValue==0){
						if(eachArr[eachValue]=="1")
						System.out.println("Approval Status: "+"Approved\n");
						else
							System.out.println("Approval Status: "+"Not Approved\n");
					}
					else if(eachValue==1){
						System.out.println("Invoice Number: "+eachArr[eachValue]);
		
					}
					else if(eachValue==2){
						System.out.println("Invoice Amount: "+eachArr[eachValue]);
					}
					else if(eachValue==3){
						System.out.println("\nDate : "+eachArr[eachValue]);
					}
					else if(eachValue==4){
						System.out.println("Customer PO: "+eachArr[eachValue]);
					}
					else if(eachValue==5){
						System.out.println("Sold To:\n "+eachArr[eachValue]);
					}
					else if(eachValue==6){
						System.out.println("Ship To: \n "+eachArr[eachValue]);
					}
					else{
						System.out.println("Remit To:\n "+eachArr[eachValue]);
					}
					
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
