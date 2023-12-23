package org.classes;
import org.entities.Register;
import org.entities.Database;
import java.util.Scanner;
import java.util.logging.Logger;
public class RegisterCustomer {
    public static final Logger logger=Logger.getLogger(RegisterCustomer.class.getName());
    public Register recordCustomer(){
        Scanner in=new Scanner(System.in);
        Register regis = new Register();
        logger.info("Enter customer Name: ");
        regis.setName(in.nextLine());
        logger.info("Enter customer Email:  ");
        String email = in.nextLine();
        regis.setEmail(email);
        logger.info("Enter customer Phone:  ");
        regis.setPhone(in.nextLine());
        logger.info("Enter customer Address:  ");
        regis.setAddress(in.nextLine());
        logger.info("Enter customer Password:  ");
        regis.setPassword(in.nextLine());
        regis.setId(Database.getId());
        if(regis.isUnUniqueEmail()){
            logger.info("This email is already taken.....PLEAS! Enter new Email");
            regis.setEmail(in.nextLine());
        }
        addNewCustomer(regis);
        return regis;
    }
    public void addNewCustomer(Register regis) {
        Database.storeObject("RecordCustomer",regis);
        Login2 login=new Login2();
        login.setEmail(regis.getEmail());
        login.setPassword(regis.getPassword());
        login.setRul("customer");
        Database.storeObject("Login",login);
        logger.info("You have been one of our Customer, Thank you!");
    }
    public void takenMsg() {
        logger.info("This email is already taken  ");
    }
}