package com.malcolm;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/2/2015.
 */
public class Record_Catalog_Display_DataModel extends AbstractTableModel {
    private int rowCount = 0;
    private int colCount = 0;
    private ResultSet resultSet;

    public Record_Catalog_Display_DataModel(ResultSet rs){
        this.resultSet=rs;
        setup();
    }
    private void setup(){
        countRows();
    }

    //this updates the jtable with the new requests
public void updateResultsSet(ResultSet newRS){
    resultSet=newRS;
    setup();
}
public boolean deleteRow(int row){
    try{
        resultSet.absolute(row+1);
        resultSet.deleteRow();
        fireTableDataChanged();
        return true;
    }catch(SQLException se){
        System.out.println("Error deleting record.");
        return false;
    }
}

private void countRows(){
    rowCount=0;
    try{
        resultSet.beforeFirst();
        while (resultSet.next()){
            rowCount++;
        }
        resultSet.beforeFirst();
    }catch(SQLException se){
        System.out.println("An Error Occurred Counting Rows. "+se);
    }
}


    @Override
    public int getRowCount() {
        countRows();
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        try{
            colCount=resultSet.getMetaData().getColumnCount();
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("Error created by get column count");
        }
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            System.out.println("Trying to get value at....");
            resultSet.absolute(rowIndex+1);
            Object data=resultSet.getObject(columnIndex+1);
            return data.toString();
        }catch(SQLException se){
            System.out.println(se +" Triggered by getValueAt in datamodel.");
        return se.toString();
        }

    }

    @Override
    //TODO Make this edit cells that can be edited, must use "isEditable" to be established further the line
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
    public boolean isCellEditable(int rowIndex,int colIndex){
        //By havingt this I can edit certain the information within the table
        if(colIndex==1||colIndex==2||colIndex==3||colIndex==5){//// FIXME: Make this a variable, depending on which resultset type is being used
            return true;
        }else {
            return false;
        }
    }
    public boolean insert_Consigner_To_List(String consigner_Name,String address,String E_Mail,String banking_Number){
        PreparedStatement ps = null;
        try{
            String prep_Insert_String="INSERT INTO consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number)VALUES(?,?,?,?)";
            resultSet.moveToInsertRow() ;
            ps=ConnectToDB.conn.prepareStatement(prep_Insert_String);
            ps.setString(1,consigner_Name);
            ps.setString(2,address);
            ps.setString(3,E_Mail);
            ps.setString(4,banking_Number);
            ps.executeUpdate();
            this.fireTableDataChanged();
            ps.close();
            JOptionPane.showMessageDialog(null,"You successfully added "+consigner_Name+", Address: "+address+", E-mail: "+E_Mail+", Routing Number: "+banking_Number+".");
            System.out.println(String.format("You added"));
            return true;
        }catch(SQLException se){
            System.out.println("Insertion of Consigner Information Failed");
            System.out.println(se);
            return false;
        }
    }
    public boolean insert_Record_To_Catalog(String artistName,String albumTitle,double price,String shelvedDay,boolean soldOrNot,int consignerID){
        PreparedStatement ps = null;
        try{

            //insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-03-15',false,5);

            String preparedInsertString="INSERT INTO record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) VALUES(?,?,?,?,?,?)";
            resultSet.moveToInsertRow();
            ps=ConnectToDB.conn.prepareStatement(preparedInsertString);
            ps.setString(1,artistName);
            ps.setString(2,albumTitle);
            ps.setDouble(3,price);
            ps.setString(4,shelvedDay);
            ps.setBoolean(5,soldOrNot);
            ps.setInt(6,consignerID);
            ps.executeUpdate();
            this.fireTableDataChanged();
            //FIXME: I know this is a terrible solution for the issue, but it works for now.
            this.fireTableDataChanged();
            ps.close();
            return true;
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("An error occurred adding a row.");
            return false;
            }
    }
    public boolean sellRecord(String record_ID){
        PreparedStatement ps = null;
        int r_id=Integer.parseInt(record_ID);
        String sell_Record_SQL="UPDATE record_catalog SET Sold_Or_Not = true WHERE Record_ID = ? ;";
        try{
            ps=ConnectToDB.conn.prepareStatement(sell_Record_SQL);
            ps.setInt(1,r_id);
            ps.executeUpdate();
            this.fireTableDataChanged();
            search("Default","",0);
            return true;
        }catch(SQLException se){
            System.out.println("An error occurred when selling the record.");
            System.out.println(se);
            return false;
        }


    }
    public String getColumnName(int colIndex){
        try{
            //
            return resultSet.getMetaData().getColumnName(colIndex+1);

        }catch(SQLException se){
            System.out.println("Error caused by getColumnName of data model");
            return "ERROR IN COLUMN GETTER";
        }
    }
                                                    //0=catalog,1=consigners,2=sales page,3=//todo
    public void search(String selField, String searchString, int tabIndex) {
        /** The ideas behind this method are entirely thanks to the genius of Anna**/

        if(tabIndex==0){
            if (selField.equals("Default")) {
                try {
                    this.resultSet = ConnectToDB.statement.executeQuery("SELECT * FROM " + CreateAllTables.RECORD_CATALOG_TABLE_NAME + " WHERE Sold_Or_Not = FALSE AND Archived_Or_Not=FALSE ; ");
                }catch(SQLException se){
                    System.out.println("Error resetting to default results");
                    System.out.println(se);
                }
            } else {

                String sqlToRun = "SELECT * FROM record_catalog WHERE " +
                        selField + " LIKE ?";
                PreparedStatement ps = null;
                try {
                    ps = ConnectToDB.conn.prepareStatement(sqlToRun);
                    ps.setString(1, "%" + searchString + "%");
                    this.resultSet = ps.executeQuery();
                } catch (SQLException sqle) {
                    System.out.println("Unable to fetch search results.");
                }
            }
            this.fireTableDataChanged();
        }//Search the consigners table
        else if(tabIndex==1){
            if(selField.equals("Default")){
                try{
                    this.resultSet = ConnectToDB.statementForConsignerMaintTab.executeQuery("SELECT * FROM consigners");
                    this.fireTableDataChanged();
                }catch(SQLException se){
                    System.out.println("An error ocurred while trying reset to default search.");
                    System.out.println(se);
                }
            }else {
                String sqlToRun="SELECT * FROM consigners WHERE "+selField+" LIKE ?";
                PreparedStatement ps = null;
                try{
                    ps=ConnectToDB.conn.prepareStatement(sqlToRun);
                    ps.setString(1,"%"+searchString+"%");
                    this.resultSet=ps.executeQuery();
                }catch(SQLException se){
                    System.out.println("Unable to fetch search results");
                    System.out.println(se);
                }
            }
        this.fireTableDataChanged();
        }
        else if(tabIndex==2){
            //this is the sold records updater
            if(selField=="Default"){
                try{
                    this.resultSet=ConnectToDB.statementForSoldRecords.executeQuery("SELECT * FROM record_catalog WHERE Sold_Or_Not=TRUE AND Archived_Or_Not=FALSE ");
                }catch (SQLException se){
                    System.out.println(se);

                }
            }
        }
        else if(tabIndex==3){
            if(selField=="Default"){
                try{
                    this.resultSet=ConnectToDB.statementForConsignerSales.executeQuery("SELECT * FROM consignerSales");
                }catch (SQLException se){
                    System.out.println(se);

                }
            }
        }
        else if(tabIndex==4){
            //TODO put a final pan of archived sales.
        }
    }
    public void searchPrice(String selField, double searchPrice) {
        /** The ideas behind this method are entirely thanks to the genius of Anna**/
        String sqlToRun = "SELECT * FROM record_catalog WHERE " +
                selField + " <= ?";
        PreparedStatement ps = null;
        try {
            ps = ConnectToDB.conn.prepareStatement(sqlToRun);
            ps.setDouble(1, searchPrice);
            this.resultSet = ps.executeQuery();
        } catch (SQLException sqle) {
            System.out.println("Unable to fetch search results.");
            System.out.println(sqle);
        }

        this.fireTableDataChanged();
    }


}
