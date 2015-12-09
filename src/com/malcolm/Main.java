package com.malcolm;

import java.sql.*;

public class Main {
//    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
//    private static final String DB_NAME = "record_store";
//    private static final String USER = "root";
//    private static final String PASS = "1362WminnehahaAve";
//
//    static Statement statement = null;
//    static Connection conn = null;
   static ResultSet rs = null;
//    //Info for Consigners Table,
//    public final static String CONSIGNER_TABLE_NAME="consigners";
//    public final static String CONSIGNER_TABLE_PK="C_ID";
//    public final static String CONSIGNER_NAME_COL="Consigner_Name";
//    public final static String CONSIGNER_OWED_COL="Money_Owed";
//    public final static String CONSIGNER_BANK_NUM="Bank_Account_Number";//This exists only as a place holder for a real money interface.
//    // For now this will work.
//    //Info for Records_Catalog Table
//    public final static String RECORD_CATALOG_TABLE_NAME="record_catalog";
//    public final static String RECORD_CATALOG_PK_COL="Record_ID";
//    public final static String RECORD_CATALOG_ARTIST="Artist_Name";
//    public final static String RECORD_CATALOG_ALBUM="Album_Title";
//    public final static String RECORD_CATALOG_PRICE="Price";
//    public final static String RECORD_CATALOG_SHELVED="Shelved_Date";
//    public final static String RECORD_CATALOG_SOLD="Sold_Or_Not";
//    public final static String RECORD_CATALOG_CONSIGNER="consigners_C_ID";
//    //TODO Make and then place the sales table infor here.
//
//    //Here we put the integers for the Spinner Wheel that selects the consigner
//    public final static int MIN_CONSIGNER_NUMBER=1;
//    public final static int MAX_CONSIGNER_NUMBER=11;
    private static Record_Catalog_Display_DataModel catalog_display_dataModel=null;


    public static void main(String[] args) {
        ConnectToDB connectToDB = new ConnectToDB();

       // loadAllData();



    }

