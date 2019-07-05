package com.pdfreader.dataaccess;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import com.pdfreader.constants.InvoiceAddress;


@SuppressWarnings("unused")
public class InvoiceAddressDB {


	public void insertData(List<InvoiceAddress> tempList) {

		for (InvoiceAddress obj : tempList) {
			Connection conn = DBConnection.getConnection();
			String sqlQuery = "INSERT INTO INVOICE (SOLDTO,SHIPTO,REMITTO,INVOICENUM) VALUES (?,?,?,?) ";
			try {
				PreparedStatement statement = conn.prepareStatement(sqlQuery);
				statement.setString(1, obj.getSoldTo());
				statement.setString(2, obj.getShiftTo());
				statement.setString(3, obj.getRemitTo());
				statement.setString(4, obj.getInvoiceNum());

				int result = statement.executeUpdate();
				if (result > 0) {
					System.out.println("inserted sucessfully");
				} else {
					System.out.println("insertion failed");
				}
				
			} catch (SQLException e) {
				System.out.println("Cannot insert into Database" + e);
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
