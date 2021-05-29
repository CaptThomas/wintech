package com.mycompany.wintech;

import com.mycompany.wintech.Goal;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        Connection conn = null;
        try {
            conn = connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Goal[] arr;
        arr = viewTable(conn);
    }
    public static Connection connect() throws ClassNotFoundException {
         Class<?> forName = Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:db.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
     
    /**
     *
     * @param con
     * @return 
     */
    public static Goal[] viewTable(Connection con) {
    String query = "select * from Goals";
    try (java.sql.Statement stmt = con.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      query = "select COUNT(*) from Goals";
      ResultSet rssize = stmt.executeQuery(query);
      int size;
        size = rssize.getInt(1);
      Goal[] arr;
      arr = new Goal[size];
      int x = 0;
      for (rs.next();x<size;x++) {
        arr[x] = new Goal(rs.getInt("iconnum"), rs.getInt("minutesperday"), rs.getString("goalname"), rs.getString("completeddate"));
      }
      System.out.println(Arrays.toString(arr));
      return arr;
    } catch (SQLException e) {
      System.out.println(e);
      return null;
    }
  }
    
}