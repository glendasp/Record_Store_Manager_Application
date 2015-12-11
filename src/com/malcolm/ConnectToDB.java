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
    static ResultSet rs1 = null;
    static ResultSet rs2 = null;
    private static Record_Catalog_Display_DataModel catalog_display_dataModel=null;
    private static Consigner_Display_DataModel consigner_Display_Datamodel=null;

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
            loadAll_Consigner_Data();
            loadAll_Catalog_Data();
            create_Record_Catalog_Data_Models();
            //TODO: Fix the below so you stop getting resultsset closed situation.
            //create_Consigner_Data_Model();


            User_Interface GUI = new User_Interface(catalog_display_dataModel,consigner_Display_Datamodel);

        }catch (SQLException se){
            System.out.println(se);
            System.out.println("From the ConnectToDB Class");
        }
    }

    protected static void shutDownDBResources() throws SQLException {
        statement.close();
        conn.close();
        rs1.close();
        rs2.close();
    }
    public static void create_Consigner_Data_Model(){
        try{
            if(rs2!=null){
                rs2.close();
            }
            if(consigner_Display_Datamodel==null){
                rs2=ConnectToDB.statement.executeQuery("SELECT * FROM consigners;");
                System.out.println("The data model was null, making new consigner datamodel...");
                consigner_Display_Datamodel=new Consigner_Display_DataModel(rs2);
            }
        }catch(SQLException se){
            System.out.println("Found the data model!!!");
            System.out.println(se);
        }
    }
    public static void create_Record_Catalog_Data_Models(){
        try{
            if (rs1!=null){
                rs1.close();
            }
            if(catalog_display_dataModel==null){
                System.out.println("The data model was null, making new record catalog model...");
                rs1=ConnectToDB.statement.executeQuery("SELECT * FROM record_catalog WHERE Sold_Or_Not = FALSE");
                catalog_display_dataModel=new Record_Catalog_Display_DataModel(rs1);

            }
            else{
                System.out.println("Found the data model!!!");
                catalog_display_dataModel.updateResultsSet(rs1);
            }
        }catch(SQLException se) {
            System.out.println("Error with Create RecordCatalog Method has ocurred.");
            System.out.println(se);
        }
    }
    public static boolean loadAll_Consigner_Data(){
        try{
            String allDataFromConsignerTable="SELECT * FROM "+CreateAllTables.CONSIGNER_TABLE_NAME+";";
            rs2=ConnectToDB.statement.executeQuery(allDataFromConsignerTable);
            return true;
        }catch(SQLException se){
            System.out.println("Error Loading Consigner Data");
            return false;
        }
    }
    public static boolean loadAll_Catalog_Data(){
        try{
            String allDataFromRecordCatalog="SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Sold_Or_Not = FALSE ; ";
            rs1=ConnectToDB.statement.executeQuery(allDataFromRecordCatalog);
            return true;
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("Error loading record catalog");
            return  false;
        }
    }
}
