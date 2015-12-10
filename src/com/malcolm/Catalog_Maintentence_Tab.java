package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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
    //TODo FIXME: this works, but not with more than one resultset.  Make it get it's data from a linked list
//        try {
//            ResultSet consignerRS = ConnectToDB.statement.executeQuery("SELECT Consigner_Name FROM consigners;");
//            while(consignerRS.next()==true){
//                final String x = consignerRS.getString(1);
//                ConsignerComboBox.addItem(x);
//            }
//        }catch(SQLException se){
//            System.out.println("Error while grabbing consigner names.");
//        }

        ConsignerComboBox.addItem(comboOp1);
        ConsignerComboBox.addItem(comboOp2);
        ConsignerComboBox.addItem(comboOp3);
        ConsignerComboBox.addItem(comboOp4);
        ConsignerComboBox.addItem(comboOp5);
        ConsignerComboBox.addItem(comboOp6);
        ConsignerComboBox.addItem(comboOp7);
        ConsignerComboBox.addItem(comboOp8);
        ConsignerComboBox.addItem(comboOp9);
        ConsignerComboBox.addItem(comboOp10);
        ConsignerComboBox.addItem(comboOp11);
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
                if(ConsignerComboBox.getSelectedItem().equals(comboOp0)){
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"Please choose a consigner before adding a record!");
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp1)){
                    c_ID=1;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp2)){
                    c_ID=2;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp3)){
                    c_ID=3;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp4)){
                    c_ID=4;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp5)){
                    c_ID=5;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp6)){
                    c_ID=6;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp7)){
                    c_ID=7;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp8)){
                    c_ID=8;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp9)){
                    c_ID=9;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp10)){
                    c_ID=10;
                }else if(ConsignerComboBox.getSelectedItem().equals(comboOp11)){
                    c_ID=11;
                }
                albumName=albumTitleTextField.getText();
                price= Double.parseDouble(priceTextField.getText());
                year=yearTextField.getText();
                month=monthTextField.getText();
                day=dayTextField.getText();
                completeDateInfo=year+"-"+month+"-"+day;
                //TODO add the C_ID If statemnts here.  Better yet, make a method.
                artistName = artistNameTextField.getText();
                catalogDisplayDataModel.insert_Record_To_Catalog(artistName,albumName,price,completeDateInfo,soldOrNot,c_ID);
            }
        });
        recordCatalogDisplayJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("I Hear the MOUSE BEING CLICKEDDDDDD");
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = recordCatalogDisplayJTable.getSelectedRow();
                if(currentRow == -1){
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"Please choose a record to delete!");
                }
                boolean deleted = catalogDisplayDataModel.deleteRow(currentRow);
                if(deleted){
                    ConnectToDB.loadAllData();
                }else{
                    JOptionPane.showMessageDialog(catalog_Maint_Panel,"The selected row was not deleted for some reason.");
                }
            }
        });
        searchRecordsByButton.addActionListener(new ActionListener() {
            double price=0;
            String searchParam="";
            String defaultQuery = "SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Sold_Or_Not = FALSE ; ";
            String artistQuery="SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Artist_Name LIKE '"+searchParam+"%';";
            String albumQuery="SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Album_Title LIKE '"+searchParam+"%'";
            String priceQuery="SELECT * FROM "+CreateAllTables.RECORD_CATALOG_TABLE_NAME+" WHERE Price<="+price+";";


            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(searchParamComboBox.getSelectedItem().equals(searchOp0)){
                        ResultSet searchRS = ConnectToDB.statement.executeQuery(defaultQuery);
                        catalogDisplayDataModel.updateResultsSet(searchRS);
                        System.out.println(searchRS);
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp1)){
                        searchParam = searchParameterTextField.getText();
                        ResultSet searchRS = ConnectToDB.statement.executeQuery(artistQuery);
                        catalogDisplayDataModel.updateResultsSet(searchRS);
                        System.out.println(searchRS);
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp2)){
                        searchParam = searchParameterTextField.getText();
                        ResultSet searchRS = ConnectToDB.statement.executeQuery(albumQuery);
                        catalogDisplayDataModel.updateResultsSet(searchRS);
                        System.out.println(searchRS);
                    }else if(searchParamComboBox.getSelectedItem().equals(searchOp3)) {
                        price = Double.parseDouble(searchParameterTextField.getText());
                        ResultSet searchRS = ConnectToDB.statement.executeQuery(priceQuery);
                        catalogDisplayDataModel.updateResultsSet(searchRS);
                        System.out.println(searchRS);
                    }catalogDisplayDataModel.fireTableDataChanged();
               }catch(SQLException se){

               }
                   //catalogDisplayDataModel.updateResultsSet();
            }
        });
    }
    public JPanel getPanel(){
        return catalog_Maint_Panel;
    }


}

