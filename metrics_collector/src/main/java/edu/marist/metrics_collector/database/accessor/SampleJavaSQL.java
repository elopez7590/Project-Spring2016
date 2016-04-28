/**
 * 
 * @filename: SampleJavaSQL
 */
package edu.marist.metrics_collector.database.accessor;

import java.util.ArrayList;

/**
 *
 * @author EddeAnthony
 */
public class SampleJavaSQL {

    DbAccessor db = null;
    int interval = 15; //15 seconds is the default
    
    /**
     * SampleJavaSQL constructor.
     */
    public SampleJavaSQL()
    {
       try {
        db = new DbAccessor();
       } catch (Exception e) {
          e.printStackTrace();
       }
    }
    
    /**
     * updateProcess method, Updates a process in the database.
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
     * deleteProcess method, Deletes a process from the database.
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
     * Inserts a metric into the SQL database.
     * 
     * @param row String:  The metric and information to be sent to the database.
     */
    public void insertProcess(String row)
    {
        String[] data = row.split(",");
        try {
            db.putData("INSERT INTO MetricCollection (PID, processname, machinename, parentPID, totalsize, dateofcreation) VALUES (" 
                    + Integer.parseInt(data[0]) + ", '" + data[1] + "', '" + data[2] + "', " + Integer.parseInt(data[3]) 
                    + ", " + Long.parseLong(data[4]) + ", '" + data[5] + "');");
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * Create a new table with a specified name.
     * 
     * @param tableName String:  The metric and information to be sent to the database
     *                           for making a new table.
     */
    public void createTable(String tableName)
    {
        try {
            String ct = "CREATE TABLE IF NOT EXISTS " + tableName + " (PID char(10) not null, processname char(50) not null, machinename char(50) not null, parentPID char(10), totalsize bigint, dateofcreation date, PRIMARY KEY(PID,processname,machinename));";
            db.putData(ct);
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * Send ALL data from one table to the GUI.
     * TODO: Interface with GUI.
     * 
     * @param tableName String:  The table to be displayed on the GUI.
     */
    public void sendData(String tableName)
    {
        String query = "SELECT * FROM " + tableName;
        ArrayList<String> data = db.getData(query);
    }
    
    /**
     * @param args String[]: The command line argument(s).
     */
    public static void main(String[] args) {
        
      //System.out.println("Opened database successfully");
    }
    
}
