package dbutil;

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
		
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	conn = DBConnectionFactory.getDbConnection();
	    	
	    	stmt = conn.createStatement();
	      
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

	private static String generateAttrSQL(List<DBColumn> columns) {
		String result = " ";
		for(DBColumn column : columns) {
			if(!column.getName().equalsIgnoreCase("id")) {
				result += "ADD COLUMN "+column.getName()+" "+column.getType()+",";
			}
		}
		return result.replaceAll(",$", ";");
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
