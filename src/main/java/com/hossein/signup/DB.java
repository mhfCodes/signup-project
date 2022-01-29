package com.hossein.signup;

import java.sql.*;

public class DB {
	
	final private static String URL = "jdbc:oracle:thin:@localhost:1521/pdbdev";
	final private static String USERNAME = "hossein";
	final private static String PASSWORD = "rekaeil";
	
	public static Connection connect() {
		
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int insertData(String name, String email, String username, String password) {
		
		Connection connection = connect();
		String sql = "INSERT INTO USERS (NAME, EMAIL, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, name);
			prepStatement.setString(2, email);
			prepStatement.setString(3, username);
			prepStatement.setString(4, password);
			int rows = prepStatement.executeUpdate();
			return rows;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
