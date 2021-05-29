package com.mycompany.wintech;

import static com.mycompany.wintech.GoalList.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main {

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        Connection conn = null;
        try {
            conn = connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Goal[] arr;
        arr = viewTable(conn);
    }
}