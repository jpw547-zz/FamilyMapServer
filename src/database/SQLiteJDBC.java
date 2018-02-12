package database;

import java.sql.*;

public class SQLiteJDBC {
  public static void main( String args[] ) {
      Connection c = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         //SQLiteJDBC jdbc = new SQLiteJDBC();
         c = DriverManager.getConnection("jdbc:sqlite:fmdb.db");
         c.setAutoCommit(false);
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      
      try {
    	  Statement stmt = c.createStatement();
          String sql = "INSERT INTO USERS (userName, password, email, firstName, lastName, gender) " + "VALUES ('klinc9', 'testy', 'andrew_werner@byu.edu', 'Andrew', 'Werner', 'M');"; 
          stmt.executeUpdate(sql);
          
          stmt.close();
          c.commit();
          c.close();
      } catch(SQLException s) {
    	  System.err.println( s.getClass().getName() + ": " + s.getMessage() );
          System.exit(0);
      }
      
      System.out.println("Records created successfully");
   }
}