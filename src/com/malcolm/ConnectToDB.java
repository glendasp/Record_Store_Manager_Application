package com.malcolm;

import com.mysql.jdbc.interceptors.ResultSetScannerInterceptor;

import javax.xml.transform.Result;
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
    static Statement statement2 = null;
    static Statement statementForComboBox = null;
    static Statement statementForSoldRecords = null;
    static Statement statementForConsignerSales=null;
    static Statement statementForConsignerMaintTab=null;

    static Connection conn = null;
    static ResultSet rs1 = null;
    static ResultSet rs2 = null;
    static ResultSet rsForConsingerMaintTab=null;
    static ResultSet rsForSoldRecords=null;
    static ResultSet rsForConsignerSales = null;
    private static Record_Catalog_Display_DataModel catalog_display_dataModel=null;
    private static Record_Catalog_Display_DataModel consigner_Display_Datamodel=null;
    private static Record_Catalog_Display_DataModel sold_Record_Display_Datamodel=null;
    private static Record_Catalog_Display_DataModel consigners_On_Sales_Page=null;


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
                statement2=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statementForSoldRecords=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statementForConsignerSales=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statementForComboBox=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                statementForConsignerMaintTab=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                CreateAllTables createAllTables = new CreateAllTables();
            loadAll_Consigner_Data();
            loadAll_Catalog_Data();
            loadAll_Sold_Records_Data();
            create_Record_Catalog_Data_Models();
            create_Sold_Records_Data_Model_AND_ConsignerSales();
            //TODO: Fix the below so you stop getting resultsset closed situation.
            create_Consigner_Data_Model();


            User_Interface GUI = new User_Interface(catalog_display_dataModel,consigner_Display_Datamodel,sold_Record_Display_Datamodel,consigners_On_Sales_Page);

        }catch (SQLException se){
            System.out.println(se);
            System.out.println("From the ConnectToDB Class");
        }
    }

    protected static void shutDownDBResources() throws SQLException {
        statement.close();
        statement2.close();
        statementForComboBox.close();
        statementForConsignerSales.close();
        statementForSoldRecords.close();
        conn.close();
        rs1.close();
        rs2.close();
        rsForConsignerSales.close();
        rsForSoldRecords.close();

    }
    public static void create_Consigner_Data_Model(){
        try{
            if(rsForConsingerMaintTab!=null){
                rsForConsingerMaintTab.close();
            }
            if(consigner_Display_Datamodel==null){
                rsForConsingerMaintTab=ConnectToDB.statementForConsignerMaintTab.executeQuery("SELECT * FROM consigners;");
                System.out.println("The data model was null, making new consigner datamodel...");
                consigner_Display_Datamodel=new Record_Catalog_Display_DataModel(rsForConsingerMaintTab);
            }
        }catch(SQLException se){
            System.out.println("Found the data model!!!");
            System.out.println(se);
        }
    }
    public static void create_Sold_Records_Data_Model_AND_ConsignerSales(){
        try{
            if (rsForSoldRecords!=null){
                rsForSoldRecords.close();
            }
            if (rsForConsignerSales!=null){
                rsForConsignerSales.close();
            }
            if ((sold_Record_Display_Datamodel==null)){
                rsForSoldRecords=ConnectToDB.statementForSoldRecords.executeQuery("SELECT * FROM record_catalog WHERE Sold_Or_Not = TRUE");
                sold_Record_Display_Datamodel=new Record_Catalog_Display_DataModel(rsForSoldRecords);
                System.out.println("The Sold Records data model was null, making a new Sold Records Data model...");
            }
            if(consigners_On_Sales_Page==null){
                createViewOfConsignerSales();
                rsForConsignerSales=ConnectToDB.statementForConsignerSales.executeQuery("SELECT * FROM consignersales;");
                consigners_On_Sales_Page=new Record_Catalog_Display_DataModel(rsForConsignerSales);
                System.out.println("Data model for consigner sales was null, making new one...");
            }
        }catch(SQLException se){
            System.out.println("Found the data model");
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
            rsForConsingerMaintTab=ConnectToDB.statementForConsignerMaintTab.executeQuery(allDataFromConsignerTable);
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
    public static boolean loadAll_Sold_Records_Data(){
        try{
            String allSoldRecordsData="SELECT * FROM record_catalog WHERE Sold_Or_Not = TRUE";
            rsForSoldRecords=ConnectToDB.statementForSoldRecords.executeQuery(allSoldRecordsData);
            return true;
        }catch(SQLException se){
            System.out.println("Loading all sold records didnt work.");
            return false;
        }
    }
    public static boolean createViewOfConsignerSales(){
        try{
            String create_view="CREATE OR REPLACE VIEW consignerSales (C_ID,Consigner,Current_Sale) as SELECT C_ID,Consigner_Name, SUM(Price*0.4) from consigners con,record_catalog rec  Where con.C_ID=rec.consigners_C_ID AND Sold_Or_Not=True  GROUP BY con.C_ID;";
            rsForConsignerSales=ConnectToDB.statementForConsignerSales.executeQuery(create_view);
            return true;
        }catch (SQLException se){
            System.out.println(se);
            System.out.println("An error making the sales view");
            return false;
        }
    }
}
