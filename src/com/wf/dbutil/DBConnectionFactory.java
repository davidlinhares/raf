package com.wf.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
	
	private static Connection connection;
	
	private static final String DB_NAME = "asd_proj_1";
	private static final String SERVER_URL = "jdbc:mysql://localhost:8889?serverTimezone=UTC";
	private static final String DB_URL = "jdbc:mysql://localhost:8889/"+DB_NAME+"?serverTimezone=UTC";

    //  Database credentials
	private static final String USER = "root";
	private static final String PASS = "root";
	
	public synchronized static Connection getDbConnection() {
		if(connection != null) {
			return connection;
		}
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getServerConnection() {
		Connection serverConnection = null;
		try {
			serverConnection = DriverManager.getConnection(SERVER_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serverConnection;
	}

}
