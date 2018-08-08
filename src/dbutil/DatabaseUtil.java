package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DatabaseUtil {
	
	private static final String DB_NAME = "asd_proj_1";

	public static void createTable(String name, List<DBColumn> columns) {
		createDatabase(DB_NAME);
		
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	conn = DBConnectionFactory.getDbConnection();
	    	
	    	System.out.println("Connected database successfully...");
	      
	    	System.out.println("Creating table in given database...");
	    	stmt = conn.createStatement();
	      
	    	String sql = "CREATE TABLE "+ name +" (id INTEGER not NULL, "+ generateAttrSQL(columns) +"PRIMARY KEY ( id ))"; 
	    	System.out.println(sql);

	    	stmt.executeUpdate(sql);
	    	System.out.println("Created table in given database...");
	   }catch(Exception se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }
	}
	
	private static String generateAttrSQL(List<DBColumn> columns) {
		String result = "";
		for(DBColumn column : columns) {
			if(!column.getName().equalsIgnoreCase("id")) {
				result += column.getName()+" "+column.getType()+", ";
			}
		}
		return result;
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
	
	private void runQuery(String q) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sonoo","root","root"
			);  
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from emp");  
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			}  
			//con.close();  
		}catch(Exception e){ 
			System.out.println(e); 
		}  
	}

}
