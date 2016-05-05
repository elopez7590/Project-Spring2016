package edu.marist.metrics_collector.database.helpers;

import java.sql.*;

/**
 *
 * @author Anthony Cali, Edde Lopez
 * 
 * AbstractDatabaseFunctions class and implementation.
 */
public class AbstractDatabaseFunctions {
   
   /**
    * 
    * AbstractDatabaseFunctions constructor populates the database class.
    * @throws ClassNotFoundException 
    */
   public AbstractDatabaseFunctions() throws ClassNotFoundException {
      Class.forName("org.postgresql.Driver");
   }
   
   /**
    * 
    * connectToDb method returns a connection to the target database using
    *     specified credentials.
    * @param database String: The target database.
    * @param user String: The user name.
    * @param password String: The user password.
    * @return Connection: The database connection.
    * @throws SQLException 
    */
   public Connection connectToDb(String database, String user, String password) throws SQLException {
      Connection db = DriverManager.getConnection(database, user, password);
      return db;
   }
   
   /**
    * 
    * ReleaseDb method closes the database connection.
    * @param db Connection: The database connection to be closed.
    * @throws SQLException 
    */
   public void ReleaseDb(Connection db) throws SQLException {
      db.close();
   }
   
   
}
