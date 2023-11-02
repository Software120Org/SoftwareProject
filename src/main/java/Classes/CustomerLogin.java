package Classes;

import Entities.Regis;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerLogin {
    private Regis customer;

    public Regis getCustomer() {
        return customer;
    }

    Logger logger = Logger.getLogger(CustomerLogin.class.getName());
    public void menu(){
        logger.info("المفروض هون تكون في مينيو لليوزر حسب مشروعنا ");


    }

    public void customerPage() {
        logger.info("Welcome To The Customer Dashboard ");
        menu();
                }

            public void setCustomer (Regis customer){
                this.customer = customer;
            }

        }