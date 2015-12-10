package com.malcolm;

import java.sql.*;

public class Main {

   static ResultSet rs = null;

    private static Record_Catalog_Display_DataModel catalog_display_dataModel=null;


    public static void main(String[] args) {
        ConnectToDB connectToDB = new ConnectToDB();

       // loadAllData();



    }

    //TODO FIXME Right now this is checking for the datamodel before the tables and everytihng exists, It must check ofter the tables and such exist.

//    }
}
