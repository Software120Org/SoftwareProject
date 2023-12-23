package org.classes;
import org.entities.Database;
import org.entities.AddInstallDate;
import java.util.Scanner;
import java.util.logging.Logger;
public class AddInstall {
public AddInstall(){}
    public  static final java.util.logging.Logger logger = Logger.getLogger(AddInstall.class.getName());
   
    public static AddInstallDate recordInstall() {
        Scanner in=new Scanner(System.in);
        AddInstallDate addInstallDate=new AddInstallDate();
        logger.info("Enter installation Date");
        String date =in.nextLine();
        addInstallDate.setDate(date);
        logger.info("Enter installation Time ");
        String time =in.nextLine();
        addInstallDate.setTime(time);
        addInstallDate.setId(Database.getInstallId());
        addInstallDate.setStatus("Available");
        addNewDate(addInstallDate);
        return addInstallDate;
    }
    public static void addNewDate(AddInstallDate addInstallDate){
        Database.storeObject("dates",addInstallDate);
    }
}