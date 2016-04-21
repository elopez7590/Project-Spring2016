/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marist.metrics_collector.database.accessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author EddeAnthony
 */
public class SampleJavaSQL {

    DbAccessor db = null;
    
    public SampleJavaSQL()
    {
       try {
        db = new DbAccessor();
       } catch (Exception e) {
          e.printStackTrace();
       }
    }
    
    /**
     * Updates a process on the table
     */
    public void updateProcess()
    {
        
    }
    
    /**
     * Deletes a process from the table
     */
    public void deleteProcess(int pid)
    {
        Connection c = null;
        Statement stmt = null;
        try {
           db.putData("DELETE FROM MetricCollection WHERE PID = " + pid);      
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * Inserts a metric into the SQL table
     * 
     * @param 
     */
    public void insertProcess(ArrayList<String> row)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            db.putData("INSERT INTO MetricCollection (PID, processname, parentPID, totalsize, dateofcreation) VALUES (" 
                    + Integer.parseInt(row.get(0)) + ", '" + row.get(1)+ "', " + Integer.parseInt(row.get(2)) 
                    + ", " + Integer.parseInt(row.get(3)) + ", '" + row.get(4) + "';");
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      //System.out.println("Opened database successfully");
    }
    
}
