/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.java.sql;

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
        db = new DbAccessor();
    }
    
    /**
     * Updates a process on the table
     */
    public void updateProcess(int pid, String date)
    {
        try {
           //db.putData("UPDATE MetricCollection SET WHERE PID = " + pid);      
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * Deletes a process from the table
     */
    public void deleteProcess(int pid)
    {
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
    public void insertProcess(String row)
    {
        String[] data = row.split(",");
        try {
            db.putData("INSERT INTO MetricCollection (PID, processname, machinename, parentPID, totalsize, dateofcreation) VALUES (" 
                    + Integer.parseInt(data[0]) + ", '" + data[1] + "', '" + data[2] + "', " + Integer.parseInt(data[3]) 
                    + ", " + Integer.parseInt(data[4]) + ", '" + data[5] + "';");
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
