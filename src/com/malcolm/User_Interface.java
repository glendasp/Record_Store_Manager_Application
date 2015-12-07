package com.malcolm;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/2/2015.
 */

public class User_Interface extends JFrame implements WindowListener{
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
