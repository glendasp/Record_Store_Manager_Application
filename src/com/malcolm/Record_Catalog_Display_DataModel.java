package com.malcolm;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/2/2015.
 */
public class Record_Catalog_Display_DataModel extends AbstractTableModel {
    private int rowCount = 0;
    private int colCount = 0;
    ResultSet resultSet;

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
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            resultSet.absolute(rowIndex+1);
            Object singleRowOfData=resultSet.getObject(columnIndex+1);
            return singleRowOfData.toString();
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
        if(colIndex==666){//// FIXME: Make this a variable, depending on which resultset type is being used
            return true;
        }
        return false;
    }
    public boolean insert_Record_To_Catalog(String artistName,String albumTitle,double price,String shelvedDay,boolean soldOrNot,int consignerID){
        try{
            resultSet.moveToInsertRow();
            //TODO add the rest of the record info
            resultSet.updateString(Main.RECORD_CATALOG_ARTIST,artistName);
            resultSet.updateString(Main.RECORD_CATALOG_ALBUM,albumTitle);
            resultSet.updateDouble(Main.RECORD_CATALOG_PRICE,price);
            resultSet.updateString(Main.RECORD_CATALOG_SHELVED,shelvedDay);
            resultSet.updateBoolean(Main.RECORD_CATALOG_SOLD,soldOrNot);
            resultSet.updateInt(Main.RECORD_CATALOG_CONSIGNER,consignerID);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            return true;
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("An error occurred adding a row.");
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


}
