/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marist.metrics_collector.database.helpers;

import java.sql.*;

/**
 *
 * @author Anthony Cali
 */
public class AbstractDatabaseFunctions {
   
   public AbstractDatabaseFunctions() throws ClassNotFoundException {
      Class.forName("org.postgresql.Driver");
   }
   
   public Connection connectToDb(String database, String user, String password) throws SQLException {
      Connection db = DriverManager.getConnection(database, user, password);
      return db;
   }
   
   public void ReleaseDb(Connection db) throws SQLException {
      db.close();
   }
   
   
}
