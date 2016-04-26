/**
 * 
 * @authors Anthony Cali, Edde Lopez
 * 
 * @filename DbAccessor.java
 * @version 0.9
 * 
 */
package edu.marist.metrics_collector.database.accessor;

import java.util.ArrayList;

import edu.marist.metrics_collector.database.helpers.AbstractDatabaseFunctions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * DbAccessor class and implementation.
 * 
 */
public class DbAccessor {
   AbstractDatabaseFunctions db;
   
   /**
    * DbAccessor constructor;
    * 
    * @throws ClassNotFoundException if the PostgreSQL database
    *         class implementation is not found.
    */
   public DbAccessor() throws ClassNotFoundException {
      db = new AbstractDatabaseFunctions();
   }
   
   public ArrayList<String> getData(String query) {
      ArrayList<String> localArr = new ArrayList<>();
      
      String database = "jdbc:postgresql:metrics";
      String user = "postgres";
      String password = "metricsDb";
      try {
        Connection conn = db.connectToDb(database, user, password);
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
           String result = rs.getString("PID")+ ","
                   + rs.getString("processname") + ","
                   + rs.getString("machinename") + ","
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
   
   /**
    * putData method for sending data to the database.
    * @param query the query string (Database operation).
    * @return if there is a result set or not.
    */
   public boolean putData(String query) {
      boolean output = false;
      
      String database = "jdbc:postgresql:metrics";
      String user = "postgres";
      String password = "metricsDb";
      try {
         Connection conn = db.connectToDb(database, user, password);
         Statement stmt = conn.createStatement();
         
         output = stmt.execute(query);
         stmt.close();
         db.ReleaseDb(conn);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return output;
   }
   
   /**
    * runQuery method for sending data to the database.
    * @param query the query string (Database SELECT operation).
    * @return if there is a result set or not.
    */
   public boolean runQuery(String query) {
      boolean output = false;
      
      String database = "jdbc:postgresql:metrics";
      String user = "postgres";
      String password = "metricsDb";
      try {
         Connection conn = db.connectToDb(database, user, password);
         Statement stmt = conn.createStatement();
         
         ResultSet result = stmt.executeQuery(query);
         output = result.next();
         stmt.close();
         db.ReleaseDb(conn);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return output;
   }
}
