package com.mycompany.wintech;

import com.mycompany.wintech.Goal;
import com.mycompany.wintech.GoalList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main {

    /**
     *
     * @param args
     */
    public main(String args[]) {
        Connection conn = null;
        try {
            conn = GoalList.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Goal[] arr;
        arr = GoalList.viewTable(conn);
    }
}