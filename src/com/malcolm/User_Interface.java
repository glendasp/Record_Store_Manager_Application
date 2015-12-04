package com.malcolm;

import javax.swing.*;

/**
 * Created by Malcolm on 12/2/2015.
 */
public class User_Interface extends JFrame{
    private JPanel rootPanel;
    //private JTable recordCatalogDisplayJTable;
    private JTabbedPane tabbedPane;

    public User_Interface(){
        setContentPane(rootPanel);
        //create the tabbedpane, add to rootPanel, then add the tabs.
        tabbedPane=new JTabbedPane();
        rootPanel.add(tabbedPane);
        tabbedPane.add("Record Catalog",new Catalog_Maintentence_Tab().getPanel());
        tabbedPane.add("Consigner Information",new Consigner_Maintenece_Tab().getPanel());
        setVisible(true);
        pack();


    }
}
