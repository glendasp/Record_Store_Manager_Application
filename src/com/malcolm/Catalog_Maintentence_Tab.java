package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class Catalog_Maintentence_Tab extends JPanel{
    private JPanel catalog_Maint_Panel;
    private JButton addRecordButton;

    private JTable recordCatalogDisplayJTable;

    private JTextField artistNameTextField;
    private JTextField albumTitleTextField;
    private JTextField priceTextField;
    private JScrollPane containsTheJTable;
    private JTextField yearTextField;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JComboBox ConsignerComboBox;
    private JLabel consignerComboBoxLabel;
    private JButton deleteRecordButton;
    private JButton sellSelectedRecordButton;
    private JTextField searchParameterTextField;
    private JButton searchRecordsByButton;
    private JComboBox searchParamComboBox;
    //The combo box for choosing consigner choices
    //FIXME THIS NEEDS TO BE BETTER. MAKE LINK LIST FOR COMBOBOX. SELECT COLX, COLY From consigners. Save to hashmap? LinkedList?
    final private String comboOp0 = "Select Consigner";
    final private String comboOp1 = "(1) Cheapo Disc";
    final private String comboOp2 ="(2) Cheapo Disc";
    final private String comboOp3 ="(3) Treehouse Records";
    final private String comboOp4 ="(4) Raving Als Records";
    final private String comboOp5 ="(5) Tattersails Discs";
    final private String comboOp6 ="(6) Forkrul Assail Warren of Tunes";
    final private String comboOp7 ="(7) Glibibdoolblips Emporium";
    final private String comboOp8 ="(8) Cheapo Disc";
    final private String comboOp9 ="(9) Kurald Galein";
    final private String comboOp10 ="(10) Kurald Immourlan";
    final private String comboOp11 ="(11) Thiure Galein";

    //the comboBox options for Searching Records
    final private String searchOp0="Default";
    final private String searchOp1 = "Artist Name";
    final private String searchOp2 = "Album Title";
    final private String searchOp3 = "Price";

    Catalog_Maintentence_Tab(final Record_Catalog_Display_DataModel catalogDisplayDataModel) {
    //I am super proud of this particular bit here, It makes the comboBox and populates it... uncertain if it updates, probably doesn't.
        //TODO : MAKE THIS A METHOD SO IT IS CALLED EVERYTIME A CONSIGNER IS ADDED
        try {
            ResultSet consignerRS = ConnectToDB.statementForComboBox.executeQuery("SELECT C_ID,Consigner_Name FROM consigners;");
            while(consignerRS.next()==true){
                final String x = consignerRS.getString(1);
                final String y = consignerRS.getString(2);
                final String combined = x+":"+y;
                ConsignerComboBox.addItem(combined);
            }
        }catch(SQLException se){
            System.out.println("Error while grabbing consigner names.");
        }

//        ConsignerComboBox.addItem(comboOp1);
//        ConsignerComboBox.addItem(comboOp2);
//        ConsignerComboBox.addItem(comboOp3);
//        ConsignerComboBox.addItem(comboOp4);
//        ConsignerComboBox.addItem(comboOp5);
//        ConsignerComboBox.addItem(comboOp6);
//        ConsignerComboBox.addItem(comboOp7);
//        ConsignerComboBox.addItem(comboOp8);
//        ConsignerComboBox.addItem(comboOp9);
//        ConsignerComboBox.addItem(comboOp10);
//        ConsignerComboBox.addItem(comboOp11);
        //add potential search parameters
        searchParamComboBox.addItem(searchOp0);
        searchParamComboBox.addItem(searchOp1);
        searchParamComboBox.addItem(searchOp2);
        searchParamComboBox.addItem(searchOp3);
        //set table to display the stuff
        recordCatalogDisplayJTable.setGridColor(Color.BLACK);
        recordCatalogDisplayJTable.setModel(catalogDisplayDataModel);


        addRecordButton.addActionListener(new ActionListener() {

            String artistName="";
            String albumName="";
            double price = 0;
            String year = "";
            String month = "";
            String day = "";
            boolean soldOrNot=false;
            String completeDateInfo="";
            int c_ID;
            @Override
            public void actionPerformed(ActionEvent e) {
                int slotCount=0;
                String split_C_ID="";
                String comboBoxSplitList=(String)ConsignerComboBox.getSelectedItem();
                String[] littleList=new String[2];
                for(String piece:comboBoxSplitList.split(":")){
                    littleList[slotCount]=piece;
                    slotCount++;
                }


//                if(ConsignerComboBox.getSelectedItem().equals(comboOp0)){
//                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"Please choose a consigner before adding a record!");
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp1)){
//                    c_ID=1;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp2)){
//                    c_ID=2;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp3)){
//                    c_ID=3;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp4)){
//                    c_ID=4;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp5)){
//                    c_ID=5;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp6)){
//                    c_ID=6;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp7)){
//                    c_ID=7;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp8)){
//                    c_ID=8;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp9)){
//                    c_ID=9;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp10)){
//                    c_ID=10;
//                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp11)){
//                    c_ID=11;
//                }
                c_ID=Integer.parseInt(littleList[0]);
                albumName=albumTitleTextField.getText();
                price= Double.parseDouble(priceTextField.getText());
                year=yearTextField.getText();
                month=monthTextField.getText();
                day=dayTextField.getText();
                completeDateInfo=year+"-"+month+"-"+day;
                //TODO add the C_ID If statemnts here.  Better yet, make a method.
                artistName = artistNameTextField.getText();
                catalogDisplayDataModel.insert_Record_To_Catalog(artistName,albumName,price,completeDateInfo,soldOrNot,c_ID);
            ConsignerComboBox.setSelectedItem(comboOp0);
                albumTitleTextField.setText("");
                yearTextField.setText("");
                monthTextField.setText("");
                dayTextField.setText("");
                artistNameTextField.setText("");
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
//This dialog box is the best thing ever.
                int n=JOptionPane.showConfirmDialog(null,"Are you certain you want to delete that record?","WARNING!",JOptionPane.YES_NO_OPTION);
                if (n==0){

                int currentRow = recordCatalogDisplayJTable.getSelectedRow();
                if(currentRow == -1){
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"Please choose a record to delete!");
                }
                boolean deleted = catalogDisplayDataModel.deleteRow(currentRow);
                if(deleted) {
                    ConnectToDB.loadAll_Catalog_Data();
                }
                }else if(n==1){
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"The selected row was not deleted for some reason.");
                }
            }
        });
        searchRecordsByButton.addActionListener(new ActionListener() {
            double price=0;
            String searchParam="";
            @Override
            public void actionPerformed(ActionEvent e) {

                    if(searchParamComboBox.getSelectedItem().equals(searchOp0)){
                        catalogDisplayDataModel.search("Default","");
                        System.out.println("Default Settings re-established");
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp1)){
                        searchParam = searchParameterTextField.getText();
                        catalogDisplayDataModel.search("Artist_Name",searchParam);
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp2)){
                        searchParam = searchParameterTextField.getText();
                        catalogDisplayDataModel.search("Album_Title",searchParam);
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp3)) {
                        price = Double.parseDouble(searchParameterTextField.getText());
                        catalogDisplayDataModel.searchPrice("Price",price);
                    }catalogDisplayDataModel.fireTableDataChanged();
                   //catalogDisplayDataModel.updateResultsSet();
            }
        });
        sellSelectedRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
/**  When you left off you were looking to figure out how to just get the
 * Record_ID of the selected record and then alter that single record with
 * a prepared statement.  Shouldn't be too hard, use the datamodel and make the SQL do the hard work.**/

                int n = JOptionPane.showConfirmDialog(null, "Are you certain you want to sell that record?", "WARNING!", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    int rowIndex;int colIndex;
                    rowIndex=recordCatalogDisplayJTable.getSelectedRow();
                    System.out.println("The row I grabbed :"+rowIndex);
                    colIndex=recordCatalogDisplayJTable.getColumnCount();
                    System.out.println("The column I just grabbed :"+colIndex);
                    //this grabs the ID number of the record so that I can execute the Data Models 'Sell Record' method.
                    String i = (String)catalogDisplayDataModel.getValueAt(rowIndex,0);
                    if(catalogDisplayDataModel.sellRecord(i)){
                        JOptionPane.showMessageDialog(catalog_Maint_Panel,"Record has been sold and moved to sales tab.");
                    }
                } else if(n==1){
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"  Sales Transaction Aborted.  ");
                }
            }
        });
    }
    public JPanel getPanel(){
        return catalog_Maint_Panel;
    }


}

