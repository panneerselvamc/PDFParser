package com.pdfreader.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	public static Connection conn;

	public static Connection getConnection() {

		try {
			String userName = "hr";
			String password = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);
			conn = DriverManager.getConnection(url, connectionProps);

			System.out.println("Database connections successful");

			return conn;

		} catch (SQLException e) {
			System.err.println("Failed to connect to database" + e);
		}
		return conn;
	}

}
