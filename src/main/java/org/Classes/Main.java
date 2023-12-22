package org.Classes;



import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void menu(){
        logger.info("Welcome to A&W car company :) ");
        logger.info("If you want to login enter number 1");
        logger.info("If you are new customer enter number 2");

    }

    public static void main(String[]args) {



        Scanner in = new Scanner(System.in);
        int option = 0;

        try {
            menu();
            option = in.nextInt();
        } catch (InputMismatchException e) {
            logger.info("Please enter a valid number");
            main(new String[5]);
        }
        if (option == 1) {
            userLogin();
        } else if(option==2) {
            RegisCust recordCustomer = new RegisCust();
            recordCustomer.recordCustomer();
        }

    }

    public static void userLogin(){

        Scanner in=new Scanner(System.in);
        logger.info("*************** Welcome Back ***************");
        while(true) {
            logger.info("Enter your email: ");
            String email = in.nextLine();
            logger.info("Enter your password: ");
            String password = in.nextLine();
            Login2 login=new Login2();
            login.setEmail(email);
            login.setPassword(password);
            if(email.equals("adminClean@gmail.com")){
              //  logger.info("hi iam here");
                login.setRul("admin");
            }
            else login.setRul("customer");
            if(login.isCorrectInfo()){
                login.login();
                break;
            }
            else {
                logger.info("Incorrect user name or password, try again");
            }

        }
    }
}