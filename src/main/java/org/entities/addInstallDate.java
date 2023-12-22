package org.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addInstallDate {
    private int id;
    private String date;
    private String time;
    private String status;
    public addInstallDate(){}
    public addInstallDate(int id, String date, String time, String status) {
        this.date = date;
        this.time = time;
        this.id = id;
        this.status = status;
    }
    public addInstallDate(String date, String time, String status) {
        this.date = date;
        this.time = time;
        this.id = Database.get_install_id();
        this.status = status;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return id +
                "      " + date +
                "      " + time +
                "      " + status + "\n";
    }
    public boolean is_taken_date(){
        boolean flage=false;
        for(addInstallDate date1:Database.gitDate()){
            if(date1.getDate().equals(this.getDate())&&date1.getTime().equals(this.getTime())) {

                flage=true;
            }
        }
        return flage;
    }

    public boolean isExitdate(){
        boolean flag=false;
        for(addInstallDate addInstallDate:Database.gitDate()){
            if(addInstallDate.getId()==this.id){
                flag=true;
                break;
            }
        }
        return flag;
    }
    public  boolean isValidDate(){

        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern=Pattern.compile(dateRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(this.getDate());
        return matcher.find();
    }
    public  boolean isValidTime() {
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5][0-9]\\s(?:AM|PM)$";
        Pattern pattern = Pattern.compile(timeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.getTime());
        return matcher.matches();
    }
    public boolean isUnUniqueDate(String date, String time) {
        String date_time = date + "      " + time;
        List<String> Instal = new ArrayList<>();

        for (String install : Database.getObjects("dates")) {
            String[] arr = install.split("      ");
            if (arr.length >= 3) { // Ensure the array has at least three elements
                String dateAndTime = arr[1] + "      " + arr[2];
                Instal.add(dateAndTime);
            } else {
                // Handle the case when the split result doesn't have enough elements
                // Log an error or handle it based on your application's logic
            }
        }

        return Instal.contains(date_time);
    }








}
