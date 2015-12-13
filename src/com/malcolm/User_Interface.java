package com.malcolm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/2/2015.
 */

public class User_Interface extends JFrame implements WindowListener{
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;


    public User_Interface(final Record_Catalog_Display_DataModel catalog_display_dataModel,final Record_Catalog_Display_DataModel consigner_display_dataModel,final Record_Catalog_Display_DataModel sold_Records_Datamodel,final Record_Catalog_Display_DataModel sales_Tab_Consigner_DataModel){
        setContentPane(rootPanel);
        //create the tabbedpane, add to rootPanel, then add the tabs.
        tabbedPane=new JTabbedPane();
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                System.out.println("Tab Changed To: "+sourceTabbedPane.getTitleAt(index));
                System.out.println("Tab Index Number: "+index);
                if(index==0){
                    catalog_display_dataModel.search("Default","");
                }
                else if(index==1){
                    consigner_display_dataModel.search("Default","");
                }
                else if(index==2){
                    System.out.println("This is the sales tab!");
                    //TODO put actual code here
                }
            }
        };
        tabbedPane.addChangeListener(changeListener);
        rootPanel.add(tabbedPane);
        tabbedPane.add("Record Catalog",new Catalog_Maintentence_Tab(catalog_display_dataModel).getPanel());
        tabbedPane.add("Consigner Information",new Consigner_Maintenece_Tab(consigner_display_dataModel).getPanel());
        tabbedPane.add("Sold Records and Consigner Totals",new Record_Sales_Tab(sold_Records_Datamodel,sales_Tab_Consigner_DataModel).getPanel());
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //todo Activate the DBSHUTDOWN METHOD IN CONNECTODB to close all resources


    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
try{
    ConnectToDB.shutDownDBResources();
}catch(SQLException se){
    System.out.println("An error occurred when trying to close DB resources.");
}
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
