package edu.marist.metrics_collector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.*;

public class MetricsQueryDao {
	
public static Connection getConection(){
		
		//TODO: modify the jdbc driver and database url, username and password here
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://172.16.24.127:3306/dtmngipt01db";
		String user = "dtmng_ipt01db"; 
		String password = "111111";


		Connection conn = null;
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	public static String getAllMetrics(){
		
		JSONObject json = new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		
		try{			
			Connection conn = getConection();
			Statement statement = conn.createStatement();

	        // change the sql command if necessary
	        String sql = "select * from MetricCollection";
	        
	        // change the attribute name in table if necessary
	        ResultSet rs = statement.executeQuery(sql);
	        while(rs.next())
	        {
	        	JSONObject proJSON = new JSONObject();
	        	proJSON.put("PID", rs.getString("PID"));
	        	proJSON.put("processname", rs.getString("processname"));
	        	proJSON.put("machinename", rs.getString("machinename"));
	        	proJSON.put("parentPID", rs.getString("parentPID"));
	        	proJSON.put("totalsize", rs.getString("totalsize"));
	        	proJSON.put("dateofcreation", rs.getString("dateofcreation"));
				
				jsonMembers.put(proJSON);	        	
	        }
	        json.put("metrics", jsonMembers);
	        conn.close();    
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return json.toString();
	}

}
