package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class Record_Sales_Tab extends JPanel{
   private JPanel sales_Maint_Tab;
    private JTable sold_Records_JTable;
    private JTable consignersAndOwedMoneyJTable;
    private JButton settleBatchOfAllButton;
    private JButton reverseSaleOfSelectedButton;
    private JLabel DISPLAY_STORE_MONEY_LABEL;

    Record_Sales_Tab(final Record_Catalog_Display_DataModel sold_Records_Model,final Record_Catalog_Display_DataModel consigner_display_dataModel) {
        //Set our tables
        consignersAndOwedMoneyJTable.setGridColor(Color.BLACK);
        consignersAndOwedMoneyJTable.setModel(consigner_display_dataModel);
        sold_Records_JTable.setGridColor(Color.BLACK);
        sold_Records_JTable.setModel(sold_Records_Model);


        settleBatchOfAllButton.addActionListener(new ActionListener() {
            //TODO first get the info from the original record that you need for the insert statement
            //then insert via hard coded statements since there is nothing for a SQL injection to ride
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
            reverseSaleOfSelectedButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e){
                int n = JOptionPane.showConfirmDialog(null, "Are you sure about this return?", "WARNING!", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    int rowIndex;int colIndex;
                    rowIndex=sold_Records_JTable.getSelectedRow();
                    System.out.println("The row I grabbed :"+rowIndex);
                    colIndex=sold_Records_JTable.getColumnCount();
                    System.out.println("The column I just grabbed :"+colIndex);
                    //this grabs the ID number of the record so that I can execute the Data Models 'Sell Record' method.
                    String i = (String)sold_Records_Model.getValueAt(rowIndex,0);
                    if(sold_Records_Model.record_Returned(i)){
                        sold_Records_Model.fireTableDataChanged();

                        JOptionPane.showMessageDialog(sales_Maint_Tab,"Record has been sold and moved to sales tab.");
                    }
                } else if(n==1){
                    JOptionPane.showMessageDialog(sales_Maint_Tab,"  Sales Transaction Aborted.  ");
                }
                    consigner_display_dataModel.search("Default","",3);
            }
        });
    }
    public JPanel getPanel(){
        System.out.println("Grabbing th 3rd Panel");
        return sales_Maint_Tab;
    }
}
