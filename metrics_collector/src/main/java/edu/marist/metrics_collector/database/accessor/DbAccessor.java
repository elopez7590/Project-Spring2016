/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marist.metrics_collector.database.accessor;

import java.util.ArrayList;

import edu.marist.metrics_collector.database.helpers.AbstractDatabaseFunctions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Anthony Cali
 */
public class DbAccessor {
   AbstractDatabaseFunctions db;
   public DbAccessor() throws ClassNotFoundException {
      db = new AbstractDatabaseFunctions();
   }
   
   public ArrayList<String> getData(String query) {
      ArrayList<String> localArr = new ArrayList<>();
      
      String database = "jdbc:postgresql:metrics";
      String user = System.getProperty("User");
      String password = "metricsDb";
      try {
        Connection conn = db.connectToDb(database, user, password);
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
           String result = rs.getString("PID")+ ","
                   + rs.getString("processname") + ","
                   + rs.getString("parentPID") + ","
                   + rs.getString("totalsize") + ","
                   + rs.getString("dateofcreation");
           localArr.add(result);
        }
        db.ReleaseDb(conn);
      } catch (Exception e){
         e.printStackTrace();
      }
      return localArr;
   }
   
   public boolean putData(String query) {
      boolean complete = false;
      
      String database = "jdbc:postgresql:metrics";
      String user = System.getProperty("User");
      String password = "metricsDb";
      try {
         Connection conn = db.connectToDb(database, user, password);
         Statement stmt = conn.createStatement();
         
         complete = stmt.execute(query);
         db.ReleaseDb(conn);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return complete;
   }
}
