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

    private static Record_Catalog_Display_DataModel catalog_display_dataModel=null;

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
            create_Record_Catalog_Data_Model();

            User_Interface GUI = new User_Interface(catalog_display_dataModel);

        }catch (SQLException se){
            System.out.println(se);
            System.out.println("From the ConnectToDB Class");
        }
    }

    protected static void shutDownDBResources() throws SQLException {
        statement.close();
        conn.close();
        rs.close();
    }
    public static void create_Record_Catalog_Data_Model(){
        try{
            if (rs!=null){
                rs.close();
            }
            if(catalog_display_dataModel==null){
                System.out.println("The data model was null, making new one...");
                rs=ConnectToDB.statement.executeQuery("SELECT * FROM record_catalog WHERE Sold_Or_Not = FALSE");
                catalog_display_dataModel=new Record_Catalog_Display_DataModel(rs);

            }
            else{
                System.out.println("Found the data model!!!");
                catalog_display_dataModel.updateResultsSet(rs);
            }
        }catch(SQLException se) {

        }
    }
    public static boolean loadAll_Catalog_Data(){
        try{
            String allDataFromRecordCatalog="SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Sold_Or_Not = FALSE ; ";
            rs=ConnectToDB.statement.executeQuery(allDataFromRecordCatalog);
            return true;
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("Error loading record catalog");
            return  false;
        }
    }
}
