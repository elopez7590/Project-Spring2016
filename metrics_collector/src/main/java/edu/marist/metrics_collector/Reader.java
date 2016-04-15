/**
 * @filename empty.java
 */
package edu.marist.metrics_collector;

import java.io.File;
import java.util.ArrayList;

import edu.marist.metrics_collector.database.accessor.DbAccessor;

/**
 * Metrics Reader class
 * Placeholder
 */
public class Reader {
   public static void main(String[] args) {
      ArrayList<String> queries = new ArrayList<>();
      String query = "INSERT INTO metrics VALUES (";
      File rootDir = new File("/" + args[0]);
      for(File F : rootDir.listFiles()) {
         String queryAdd = query;
         if(F.getName().matches("+d")) {
            
            queries.add();
         }
      }
      try {
         DbAccessor db = new DbAccessor();
         db.putData(query);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
