package com.malcolm;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Malcolm on 12/4/2015.
 */
public class CreateAllTables {
    private static final String DB_NAME = "record_store";

    public final static String CONSIGNER_TABLE_NAME="consigners";
    public final static String CONSIGNER_TABLE_PK="C_ID";
    public final static String CONSIGNER_NAME_COL="Consigner_Name";
    public final static String CONSIGNER_ADDRESS="Address";
    public final static String CONSIGNER_EMAIL="E_Mail";
    public final static String CONSIGNER_BANK_NUM="Bank_Account_Number";//This exists only as a place holder for a real money interface.
    // For now this will work.
    //Info for Records_Catalog Table
    public final static String RECORD_CATALOG_TABLE_NAME="record_catalog";
    public final static String RECORD_CATALOG_PK_COL="Record_ID";
    public final static String RECORD_CATALOG_ARTIST="Artist_Name";
    public final static String RECORD_CATALOG_ALBUM="Album_Title";
    public final static String RECORD_CATALOG_PRICE="Price";
    public final static String RECORD_CATALOG_SHELVED="Shelved_Date";
    public final static String RECORD_CATALOG_SOLD="Sold_Or_Not";
    public final static String RECORD_CATALOG_CONSIGNER="consigners_C_ID";
    //TODO Make and then place the sales table infor here.
    public final static String ARCHIVED_SALES_TABLE_NAME="archived_Sales";
    public final static String ARCHIVED_SALES_PK_SALES_ID="Sale_ID";
    public final static String ARCHIVED_SALES_C_ID="C_ID";
    public final static String ARCHIVED_SALES_RECORD_ID="Record_ID";
    public final static String ARCHIVED_SALES_S_PRICE="SalePrice";
    public final static String ARCHIVED_SALES_S_PR_60="Our_60";
    public final static String ARCHIVED_SALES_S_PR_40="Their_40";
    public final static String ARCHIVED_SALES_DATE="Date_Sold";
    public final static String ARCHIVED_SALES_ALBUM="Album_Title";
    public final static String ARCHIVED_SALES_ARTIST="Artist_Name";
    public final static String ARCHIVED_SALES_BANK_NUM="Bank_Account_Number";

