package org.classes;
import org.entities.Database;
import org.entities.addInstallDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class addInstall {
    public static  final java.util.logging.Logger logger = Logger.getLogger(addInstall.class.getName());
    public static addInstallDate recordInstall() {
        Scanner in=new Scanner(System.in);
        addInstallDate addInstallDate=new addInstallDate();
        logger.info("Enter installation Date");
        String date =in.nextLine();
        while (true) {
            if (isValidDate(date)) {
                addInstallDate.setDate(date);
                break;
            } else {
                logger.info("Invalid Date... YYYY-MM-DD !");
                date = in.nextLine();
            }
        }
        logger.info("Enter installation Time ");
        String time =in.nextLine();
        while ((true)) {
            if(isValidTime(time)){
                addInstallDate.setTime(time);
                break;
            }else {
                logger.info("Invalid time... 00:00 AM|PM !");
                time = in.nextLine();

        }}while (true) {
            if (!(isUnUniqueDate(date, time))) {
                addInstallDate.setDate(date);
                addInstallDate.setTime(time);
                break;
            } else {
                logger.info("This date and time is already taken.");
                date = in.nextLine();
                time = in.nextLine();
            }
        }
        addInstallDate.setId(Database.get_install_id());
        addInstallDate.setStatus("Available");
        addNewDate(addInstallDate);
        return addInstallDate;
    }
    public static void addNewDate(addInstallDate addInstallDate){
        Database.storeObject("dates",addInstallDate);
    }
    public static boolean isValidDate(String date){

        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern=Pattern.compile(dateRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(date);
        return matcher.find();
    }
    public static boolean isValidTime(String time) {
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s(?:AM|PM)$";
        Pattern pattern = Pattern.compile(timeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
    public static boolean isUnUniqueDate(String date, String time) {
        String date_time = date + "      " + time;
        List<String> Install = new ArrayList<>();

        for (String install : Database.getObjects("dates")) {
            String[] arr = install.split(" {6}");
            String dateAndTime = arr[1] + "      " + arr[2];
            Install.add(dateAndTime);
        }
        return Install.contains(date_time);
    }


}