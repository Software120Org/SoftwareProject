package Classes;

import Entities.Database;
import Entities.Product;
import Entities.ProductInfo;
import Entities.Regis;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerDashboard {
    private Regis customer;

    public Regis getCustomer() {
        return customer;
    }

    Logger logger = Logger.getLogger(CustomerDashboard.class.getName());
    public void menu(){
        logger.info("If you want to see all products enter number 1");
        logger.info("If you want to search for any product enter number 2");
        logger.info("If you want to see your information enter number 3");
        logger.info("If you want to update your information enter number 4");
        logger.info("If you want to logout enter number 5");

    }

    public void customerPage() {
        logger.info("Welcome To The Customer Dashboard ");
        Scanner input=new Scanner(System.in);
        menu();
        try {
            int option = input.nextInt();

            while (true){
                if(option==1){
                    printProducts();
                }

                else if (option==2) {
                 searchProduct();
                }

                else if(option==3){
                    logger.info(()->String.valueOf(customer));
                }

                else if (option==4) {
                    updateMenu();
                    int x=input.nextInt();
                    String attribute="";
                    String value="";
                    if (x == 1) {
                        logger.info("Enter new password: ");
                        value = input.nextLine();
                        attribute="password";

                    } else if (x == 2) {
                        logger.info("Enter New phone number: ");
                        value = input.nextLine();
                        attribute="phone";

                    } else if (x == 3) {
                        logger.info("Enter New address: ");
                        value = input.nextLine();
                        attribute = "address";

                    }

                    updateInformation(attribute,value);
                }

                else if(option==5){
                    break;
                }
                menu();
                option = input.nextInt();
            }
        }
        catch (InputMismatchException e){
            logger.info("please enter a valid number :( ");
        }

    }




    public void printProducts(){
        List<Product> products = ProductInfo.getProduct();
        logger.info("************************************************************** Products ***************************************************************");
        logger.info("      ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProductName()+"\t\t\t\t"+
                    product.getDescription()+"\t\t"+product.getCategory()+
                    "\t\t"+product.getPrice()+"\t\t"+product.getAvailability());

        }
    }

    public void searchProduct(){
        logger.info("Enter the name of the product you want to search:");
        Scanner in=new Scanner(System.in);
        String name = in.nextLine();
        List<Product> products = ProductInfo.searchProduct(name);
        logger.info("**************************************************************** Products **********************************************************");
        logger.info("      ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProductName()+"\t\t\t\t"+
                    product.getDescription()+"\t\t"+product.getCategory()+
                    "\t\t"+product.getPrice()+"\t\t"+product.getAvailability());

        }
    }


    public void updateMenu(){
        logger.info("If you want to update your password enter number 1");
        logger.info("If you want to update your phone number enter number 2");
        logger.info("If you want to update your address enter number 3");
    }

    public void updateInformation(String attribute, String value) {

        if (attribute.equalsIgnoreCase("Phone")) {
            customer.setPhone(value);
        } else if (attribute.equalsIgnoreCase("Password")) {
            customer.setPassword(value);
        } else if (attribute.equalsIgnoreCase("Address")) {
            customer.setAddress(value);
        }
        List<Regis> customers = Database.getCustomer();
        for (Regis customer1 : customers) {
            int ind = customers.indexOf(customer1);
            if (customer1.getId() == customer.getId()) {
                customers.remove(ind);
                customers.add(ind, customer);
                logger.info("Customer information updated successfully :))");
                break;

            }
        }

        Database.updateCustomers(customers);
    }

    public void updatedMessage() {
        logger.info("Your information updated Successfully :))");
    }
    public void setCustomer (Regis customer){
                this.customer = customer;
            }

        }