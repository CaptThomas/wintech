
import java.beans.*;
import java.sql.*;
import java.util.Arrays;

/*
 * The MIT License
 *
 * Copyright 2021 Thomas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author Thomas
 */

public class GoalList {
     public static Connection connect() {
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
    public static void newGoal(Connection con, int iconnum, int minutes, String goalname) throws SQLException{
        if (goalname.length() > 255) {
            goalname = goalname.substring(0, 254);
        }
        String query;
         query = String.format("INSERT INTO Goals VALUES ('%s', %d, '-1', %d);", goalname, minutes, iconnum);
        try (java.sql.Statement stmt = con.createStatement()) {
            stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
