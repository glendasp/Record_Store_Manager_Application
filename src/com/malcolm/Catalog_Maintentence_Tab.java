package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class Catalog_Maintentence_Tab extends JPanel implements WindowListener{
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

    Catalog_Maintentence_Tab(final Record_Catalog_Display_DataModel catalogDisplayDataModel){

        recordCatalogDisplayJTable.setGridColor(Color.BLACK);
        recordCatalogDisplayJTable.setModel(catalogDisplayDataModel);




    }
    public JPanel getPanel(){
        return catalog_Maint_Panel;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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

