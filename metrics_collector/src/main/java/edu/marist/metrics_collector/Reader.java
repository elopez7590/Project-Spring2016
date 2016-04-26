/**
 * @authors Anthony Cali, Edde Lopez
 * 
 * @filename Reader.java
 * @version 0.8
 */
package edu.marist.metrics_collector;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


import edu.marist.metrics_collector.database.accessor.DbAccessor;


/**
 * Reader class and implementation.
 * 
 */
public class Reader {
   /**
    * main method for Reader class also the entrypoint.
    * @param args Command line arguments for entrypoint.
    */
   public static void main(String[] args) {
      String[] queryValues;
      ArrayList<String> queries = new ArrayList<>();
      String queryInsert = "INSERT INTO metricdata VALUES ('";
      String queryDiscover = "SELECT pid FROM metricdata WHERE pid = '";
      String queryUpdate = "UPDATE metricdata SET parentpid = '";
      File rootDir = new File("/" + args[0]);
      for(File search : rootDir.listFiles()) {
         String queryAdd = "";
         if(search.getName().matches("\\d+")) {
            boolean noResults = false;
            try {
               DbAccessor db = new DbAccessor();
               noResults = db.runQuery(queryDiscover + search.getName() + "';");
            } catch (Exception e) {
               e.printStackTrace();
            }
            if(readFile(search) != null && !noResults) {
               queryValues = readFile(search);
               queryAdd = queryInsert + 
                          queryValues[0]  + "','" +
                          queryValues[1]  + "','" +
                          getHostName()   + "','" +
                          queryValues[3]  + "','" +
                          queryValues[22] + "','Now()');";
               queries.add(queryAdd);      
            } else if (readFile(search) != null && noResults) {
               queryValues = readFile(search);
               queryAdd = queryUpdate + 
                          queryValues[3]  + "', totalsize = '" +
                          queryValues[22] + "' WHERE pid = '" +
                          queryValues[0]  + "';";
               queries.add(queryAdd);
               
            }
         }
      }
      try {
         DbAccessor db = new DbAccessor();
         for(String queryOut : queries) {
            if(db.putData(queryOut)) {
               System.err.println("Database error will retry");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(2);
      }
   }
   
   /**
    * readFile method for getting the metric data content.
    * @param in : Input file for metric to be collected.
    * @return String[]: An array of metrics.
    */
   private static String[] readFile(File in) {
      String[] out = null;
      BufferedReader fileReader;
      try {
      fileReader = new BufferedReader(new FileReader(
                                         in.getPath()+ "/stat"));
         out = (fileReader.readLine()).split(" ");
         fileReader.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("File access error");
      }
      return out;
   }
   
   /**
    * getHostName method for getting the machine (host) name.
    * @return String: The machine (host) name or Unknown.
    */
   private static String getHostName() {
      String out = "Unknown";
      String filepath = "/proc/sys/kernel/hostname";
      BufferedReader hostReader;
      try {
         hostReader = new BufferedReader(new FileReader(filepath));
         out = hostReader.readLine();
         hostReader.close();
      } catch (Exception e) {
         e.printStackTrace();
      } 
      return out;
   }
}
