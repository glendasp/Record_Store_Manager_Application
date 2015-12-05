package com.malcolm;

import javax.swing.*;

/**
 * Created by Malcolm on 12/2/2015.
 */

public class User_Interface extends JFrame{
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;


    public User_Interface(final Record_Catalog_Display_DataModel catalog_display_dataModel){
        setContentPane(rootPanel);
        //create the tabbedpane, add to rootPanel, then add the tabs.
        tabbedPane=new JTabbedPane();
        rootPanel.add(tabbedPane);
        tabbedPane.add("Record Catalog",new Catalog_Maintentence_Tab(catalog_display_dataModel).getPanel());
        tabbedPane.add("Consigner Information",new Consigner_Maintenece_Tab().getPanel());
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //todo Activate the DBSHUTDOWN METHOD IN CONNECTODB to close all resources


    }
}
