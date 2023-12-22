package org.classes;
import org.entities.Database;
import org.entities.addInstallDate;
import java.util.Scanner;
import java.util.logging.Logger;
public class addInstall {
    public final static java.util.logging.Logger logger = Logger.getLogger(addInstall.class.getName());
    public static addInstallDate recordInstall() {
        Scanner in=new Scanner(System.in);
        addInstallDate addInstallDate=new addInstallDate();
        logger.info("Enter installation Date");
        String date =in.nextLine();
        addInstallDate.setDate(date);
        logger.info("Enter installation Time ");
        String time =in.nextLine();
        addInstallDate.setTime(time);
        addInstallDate.setId(Database.get_install_id());
        addInstallDate.setStatus("Available");
        addNewDate(addInstallDate);
        return addInstallDate;
    }
    public static void addNewDate(addInstallDate addInstallDate){
        Database.storeObject("dates",addInstallDate);
    }
}