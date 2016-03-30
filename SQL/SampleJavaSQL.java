/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author EddeAnthony
 */
public class SampleJavaSQL {

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
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sqlIns = "DELETE FROM MetricCollection WHERE PID = " + pid;
           stmt.executeUpdate(sqlIns);
           stmt.close();
           c.commit();
//           ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
//         while ( rs.next() ) {
//             //"INSERT INTO MetricCollection (PID, processname, machinename, parentPID, totalsize, dateofcreation) VALUES (1, 'testprocess', 'machine1', 0, 01/01/1990);";
//            int id = rs.getInt("PID");
//            String name = rs.getString("processname");
//            int age  = rs.getInt("age");
//            String  address = rs.getString("address");
//            float salary = rs.getFloat("salary");
//         }
         //rs.close();
         stmt.close();
         c.close();           

        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
    }
    
    /**
     * Inserts a metric into the SQL table
     */
    public void insertProcess()
    {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sqlIns = "INSERT INTO MetricCollection (PID, processname, machinename, parentPID, totalsize, dateofcreation) VALUES (1, 'testprocess', 'machine1', 0, 01/01/1990);";
           stmt.executeUpdate(sqlIns);
           stmt.close();
           c.commit();
           c.close();
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