    public CreateAllTables(){
        ActivateTableCreation();

    }
public void ActivateTableCreation(){
    System.out.println("Checking if tables are present...");
    try{
        if(!consigners_Table_Exists()){
            String create_Consigner_Table_SQL="CREATE TABLE Consigners (C_ID int NOT NULL Primary Key AUTO_INCREMENT,Consigner_Name varchar(40),Address VARCHAR(50),E_Mail VARCHAR(50) , Bank_Account_Number varchar(9));";
            ConnectToDB.statement.executeUpdate(create_Consigner_Table_SQL);
            insert_Into_Consigners_Table();
            System.out.println("Created the Consigner's Information Table");
        }
        if(!catalog_Table_Exists()){
            String create_Record_Catalog_SQL="CREATE TABLE record_catalog (`Record_ID` INT(11) NOT NULL AUTO_INCREMENT ,`Artist_Name` VARCHAR(40) NULL DEFAULT NULL,`Album_Title` VARCHAR(50) NULL DEFAULT NULL,`Price` DECIMAL(6,2) NULL DEFAULT NULL,`Shelved_Date` DATE NULL DEFAULT NULL,`Sold_Or_Not` BOOLEAN NULL DEFAULT False,Archived_Or_Not BOOLEAN NULL DEFAULT FALSE ,`consigners_C_ID` INT(11) not NULL,PRIMARY KEY (`Record_ID`, `consigners_C_ID`),INDEX `fk_record_catalog_consigners_idx` (`consigners_C_ID` ASC),CONSTRAINT `fk_record_catalog_consigners`FOREIGN KEY (`consigners_C_ID`)REFERENCES `record_store`.`consigners` (`C_ID`)ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8";
            ConnectToDB.statement.executeUpdate(create_Record_Catalog_SQL);
            insert_Test_Data_For_Record_Catalog();
            System.out.println("Created the Records Information Table");
        }
        if(!sales_Table_Exists()){
            String create_ArchivedSales_Table_SQL="CREATE TABLE IF NOT EXISTS `record_store`.`archived_sales` (`Sale_ID` INT(11) NOT NULL AUTO_INCREMENT, `C_ID` INT(11) NULL DEFAULT NULL, `Record_ID` INT(11) NULL DEFAULT NULL, `SalePrice` FLOAT(6,2) NULL DEFAULT NULL, `Our_60` FLOAT(6,2) NULL DEFAULT NULL, `Their_40` FLOAT(6,2) NULL DEFAULT NULL, `Date_Sold` DATE NULL DEFAULT NULL, `Album_Title` VARCHAR(50) NULL DEFAULT NULL, `Artist_Name` VARCHAR(40) NULL DEFAULT NULL, `Bank_Account_Number` INT(9) NULL DEFAULT NULL, PRIMARY KEY (`Sale_ID`), INDEX `fk_archived_sales_record_catalog1_idx` (`Record_ID` ASC), INDEX `fk_archived_sales_consigners1_idx` (`C_ID` ASC), CONSTRAINT `fk_archived_sales_consigners1` FOREIGN KEY (`C_ID`) REFERENCES `record_store`.`consigners` (`C_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_archived_sales_record_catalog1` FOREIGN KEY (`Record_ID`) REFERENCES `record_store`.`record_catalog` (`Record_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
            ConnectToDB.statement.executeUpdate(create_ArchivedSales_Table_SQL);
            insert_Test_Data_Into_Archive_Sales();
            System.out.println("Created the Sales Archive Table.");
        }

    }catch(SQLException se){
        System.out.println("Error in creating the tables.  ActivateTableCreation");
    }
}
    private static boolean insert_Into_Consigners_Table(){

        try{
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Cheapo Disc','123 Primary Street','cheapo_Store14564@cheapo.com',902341574);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Cheapo Disc','456 Secondary Street','cheapo_Store14643@cheapo.com',593720754);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Treehouse Records','2557 Lyndale Ave S','treehouseRecords@gmail.com',376198735);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Raving Als Records','1800 Lovecraftian Street','insaneDeals@alsRecords.com',738499276);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Tattersails Discs','3429 Rat Quarter','alwaysAnEvenTrade@sorceress.com',837265092);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Forkrul Assail Warren of Tunes','2938 Kolanse Street','estobanse_province@kolanse.com',283749203);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Cheapo Disc','789 Tertiary Blvd.','cheapo_Store19854@cheapo.com',831924622);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Glibibdoolblips Emporium','8473 Underdark Blvd','underground_music@menzoberranzon.com',333948202);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Kurald Galein','921 Korrabas Ave','warren_of_Darkness@motherDark.com',999382736);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Kurald Immourlan','836 Annomandaris Porakki Street','warren_of_Shadow@motherDark.com',374652223);");
            ConnectToDB.statement.executeUpdate("insert into consigners (Consigner_Name,Address,E_Mail,Bank_Account_Number) Values('Thiure Galein','32875 Tiste-Andii Drive','warren_of_Light@motherDark.com',927361109);");
            return true;
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("\nYou had an error inserting data in the Cosigners tables.");
            return false;
        }

    }
    private static boolean insert_Test_Data_Into_Archive_Sales(){
        try{
            //Just a test data, does not actually belong to any consigner or have a record ID
            ConnectToDB.statement.executeUpdate("insert into archived_sales (SalePrice,Our_60,Their_40,Date_Sold,Album_Title,Artist_Name,Bank_Account_Number) Values(25.66,15.39,10.26,'2014-11-05','Money Shot','Puscifer','927509821');");

            return true;
        }catch (SQLException se){

            return false;
        }
    }
    private static boolean insert_Test_Data_For_Record_Catalog(){
        try {
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-03-15',false,5); ");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-08-19',false,1);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dosh','Tommy',20.75,'2015-11-22',false,3);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','The Mysterious Production of Eggs',14.59,'2015-12-01',false,4);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','The Mysterious Production of Eggs',14.59,'2015-11-02',false,2);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','Noble beast',22.15,'2015-08-19',false,6);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Andrew Bird','Armchair Apocropha',20.99,'2015-11-15',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Explosions in the Sky','All of a Sudden I Miss Everyone',68.99,'2015-12-01',false,8);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Godspeed You! Black Emperor','F#A#(INFINITY)',90.99,'2015-11-27',false,9);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Hella','There is no 666 in Outer Space',23.25,'2015-11-22',false,10);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Death Grips','The Money Store',15.10,'2015-03-15',false,11);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Emperor','In the Nightside Eclipse',10.50,'2015-11-10',false,1);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Venom','Black Metal',13.00,'2015-09-09',false,2);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dark Throne','A Blaze in the Northern Sky',32.22,'2015-12-01',false,3);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tycho','Awake',14.00,'2015-07-08',false,4);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Deafheaven','New Bermuda Sunbather',15,'2015-12-09',false,5);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Dark Throne','Transylvania Hunger',18.90,'2015-09-15',false,6);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Electric Wizard','Dope Throne',12.34,'2015-12-01',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Sleep','Sleeps Holy Mountain',14.50,'2015-12-01',false,8);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Sleep','Dopesmoker',13.04,'2015-11-21',true,9);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Strokes','Is this It',15.55,'2015-06-01',false,10);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('CHVRCHES','The Bones of What You Believe',20.55,'2015-10-11',false,11);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('CHVRCHES','Every Open Eye',20.55,'2015-10-11',false,11);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Paul Simon','Graceland',34.99,'2015-12-01',false,1);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Simon and Garfunkel','Wednesday Morning, 3am',25.00,'2015-11-27',false,1);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Bongripper','Hail Satan Worship Doom',26.00,'2015-11-09',true,2);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('THOU','Peasant',14.44,'2015-12-02',false,3);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Father John Misty','I love you, honeybear',20.99,'2015-11-15',false,4);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Trampled by Turtles','Palamino',30.00,'2015-11-19',false,5);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Trampled by Turtles','Duluth',32.22,'2015-11-11',false,6);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Salival',54.99,'2015-11-10',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','10,000 Days',86.99,'2015-11-05',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Lateralus',44.99,'2015-11-05',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','AEnima',37.99,'2015-11-05',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Tool','Opiate',43.99,'2015-11-05',false,7);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('DIO','Holy Diver',30.00,'2015-11-17',false,8);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Beatles','Sgt. Peppers Lonely Hearts Club Band',30.00,'2015-10-29',false,9);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Pretty Hate Machine',22.22,'2015-09-09',false,10);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Downward Spiral',44.22,'2015-09-09',false,10);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Fragile',33.33,'2015-09-09',false,10);");
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','With Teeth',25.00,'2015-09-09',false,10);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Year Zero',17.99,'2015-09-09',false,10);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Ghosts I-IV (Collectors Edition)',87.00,'2015-09-09',false,10);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','The Slip',28.99,'2015-09-09',false,10);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Nine Inch Nails','Hesitation marks',20.00,'2015-09-09',false,10)" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Blitzen Trapper','Furr',21.22,'2015-11-30',false,11);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Devo','Something For Everybody',25.00,'2015-11-13',false,1);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('The Skastranauts','Keepers of Real (Limited Edition)',99.00,'2015-11-18',false,2);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('First Aid Kit','Solid Gold',24.99,'2015-11-16',false,3);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',33.33,'2015-11-25',false,4);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Wizards of the Coast','Official D&D Soundtrack',10.00,'2015-11-12',false,5);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',31.33,'2015-11-25',false,4);" );
            ConnectToDB.statement.executeUpdate("insert into record_catalog (Artist_Name,Album_Title,Price,Shelved_Date,Sold_Or_Not,consigners_C_ID) Values('Marty Robbins','Gunfighter Ballads',35.33,'2015-11-25',false,4);");

           // ConnectToDB.statement.executeUpdate(insert_Test_Data_Into_Records_Table);

        } catch (SQLException se) {
            System.out.println(se);
            System.out.println("Error inserting test data into th record catalog table.");
        }
    return true;
    }
    private static boolean DatabaseExists() throws SQLException{
        //nifty method for checking to see if it is there using the metadata instead of just a query of SHOW TABLES
        DatabaseMetaData metaData = ConnectToDB.conn.getMetaData();
        ResultSet tableRS = metaData.getTables(null,null,DB_NAME,null);
        if(tableRS.next()){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean catalog_Table_Exists() throws SQLException{
        String checkTableExists = "SHOW TABLES LIKE '"+RECORD_CATALOG_TABLE_NAME+"'";
        System.out.println(checkTableExists);
        ResultSet rs = ConnectToDB.statement.executeQuery(checkTableExists);
        if(rs.next()){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean consigners_Table_Exists() throws SQLException{
        String checkTableExists = "SHOW TABLES LIKE '"+CONSIGNER_TABLE_NAME+"'";
        System.out.println(checkTableExists);
        ResultSet rs = ConnectToDB.statement.executeQuery(checkTableExists);
        if(rs.next()){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean sales_Table_Exists() throws SQLException{
        //Fixme MAKE THIS ONCE SALES TABLE IS MADE
        String checkTableExists = "SHOW TABLES LIKE '"+ARCHIVED_SALES_TABLE_NAME+"'";
        ResultSet rs = ConnectToDB.statement.executeQuery(checkTableExists);
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }
}
