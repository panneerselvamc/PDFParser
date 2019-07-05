package com.pdfreader.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

	public static List<String[]> showData() {
		Connection conn = DBConnection.getConnection();
		ArrayList<String[]> totalData = new ArrayList<String[]>();
		try {
			Statement statement = conn.createStatement();

			ResultSet rs1 = statement.executeQuery(
					"select INVOICEDATA.ISAPPROVED,INVOICEDATA.INVOICENUM,INVOICEDATA.AMOUNT,INVOICEDATA.INVOICEDATE,INVOICEDATA.CUSTOMERPO, INVOICE.SOLDTO,INVOICE.SHIPTO,INVOICE.REMITTO FROM INVOICEDATA  INNER JOIN INVOICE  ON INVOICEDATA.INVOICENUM=INVOICE.INVOICENUM");
			while (rs1.next()) {
				String arr[] = { rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4),
						rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getString(8) };
				totalData.add(arr);
			}
		
		} catch (SQLException e) {
			System.out.println("Cannot read from database" + e);
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalData;
	}

	
	public boolean invoiceApproval(String invoiceNO) {
		Connection conn = DBConnection.getConnection();
		boolean flag=true;
		try {
			Statement statement = conn.createStatement();
			int val = statement
					.executeUpdate("UPDATE INVOICEDATA SET ISAPPROVED = 1 WHERE INVOICENUM = " + invoiceNO);
			if(val>0)
				flag=true;
			else{
				flag=false;
				System.out.println("Please try again with valid Invoice Number!!!!");
			}
			
		} 
		catch (SQLException e) {
			System.out.println("Cannot update the invoice number" + e);
			
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flag;

	}

}