package com.malcolm;

import javax.swing.*;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class Consigner_Maintenece_Tab extends JPanel{
    private JPanel consigner_Maint_Panel;
    private JTable table1;
    private JButton addConsignerButton;
    private JTextField consignerNametextField;
    private JTextField routingNumberTextField;
    private JLabel routingNumberLabel;
    private JScrollPane containsConsignerJPanel;
    private JLabel consignerLabel;

    public Consigner_Maintenece_Tab(){

    }
    public JPanel getPanel(){
        return consigner_Maint_Panel;
    }
}
