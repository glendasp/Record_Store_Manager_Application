package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class Consigner_Maintenece_Tab extends JPanel{
    private JPanel consigner_Maint_Panel;
    private JTable consigner_Display_JTable;
    private JButton addConsignerButton;
    private JTextField consignerNametextField;
    private JTextField routingNumberTextField;
    private JLabel routingNumberLabel;
    private JScrollPane containsConsignerJPanel;
    private JLabel consignerLabel;
    private JTextField addressTextField;
    private JLabel addressJLabel;
    private JTextField emailTextField;
    private JButton remove_Consigner;
    private JButton searchConsignersButton;
    private JComboBox searchOptionsComboBox;
    private JTextField searchParameter;

    //this is for the searching comboBox
    private String searchOp0="Default";
    private String searchOp1="Name";
    private String searchOp2="Address";
    private String searchOp3="E-Mail";
    private String searchOp4="Routing Number";

    public Consigner_Maintenece_Tab(final Record_Catalog_Display_DataModel consigner_display_dataModel){
        //This is the search paramters box
        searchOptionsComboBox.addItem(searchOp0);
        searchOptionsComboBox.addItem(searchOp1);
        searchOptionsComboBox.addItem(searchOp2);
        searchOptionsComboBox.addItem(searchOp3);
        searchOptionsComboBox.addItem(searchOp4);

        consigner_Display_JTable.setGridColor(Color.BLACK);
        consigner_Display_JTable.setModel(consigner_display_dataModel);


        addConsignerButton.addActionListener(new ActionListener() {
            String consignerName="";
            String address="";
            String email="";
            String bankingInfo="";
            @Override
            public void actionPerformed(ActionEvent e) {

                consignerName=consignerNametextField.getText();
                address=addressTextField.getText();
                email=emailTextField.getText();
                bankingInfo=routingNumberTextField.getText();
                if(consignerName==""||address==""||email==""||bankingInfo==""||bankingInfo.length()!=9||!email.contains("@")){
                    JOptionPane.showMessageDialog(consigner_Maint_Panel,"Please check the data you entered to ensure you left no blank fields," +
                                                                        "\n   a 9 digit banking number, as well as a correct email address.");

                }else {
                    consigner_display_dataModel.insert_Consigner_To_List(consignerName, address, email, bankingInfo);
                    consignerNametextField.setText("");
                    addressTextField.setText("");
                    emailTextField.setText("");
                    routingNumberTextField.setText("");
                }
                consigner_display_dataModel.fireTableDataChanged();

                //consigner_display_dataModel.search("Default","");

            }
        });
        searchConsignersButton.addActionListener(new ActionListener() {
            String searchParam="";
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchOptionsComboBox.getSelectedItem().equals(searchOp0)){
                    consigner_display_dataModel.search("Default","");
                }else if(searchOptionsComboBox.getSelectedItem().equals(searchOp1)){

                }else if(searchOptionsComboBox.getSelectedItem().equals(searchOp2)){

                }else if(searchOptionsComboBox.getSelectedItem().equals(searchOp3)){

                }else if(searchOptionsComboBox.getSelectedItem().equals(searchOp4)){

                }
            }
        });
    }
    public JPanel getPanel(){
        return consigner_Maint_Panel;
    }
}
