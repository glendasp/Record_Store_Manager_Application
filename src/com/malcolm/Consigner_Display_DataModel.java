package com.malcolm;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.table.AbstractTableModel;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/10/2015.
 */
public class Consigner_Display_DataModel extends AbstractTableModel{
    private int rowCount=0;
    private int colCount=0;
    private ResultSet resultSet;

    public Consigner_Display_DataModel(ResultSet rs){
        this.resultSet=rs;
        setup();
    }
    private void setup(){
        countRows();
    }
    public void updateResultSet(ResultSet newRS){
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
            System.out.printf("The record was not deleted.");
            System.out.println(se);
            return false;
        }
    }
    private void countRows(){
        rowCount=0;
        try{
            resultSet.beforeFirst();
            while(resultSet.next()){
                rowCount++;
            }
        }catch(SQLException se){
            System.out.println("An error occurred while Data model was counting rows.");
            System.out.println(se);
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
            System.out.println("Error while gettColumnCount for Consigner data table.");
            System.out.println(se);
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            System.out.println("Getting Consigner Data....");
            resultSet.absolute(rowIndex+1);
            Object data = resultSet.getObject(columnIndex+1);
            return data.toString();
        }catch(SQLException se){
            System.out.println("Cannot getValueAt for consigner Table");
            System.out.println(se);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue,int rowIndex,int colIndex){
        super.setValueAt(aValue,rowIndex,colIndex);
    }
    public boolean isCellEditable(int rowIndex,int colIndex){
        if (colIndex>=1){
            return true;
        }else{
            return false;
        }
    }
    public boolean insert_Consigner_To_List(String consigner_Name,String address,String E_Mail,String banking_Number){
        PreparedStatement ps = null;
        try{
            String prep_Insert_String="INSERT INTO consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number)VALUES(?,?,?,?)";
            resultSet.moveToInsertRow();
            ps=ConnectToDB.conn.prepareStatement(prep_Insert_String);
            ps.setString(1,consigner_Name);
            ps.setString(2,address);
            ps.setString(3,E_Mail);
            ps.setString(4,banking_Number);
            ps.executeUpdate();
            fireTableDataChanged();
            ps.close();
            return true;
        }catch(SQLException se){
            System.out.println("Insertion of Consigner Information Failed");
            System.out.println(se);
            return false;
        }
    }


}
