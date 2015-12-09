package com.malcolm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

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

    Catalog_Maintentence_Tab(final Record_Catalog_Display_DataModel catalogDisplayDataModel){



        recordCatalogDisplayJTable.setGridColor(Color.BLACK);
        recordCatalogDisplayJTable.setModel(catalogDisplayDataModel);


//        addRecordButton.addActionListener(new ActionListener() {
//
//            String artistName="";
//            String albumName="";
//            double price = 0;
//            String year = "";
//            String month = "";
//            String day = "";
//            String completeDateInfo=year+"-"+month+"-"+day;
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            artistName = artistNameTextField.getText();
//             //   catalogDisplayDataModel.insert_Record_To_Catalog(artistName,albumName)
//            }
//        });
    }
    public JPanel getPanel(){
        return catalog_Maint_Panel;
    }


}

