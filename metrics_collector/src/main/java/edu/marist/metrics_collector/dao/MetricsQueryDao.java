package edu.marist.metrics_collector.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.*;
import edu.marist.metrics_collector.database.helpers.AbstractDatabaseFunctions;
import java.sql.SQLException;

public class MetricsQueryDao {

   public static String getAllMetrics() {
      String url = "jdbc:postgresql:dtmngipt01db";
      String user = "dtmng_ipt01db";
      String password = "111111";
      
      JSONObject json = new JSONObject();
      JSONArray jsonMembers = new JSONArray();

      try {
         AbstractDatabaseFunctions db = new AbstractDatabaseFunctions();  
         Connection conn = db.connectToDb(url, user, password);
         Statement statement = conn.createStatement();

         // change the sql command if necessary
         String sql = "select * from MetricCollection";

         // change the attribute name in table if necessary
         ResultSet rs = statement.executeQuery(sql);
         while (rs.next()) {
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
         db.ReleaseDb(conn);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return json.toString();
   }

}
