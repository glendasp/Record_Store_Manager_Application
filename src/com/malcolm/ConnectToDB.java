package com.malcolm;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class ConnectToDB {
    private static final String JDBC_Driver="com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "record_store";
    private static final String USER = "root";
    private static final String PASS = "1362WminnehahaAve";

    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;

    public ConnectToDB(){
        try {
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found! Exiting the program.");
                System.out.println(cnfe);
            }

                conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                CreateAllTables createAllTables = new CreateAllTables();

        }catch (SQLException se){
            System.out.println(se);
            System.out.println("From the ConnectToDB Class");
        }
    }

}
