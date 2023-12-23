package org.classes;
import org.entities.Database;
import org.entities.Product;
import org.entities.ProductInfo;
import org.entities.Register;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
public class CustomerDashboard {
    private Register customer;
    public Register getCustomer() {
        return customer;
    }
    static final Logger logger = Logger.getLogger(CustomerDashboard.class.getName());
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
        Scanner input1 = new Scanner(System.in);
        boolean continueRunning = true;
        while (continueRunning) {
            menu();

            try {
                int option1 = input1.nextInt();
                continueRunning = handleOption(option1);
            } catch (InputMismatchException e) {
                logger.info("Please enter a valid number :( ");
                input1.nextLine();
            }
        }

        input1.close();
    }
    private boolean handleOption(int option) {
        switch (option) {
            case 1:
                printProducts();
                break;
            case 2:
                searchProduct();
                break;
            case 3:
                takenOrder();
                break;
            case 4:
                viewOrders();
                break;
            case 5:
                recordInstallation();
                break;
            case 6:
                displayCustomerInfo();
                break;
            case 7:
                updateCustomerInformation();
                break;
            case 8:
                logger.info("Goodbye :)))");
                return false;
            default:
                logger.info("Invalid option. Please try again.");
        }
        return true;
    }
    private void recordInstallation() {
        InstallRequests recordInstallation = new InstallRequests();
        recordInstallation.recordInstallation();
    }
    private void displayCustomerInfo() {
        logger.info(() -> String.valueOf(customer));
    }
    private void updateCustomerInformation() {
        updateMenu();
        Scanner input2 = new Scanner(System.in);
        int x = input2.nextInt();
        String attribute = "";
        String value = "";
        if (x == 1) {
            logger.info("Enter new password: ");
            value = input2.nextLine();
            attribute = "password";
        } else if (x == 2) {
            logger.info("Enter New phone number: ");
            value = input2.nextLine();
            attribute = "phone";
        } else if (x == 3) {
            logger.info("Enter New address: ");
            value = input2.nextLine();
            attribute = "address";
        }
        updateInformation(attribute, value);
    }
    public void printProducts(){

        List<Product> products = ProductInfo.getProduct();
        logger.info("************************************************************** Products ***************************************************************");
        logger.info("ProductID      ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product : products) {
            Boolean availability = product.getAvailability();
            if (availability != null) {
                String formattedInfo = String.format(
                        "%-10s %-20s %-30s %-15s %-10s %-10s",
                        product.getProductId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getPrice(),
                        availability);
                logger.info(formattedInfo);
            }
        }
    }
    public void searchProduct(){
        logger.info("Enter the name of the product you want to search:");
        Scanner in3=new Scanner(System.in);
        String name = in3.nextLine();
        List<Product> products = ProductInfo.searchProduct(name);
        logger.info("**************************************************************** Products **********************************************************");
        logger.info("ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product : products) {
            String logMessage = String.format("%-20s %-30s %-15s %-10s %-5s",
                    product.getProductName(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getAvailability());
            logger.info(logMessage);
        }
    }
    public Order takeOrder() {
        List<Product> products = new ArrayList<>();
        Order order = new Order();
        order.setCustomer(this.customer);
        while (true) {
            printProducts();
            Scanner input4 = new Scanner(System.in);
            logger.info("Enter the product id you want to add in your orders: ");
            int id = input4.nextInt();
            Product product1;
            product1=ProductInfo.getProductByProductId(id);
            products.add(product1);
            double out=0;
            for (Product product:products){
                order.setId(Database.getOrderId());
                order.setCustomer(this.customer);
                order.setDate(LocalDate.now());
                order.setProductID(product.getProductId());
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
        List<Register> customers = Database.getCustomer();
        for (Register customer1 : customers) {
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
    public void setCustomer (Register customer){
                this.customer = customer;
            }
}