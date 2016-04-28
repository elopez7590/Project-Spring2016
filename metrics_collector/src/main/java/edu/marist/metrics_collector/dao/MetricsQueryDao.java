/**
 * 
 * @filename MetricsQueryDao.java
 * @author Hongchao Young
 * @editor Anthony Cali, Edde Lopez
 */
package edu.marist.metrics_collector.dao;

import org.json.*;
import edu.marist.metrics_collector.database.accessor.DbAccessor;
import java.util.ArrayList;

/**
 * 
 * MetricsQueryDao class definition and implementation.
 * 
 */
public class MetricsQueryDao {
   
   /**
    * getAllMetrics method for getting the database tables as
    *               JSON for the GUI.
    * @return static String: The JSON as a string. 
    */
   public static String getAllMetrics() {
      
      JSONObject json = new JSONObject();
      JSONArray jsonMembers = new JSONArray();

      try {
         DbAccessor db = new DbAccessor();  
         String sql = "select * from MetricCollection";
         ArrayList<String> results = new ArrayList<>();
         results = db.getData(sql);         
         
         int index = 0;
         // change the attribute name in table if necessary
         while (index < results.size()) {
            String[] s = results.get(index).split(","); 
            JSONObject proJSON = new JSONObject();
            proJSON.put("PID", s[0]);
            proJSON.put("processname", s[1]);
            proJSON.put("machinename", s[2]);
            proJSON.put("parentPID", s[3]);
            proJSON.put("totalsize", s[4]);
            proJSON.put("dateofcreation", s[5]);
            jsonMembers.put(proJSON);
         }
         json.put("metrics", jsonMembers);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return json.toString();
   }

}
