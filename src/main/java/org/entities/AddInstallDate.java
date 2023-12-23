package org.entities;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AddInstallDate {
    private int id;
    private String date;
    private String time;
    private String status;
    public AddInstallDate(){}
    public AddInstallDate(int id, String date, String time, String status) {
        this.date = date;
        this.time = time;
        this.id = id;
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
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return MessageFormat.format("{0}      {1}      {2}      {3}\n", id, date, time, status);
    }
    public boolean isExitDate(){
        boolean flag=false;
        for(AddInstallDate addInstallDate:Database.gitDate()){
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
        String timeRegex = "^(0?[1-9]|1[0-2]):[0-5]\\d\\s(?:AM|PM)$";
        Pattern pattern = Pattern.compile(timeRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.getTime());
        return matcher.matches();
    }
}
