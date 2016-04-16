/**
 * @filename empty.java
 */
package edu.marist.metrics_collector;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


import edu.marist.metrics_collector.database.accessor.DbAccessor;


/**
 * Metrics Reader class
 * Placeholder
 */
public class Reader {
   public static void main(String[] args) {
      String[] queryValues;
      ArrayList<String> queries = new ArrayList<>();
      String query = "INSERT INTO metricdata VALUES ('";
      File rootDir = new File("/" + args[0]);
      File[] C = rootDir.listFiles();
      for(File F : rootDir.listFiles()) {
         String queryAdd = "";
         if(F.getName().matches("\\d+")) {
            if(readFile(F) != null) {
               queryValues = readFile(F);
               queryAdd = query + 
                          queryValues[0]  + "','" +
                          queryValues[1]  + "','" +
                          getHostName()   + "','" +
                          queryValues[3]  + "','" +
                          Long.parseLong(queryValues[22]) + "','Now()')";
               queries.add(queryAdd);
                          
            } 
         }
      }
      try {
         DbAccessor db = new DbAccessor();
         for(String queryOut : queries) {
            if(db.putData(queryOut)) {
               System.err.println("Database busy will retry");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(2);
      }
   }
   
   private static String[] readFile(File in) {
      String[] out = null;
      BufferedReader toRead;
      try {
      toRead = new BufferedReader(new FileReader(
                                         in.getPath()+ "/stat"));
         out = (toRead.readLine()).split(" ");
         toRead.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("File access error");
         System.exit(1);
      }
      return out;
   }
   
   private static String getHostName() {
      try {
         Runtime r = Runtime.getRuntime();
         Process p = r.exec("uname -n");
         BufferedReader x = new BufferedReader(new InputStreamReader(p.getInputStream()));
         return x.readLine();
      } catch (Exception e) {
         e.printStackTrace();
      } 
      return "Unknown";
   }
}
