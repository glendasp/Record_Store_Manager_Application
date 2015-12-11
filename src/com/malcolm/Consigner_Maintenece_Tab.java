package com.malcolm;

import javax.swing.*;
import java.awt.*;

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
    private JTextField adressTextField;
    private JLabel adressJLabel;
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

    public Consigner_Maintenece_Tab(final Consigner_Display_DataModel consigner_display_dataModel){
        //This is the search paramters box
        searchOptionsComboBox.addItem(searchOp0);
        searchOptionsComboBox.addItem(searchOp1);
        searchOptionsComboBox.addItem(searchOp2);
        searchOptionsComboBox.addItem(searchOp3);
        searchOptionsComboBox.addItem(searchOp4);

        consigner_Display_JTable.setGridColor(Color.BLACK);
        consigner_Display_JTable.setModel(consigner_display_dataModel);
    }
    public JPanel getPanel(){
        return consigner_Maint_Panel;
    }
}
