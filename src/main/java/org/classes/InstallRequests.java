package org.classes;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import org.entities.Database;
import org.entities.Installation;
import org.entities.AddInstallDate;
public class InstallRequests {
    public static final Logger logger=Logger.getLogger(InstallRequests.class.getName());
    public Installation recordInstallation(){
        Scanner in=new Scanner(System.in);
        Installation install = new Installation();
        logger.info("Enter product name");
        install.setProduct(in.nextLine());
        logger.info("Enter car make ");
        install.setMake(in.nextLine());
        logger.info("Enter car model ");
        install.setModel(in.nextLine());
        logger.info("Enter the email you want to receive notifications on ");
        String email = in.nextLine();
        install.setEmail(email);
        install.setId(Database.getReqId());
        addNewInstall(install);
        return install;
    }
    public void addNewInstall(Installation installation) {
        Database.storeObject("install",installation);
        logger.info("The request has been submitted successfully, Thank you!");
    }
    public String[] installBYid(AddInstallDate addInstallDate) {
        List<AddInstallDate> addInstallDates = Database.gitDate();
        String[] dateAndTime = new String[2];

        for (AddInstallDate install : addInstallDates) {
            if (install.getId() == addInstallDate.getId()) {
                dateAndTime[0] = install.getDate();
                dateAndTime[1] = install.getTime();
                break;
            }
        }
        return dateAndTime;
    }
}