    //TODO FIXME Right now this is checking for the datamodel before the tables and everytihng exists, It must check ofter the tables and such exist.

//    private static boolean DatabaseExists() throws SQLException{
//        //nifty method for checking to see if it is there using the metadata instead of just a query of SHOW TABLES
//        DatabaseMetaData metaData = conn.getMetaData();
//        ResultSet tableRS = metaData.getTables(null,null,DB_NAME,null);
//        if(tableRS.next()){
//            return true;
//        }
//        return false;
//    }
//    public static boolean setup(){
//        try{
//            String create_DB_SQL="CREATE DATABASE record_store;";
//            String create_Consigner_Table_SQL="CREATE TABLE Consigners (C_ID int NOT NULL Primary Key AUTO_INCREMENT,Consigner_Name varchar(40),Money_Owed Float, Bank_Account_Number varchar(9));";
//            String create_Record_Catalog_SQL="CREATE TABLE record_catalog (`Record_ID` INT(11) NOT NULL AUTO_INCREMENT,`Artist_Name` VARCHAR(40) NULL DEFAULT NULL,`Album_Title` VARCHAR(50) NULL DEFAULT NULL,`Price` DECIMAL(6,2) NULL DEFAULT NULL,`Shelved_Date` DATE NULL DEFAULT NULL,`Sold_Or_Not` BOOLEAN NULL DEFAULT False,`consigners_C_ID` INT(11) not NULL,PRIMARY KEY (`Record_ID`, `consigners_C_ID`),INDEX `fk_record_catalog_consigners_idx` (`consigners_C_ID` ASC),CONSTRAINT `fk_record_catalog_consigners`FOREIGN KEY (`consigners_C_ID`)REFERENCES `record_store`.`consigners` (`C_ID`)ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8";
//            String insert_Test_Data_To_Consigner_Table="insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Cheapo Disc',null,902341574);" +
//                    "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Cheapo Disc',null,593720754);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Treehouse Records',null,376198735);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Raving Als Records',null,738499276);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Tattersails Discs',null,837265092);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Forkrul Assail Warren of Tunes',null,283749203);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Glibibdoolblips Emporium',null,333948202);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Cheapo Disc',null,831924622);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Kurald Galein',null,999382736);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Kurald Immourlan',null,374652223);" + "insert into consigners (Consigner_Name,Money_Owed,Bank_Account_Number) Values('Thiure Galein',null,927361109);";
//
//            String insert_Test_Data_Into_Records_Table="insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-03-15',false,5);" +
//                    "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-08-19',false,1);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-11-22',false,3);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','The Mysterious Production of Eggs',14.59,'2015-12-01',false,4);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','The Mysterious Production of Eggs',14.59,'2015-11-02',false,2);" +
//                    "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','Noble beast',22.15,'2015-08-19',false,6);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','Armchair Apocropha',20.99,'2015-11-15',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Explosions in the Sky','All of a Sudden I Miss Everyone',68.99,'2015-12-01',false,8);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Godspeed You! Black Emperor','F#A#(INFINITY)',90.99,'2015-11-27',false,9);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Hella','There is no 666 in Outer Space',23.25,'2015-11-22',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Death Grips','The Money Store',15.10,'2015-03-15',false,11);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Emperor','In the Nightside Eclipse',10.50,'2015-11-10',false,1);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Venom','Black Metal',13.00,'2015-09-09',false,2);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dark Throne','A Blaze in the Northern Sky',32.22,'2015-12-01',false,3);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tycho','Awake',14.00,'2015-07-08',false,4);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Deafheaven','New Bermuda Sunbather',15,'2015-12-09',false,5);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dark Throne','Transylvania Hunger',18.90,'2015-09-15',false,6);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Electric Wizard','Dope Throne',12.34,'2015-12-01',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Sleep','Sleeps Holy Mountain',14.50,'2015-12-01',false,8);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Sleep','Dopesmoker',13.04,'2015-11-21',false,9);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Strokes','Is this It',15.55,'2015-06-01',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('CHVRCHES','The Bones of What You Believe',20.55,'2015-10-11',false,11);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('CHVRCHES','Every Open Eye',20.55,'2015-10-11',false,11);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Paul Simon','Graceland',34.99,'2015-12-01',false,1);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Simon and Garfunkel','Wednesday Morning, 3am',25.00,'2015-11-27',false,1);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Bongripper','Hail Satan Worship Doom',26.00,'2015-11-09',false,2);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('THOU','Peasant',14.44,'2015-12-02',false,3);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Father John Misty','I love you, honeybear',20.99,'2015-11-15',false,4);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Trampled by Turtles','Palamino',30.00,'2015-11-19',false,5);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Trampled by Turtles','Duluth',32.22,'2015-11-11',false,6);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Salival',54.99,'2015-11-10',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','10,000 Days',86.99,'2015-11-05',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Lateralus',44.99,'2015-11-05',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','AEnima',37.99,'2015-11-05',false,7);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Opiate',43.99,'2015-11-05',false,7);" +
//                    "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('DIO','Holy Diver',30.00,'2015-11-17',false,8);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Beatles','Sgt. Peppers Lonely Hearts Club Band',30.00,'2015-10-29',false,9);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Pretty Hate Machine',22.22,'2015-09-09',false,10);" +
//                    "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Downward Spiral',44.22,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Fragile',33.33,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','With Teeth',25.00,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Year Zero',17.99,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Ghosts I-IV (Collectors Edition)',87.00,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Slip',28.99,'2015-09-09',false,10);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Hesitation marks',20.00,'2015-09-09',false,10)" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Blitzen Trapper','Furr',21.22,'2015-11-30',false,11);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Devo','Something For Everybody',25.00,'2015-11-13',false,1);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Skastranauts','Keepers of Real (Limited Edition)',99.00,'2015-11-18',false,2);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('First Aid Kit','Solid Gold',24.99,'2015-11-16',false,3);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',33.33,'2015-11-25',false,4);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Wizards of the Coast','Official D&D Soundtrack',10.00,'2015-11-12',false,5);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',31.33,'2015-11-25',false,4);" + "insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',35.33,'2015-11-25',false,4);";
//            //Make the DB
//            statement.executeUpdate(create_DB_SQL);
//            //make the Consigner Table
//            statement.executeUpdate(create_Consigner_Table_SQL);
//            //Insert into the consigner table
//            statement.executeUpdate(insert_Test_Data_To_Consigner_Table);
//            //Insert data into the records_catalog
//            statement.executeUpdate(create_Record_Catalog_SQL);
//            //Now insert the test data into it
//            statement.executeUpdate(insert_Test_Data_Into_Records_Table);
//        }catch(SQLException se){
//            System.out.println(se);
//            System.out.println("There was an issue with either the creation of the DB, the Consigner_Table, or the Records_Table");
//        }return true;
//    }
}
