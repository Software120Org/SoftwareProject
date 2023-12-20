package Classes;

import Entities.Database;
import Entities.Product;
import Entities.ProductInfo;
import Entities.Regis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerDashboard {
    private Regis customer;


    public Regis getCustomer() {
        return customer;
    }

    static Logger logger = Logger.getLogger(CustomerDashboard.class.getName());
    public void menu(){
        logger.info("If you want to see all products enter number 1");
        logger.info("If you want to search for any product enter number 2");
        logger.info("If you want to add new order enter number 3");
        logger.info("If you want to see your orders enter number 4");
        logger.info("If you want, submit an installation request, enter number 5");
        logger.info("If you want to see your information enter number 6");
        logger.info("If you want to update your information enter number 7");
        logger.info("If you want to logout enter number 8");

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

                else if (option==3) {
                    takenOrder();
                }

                else if (option==4) {
                    viewOrders();
                }
                else if (option==5) {

                    InstallRequests recordInstallation = new InstallRequests();
                    recordInstallation.recordInstallation();

                }
                else if(option==6){
                    logger.info(()->String.valueOf(customer));
                }

                else if (option==7) {
                    updateMenu();
                    Scanner input2=new Scanner(System.in);
                    int x=input.nextInt();
                    String attribute="";
                    String value="";
                    if (x == 1) {
                        logger.info("Enter new password: ");
                        value = input2.nextLine();
                        attribute="password";

                    } else if (x == 2) {
                        logger.info("Enter New phone number: ");
                        value = input2.nextLine();
                        attribute="phone";

                    } else if (x == 3) {
                        logger.info("Enter New address: ");
                        value = input2.nextLine();
                        attribute = "address";

                    }

                    updateInformation(attribute,value);
                }

                else if(option==8){
                    logger.info("Good bye :)))");
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
        logger.info("ProductID      ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProduct_id()+"\t\t\t\t"+product.getProductName()+"\t\t\t\t"+
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
        logger.info("ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProductName()+"\t\t\t\t"+
                    product.getDescription()+"\t\t"+product.getCategory()+
                    "\t\t"+product.getPrice()+"\t\t"+product.getAvailability());

        }
    }




    public Order takeOrder() {
        List<Product> products = new ArrayList<>();
        Order order = new Order();
        order.setCustomer(this.customer);
        while (true) {
            printProducts();
            Scanner input = new Scanner(System.in);
            logger.info("Enter the product id you want to add in your orders: ");
            int id = input.nextInt();
            Product product1;
            product1=ProductInfo.getProductByProductId(id);
            products.add(product1);
            double out=0;
            for (Product product:products){
                order.setId(Database.getOrderId());
                order.setCustomer(this.customer);
                order.setDate(LocalDate.now());
                order.setProductID(product.getProduct_id());
                out+=product.getPrice();
                order.setTotalPrice(out);
                order.setProductID(id);

            }
            order=new Order(products);
            order.setProducts(products);
            order.setProductID(id);
            Scanner in=new Scanner(System.in);
            logger.info("Do you want to add another product to this order?  \"(yes / no)\"");
            String ans = in.nextLine();
            if (ans.equalsIgnoreCase("no")) {
                break;
            }
        }

        return order;

    }




    public void takenOrder(){
        Order order=takeOrder();
        order.setCustomer(this.customer);
        logger.info("The total price is: "+order.getTotalPrice());
        addOrder(order);

    }


    public void addOrder(Order order) {
        Database.storeObject("Orders",order);
        logger.info("The Order Added Successfully :))");
    }


    public void viewOrders() {

        logger.info("Customer Name\t\t\tOrder Date\t\t\tTotal Price \n Your products:  ");
        for (Order order : Database.getOrderByCustomer(customer)) {

            logger.info(order.getString());


        }


    }




    public void updateMenu(){
        logger.info("If you want to update your password enter number 1");
        logger.info("If you want to update your phone number enter number 2");
        logger.info("If you want to update your address enter number 3");
    }


    public void updateInformation(String attribute, String value) {
        Login2 login2 = new Login2();
        login2.setEmail(customer.getEmail());
        login2.setRul("customer");
        if (attribute.equalsIgnoreCase("Phone")) {
            customer.setPhone(value);


        } else if (attribute.equalsIgnoreCase("Password")) {
            customer.setPassword(value);
            login2.setPassword(value);
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
        List<Login2> login = Database.users();
        for (Login2 login1 : login) {
            int ind = login.indexOf(login1);
            if (login1.getEmail().equalsIgnoreCase(customer.getEmail())) {
                login.remove(ind);
                login.add(ind, login2);
                break;

            }
        }

        Database.updateCustomers(customers);
        Database.updateLogin(login);

    }


    public void updatedMessage() {
        logger.info("Your information updated Successfully :))");
    }

    public void setCustomer (Regis customer){
                this.customer = customer;
            }

}
