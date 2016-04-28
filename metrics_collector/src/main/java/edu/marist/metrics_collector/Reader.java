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
   
   private String Hostname = "";
   
   /**
    * Reader constructor.
    *   Sets the Hostname of the Reader.
    */
   public Reader() {
      Hostname = getHostName();
   }
   /**
    * main method for Reader class also the entrypoint.
    * @param args Command line arguments for entrypoint.
    */
   public static void main(String[] args) {
      Reader reader = new Reader();
      long time0 = System.currentTimeMillis();
      long time1, timeFinal = 0L;
      String[] queryValues;
      ArrayList<String> queries = new ArrayList<>();
      String queryInsert = "INSERT INTO metricdata VALUES ('";
      String queryDiscover = "SELECT pid FROM metricdata WHERE pid = '";
      String queryUpdate = "UPDATE metricdata SET parentpid = '";
      File rootDir = new File("/" + args[0]);
      for(File search : rootDir.listFiles()) {
         String queryAdd = "";
         if(search.getName().matches("\\d+") && readFile(search) != null) {
            boolean noResults = false;
            try {
               DbAccessor db = new DbAccessor();
               noResults = db.runQuery(queryDiscover + search.getName() + "';");
            } catch (Exception e) {
               e.printStackTrace();
            }
            if(!noResults) {
               queryValues = readFile(search);
               queryAdd = queryInsert + 
                          queryValues[0]  + "','" +
                          queryValues[1]  + "','" +
                          reader.Hostname + "','" +
                          queryValues[3]  + "','" +
                          queryValues[23] + "','Now()');";
               queries.add(queryAdd);      
            } else {
               queryValues = readFile(search);
               queryAdd = queryUpdate + 
                          queryValues[3]  + "', totalsize = '" +
                          queryValues[23] + "' WHERE pid = '" +
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
         time1 = System.currentTimeMillis();
         timeFinal = (time1 - time0)/1000L;
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(2);
      }
      
      System.err.println("Reader execution completed in " + timeFinal + " seconds.");
   }
   
   /**
    * readFile method for getting the metric data content.
    * @param in : Input file for metric to be collected.
    * @return String[]: An array of metrics.
    */
   private static String[] readFile(File in) {
      Long pageSize = 4096L;
      String[] out = null;
      BufferedReader fileReader;
      try {
      fileReader = new BufferedReader(new FileReader(
                                         in.getPath()+ "/stat"));
         out = (fileReader.readLine()).split(" ");
         out[23] = ((Long)(Long.parseLong(out[23]) * pageSize)).toString();
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
