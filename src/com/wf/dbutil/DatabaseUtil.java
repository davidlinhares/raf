package com.wf.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseUtil {
	
	private static final String DB_NAME = "asd_proj_1";

	public static void createTable(String name, List<DBColumn> columns) {
		createDatabase(DB_NAME);
		
	    try{
	    	Connection conn = DBConnectionFactory.getDbConnection();
	    	
	    	Statement stmt = conn.createStatement();
	      
	    	String sql = "CREATE TABLE IF NOT EXISTS "+ name +" (id INTEGER not NULL, PRIMARY KEY ( id ))";
	    	
	    	stmt.executeUpdate(sql);
	    	
	    	System.out.println("Created table in given database...");
	    }catch(Exception e){
	    	System.out.println("Table exists");
	   	}
	    
	    addColumns(columns, name);
	}
	
	private static void addColumns(List<DBColumn> columns, String name) {
		try {
			for(DBColumn col : columns) {
				addColumn(col, name);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void addColumn(DBColumn col, String tableName) throws SQLException {
		Connection conn = DBConnectionFactory.getDbConnection();
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT IF(count(*) = 1, 'exist','not_exist') AS result " + 
				"FROM information_schema.columns " + 
				"WHERE table_schema = '"+DB_NAME+"' " + 
				"AND table_name = '"+tableName+"' " + 
				"AND column_name = '"+col.getName()+"';";
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next() && rs.getString("result").equals("not_exist")) {
			sql = "ALTER TABLE "+tableName+" ADD COLUMN "+col.getName()+" "+col.getType();
			stmt.executeUpdate(sql);
		}
		
	}
	
	private static boolean createDatabase(String name) {
		try{  
			Connection conn = DBConnectionFactory.getServerConnection();
		
			Statement stmt = conn.createStatement();  
			
			String sql = "CREATE DATABASE "+name; 
			stmt.executeUpdate(sql);
			conn.close();
			return true;
		}catch(Exception e){ 
			System.out.println("Db creation skipped"); 
		}
		return false;
	}

}
