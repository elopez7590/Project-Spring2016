/**
 * 
 * @filename: SampleJavaSQL
 */
package edu.marist.metrics_collector.database.accessor;

import java.util.ArrayList;
import java.util.List;

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
       }
    }
    

    /**
     * getAllData  Gets all rows from a table
     * 
     * @return String List: The row data. 
     */
    public List<String> getAllData()
    {
        List<String> al = new ArrayList<>();
        try {
           al = db.getData("SELECT * FROM metrics");      
        } catch (Exception e) {
           System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        
        return al;
    }
    
    /**
     * deleteProcess method, Deletes a process from the database.
     */
    public void deleteProcess(int pid)
    {
        try {
           db.putData("DELETE FROM metrics WHERE PID = " + pid);      
        } catch (Exception e) {
           System.err.println(e.getClass().getName()+": "+e.getMessage());
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
            db.putData("INSERT INTO metrics (PID, processname, machinename, parentPID, totalsize, dateofcreation) VALUES (" 
                    + Integer.parseInt(data[0]) + ", '" + data[1] + "', '" + data[2] + "', " + Integer.parseInt(data[3]) 
                    + ", " + Long.parseLong(data[4]) + ", '" + data[5] + "');");
        } catch (Exception e) {
           System.err.println(e.getClass().getName()+": "+e.getMessage());
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
        } catch (Exception e) {;
           System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
    
}
