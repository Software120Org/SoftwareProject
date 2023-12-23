package org.entities;
import java.util.*;
import java.util.regex.*;
public class Register {
    private int id ;
    private String name ;
    private String password;
    private String email;
    private String phone;
    private String address;
    public Register(){}
    public Register(int id, String name, String email, String phone, String address, String password){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }
    public Register(String name, String email, String phone, String address, String password){
        this.id = Database.getId();
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password= password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone= phone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email= email;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address= address;
    }
    @Override
    public String toString() {
        return  id +
                "," + name +
                ","  +email+
                "," + phone +
                "," + address +
                "," + password +"\n";
    }
    public boolean isUnUniqueEmail() {
        List<Register>registers;
        registers=Database.getCustomer();
        int flag =0;
        for(Register customer: registers){
            if(customer.getEmail().equals(this.getEmail())){
                flag=1;
                break;
            } }
        return flag == 1;
    }
    public boolean isValidEmail(){
        String enterEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,9}$";
        Pattern pattern=Pattern.compile(enterEmail,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(this.getEmail());
        return matcher.find();
    }
    public boolean isExitCustomer(){
        boolean flag=false;
        for(Register customer: Database.getCustomer()){
            if(customer.getId()==this.id){
                flag=true;
                break;
            }
        }
        return flag;
    }

}