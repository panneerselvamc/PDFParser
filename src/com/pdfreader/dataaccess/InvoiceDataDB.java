package com.pdfreader.dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import  com.pdfreader.constants.*;
@SuppressWarnings("unused")
public class InvoiceDataDB {

	public void insertData(List<InvoiceData> obj) {
		for (InvoiceData eachobj : obj) {
			Connection conn = DBConnection.getConnection();
			String sqlQuery = "INSERT INTO INVOICEDATA (INVOICENUM,AMOUNT,CUSTOMERPO,INVOICEDATE) VALUES (?,?,?,?) ";
			try {
				PreparedStatement statement = conn.prepareStatement(sqlQuery);
				statement.setString(1, eachobj.getInvoiceNum());
				statement.setString(2, eachobj.getAmount());
				statement.setString(3, eachobj.getCustomerPO());
				statement.setString(4, eachobj.getInvoiceDate());

				int result = statement.executeUpdate();
				if (result > 0) {
					System.out.println("inserted sucessfully");
				}
				
			} catch (SQLException e) {
				System.out.println("Cannot insert into database"+e);
			}
			finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
