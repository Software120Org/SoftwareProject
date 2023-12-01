package Entities;

import Classes.Login2;
import Classes.Order;
import Classes.RegisCust;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class Database {
    static String msg= "Error";
    static String path="src/main/resources/BE/";
    static Logger logger = Logger.getLogger(Database.class.getName());


    private Database(){
    }


    public static List<String> getObjects(String fileName){
        List<String> objects=new ArrayList<>();
        try ( RandomAccessFile raf =new RandomAccessFile(path+fileName+".txt", "rw")){
            raf.seek(0);
            String s;
            while ((s = raf.readLine()) != null) {
                objects.add(s);
            }
        }
        catch(Exception ignored){
        }
        return objects;
    }
    public static void storeObject(String fileName,Object object){
        try ( RandomAccessFile raf =new RandomAccessFile(path+fileName+".txt", "rw")){

            raf.seek(raf.length());
            raf.write(object.toString().getBytes());
        }
        catch(Exception e){
            logger.info(msg);
            System.out.println(e);

        }
    }
    public static List<Login2> users(){
        List<Login2> list = new ArrayList<>();
        for (String value:getObjects("Login")){
            String[] arr = value.split(" ");
            Login2 login=new Login2(arr[0],arr[1],arr[2]);
            list.add(login);
        }

        return list;
    }


    public static List<Entities.Regis>getCustomer(){
        List<Entities.Regis> cost =new ArrayList<>();
        for(String COTM:getObjects("RecordCustomer")){
            String []arr=   COTM.split(",");
            try {
                int customerId = Integer.parseInt(arr[0]);
                Entities.Regis customer = new Entities.Regis(customerId, arr[1], arr[2], arr[3], arr[4], arr[5]);
                cost.add(customer);
                // System.out.println(customer);
            } catch (NumberFormatException e) {
                //  System.err.println("\\\\\\\\\\ " );

            }

        }
        return cost;
    }


    public static void updateCustomers(List<Regis>customers){
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/BE/RecordCustomer.txt", "rw")
        ){
            removeFileContent("RecordCustomer");
            raf.seek(0);
            for (Regis customer : customers) {
                raf.writeBytes(customer.getId() + "," +customer.getname() + "," + customer.getEmail() + "," + customer.getPhone() + "," +
                        customer.getAddress()+","+customer.getPassword() +"\r\n");

            }
        }
        catch(Exception e){//ignored
        }
    }


    public static Entities.Regis GitCustomerbyE(String email){
        Entities.Regis existCustomer=new Entities.Regis();
        for (Entities.Regis regis:getCustomer()){
            if((regis.getEmail().equals(email))){
                existCustomer=regis;
                break;
            }
        }
        return existCustomer;
    }
    public static int get_Id() {
        int id;

        // Assuming you have a global variable or some method to get the list of customers
        List<Entities.Regis> customerList = getCustomer();

        // Check if the list is not empty
        if (!customerList.isEmpty()) {
            id = customerList.get(customerList.size() - 1).getId();
            return id + 1;
        } else {
            // If the list is empty, start with ID 1 or any other desired initial ID.
            return 1;
        }
    }


    public static void removeFileContent(String fileName){
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(path+fileName+".txt"))
        ){
            writer.write("");
            writer.flush();
        }catch (Exception ignored) {//ignored


        }
    }





    public static Categories getCategoriesById(int id) {
           Categories findCategories=new Categories();
           for(Categories cat: getCategories()){
               if(cat.getId()==id){
                   findCategories=cat;
                   break;
               }
        }
        return findCategories;
    }


public static Regis getCustomerById(int id){
    Regis customer=new Regis();
    for (Regis custom:getCustomer()){
        if(custom.getId()==id){
            customer=custom;
            break;
        }
    }
    return customer;
}

    public static void updateLogin(List<Login2> loginList) {
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/BE/Login.txt", "rw")
        ){
            removeFileContent("Login");
            raf.seek(0);
            for (Login2 login:loginList) {
                raf.write(login.toString().getBytes());
            }
        }

        catch(Exception e){
            logger.info(msg);

        }
    }

    public static int getCategoryId() {
        int id;

        id=getCategories().get(getCategories().size()-1).getId();
        return id+1;
    }

    public static List<Categories> getCategories(){
        ArrayList<Categories> categories = new ArrayList<>();
        for (String value:getObjects("Categories")){
            String[] arr = value.split(",");

            try {
                Categories category=new Categories( Integer.parseInt(arr[0]),arr[1]);
                categories.add(category);

            } catch (NumberFormatException e) {

            }

        }
        return categories;
    }



    public static void updateCategories(List<Categories>categories){
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/BE/Categories.txt", "rw")
        ){
            removeFileContent("Categories");
            raf.seek(0);
            for (Categories category : categories) {
                raf.write(category.toString().getBytes());
            }
        }
        catch(Exception e){ //ignored

        }
    }



    public static void updateProducts(List<Product>products){
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/BE/Products.txt", "rw"))
        {
            removeFileContent("Products");
            raf.seek(0);
            for (Product product:products) {
                raf.write(product.toString().getBytes());

            }
        }
        catch(Exception e){
            logger.info(msg);

        }
    }




    public static List<Order> getOrders(){
        List<Order> orders = new ArrayList<>();

            for (String value : getObjects("Orders")) {
                List<Product> products;
                String[] arr = value.split(",");
                Order order = new Order();
                order.setId(Integer.parseInt(arr[0]));
                Regis customer = getCustomerById(Integer.parseInt(arr[1]));
                order.setDate(LocalDate.parse(arr[2]));
                order.setTotalPrice(Double.parseDouble(arr[3]));
                order.setProductID(Integer.parseInt(arr[4]));
                order.setCustomer(customer);
                products = getProductByOrder(order.getProductID());
                order.setProducts(products);
                orders.add(order);
            }

        return orders;
    }


    public static int getOrderId(){
        int id;
        id=getOrders().get(getOrders().size()-1).getId();
        return id+1;
    }


    public  static List<Product> getProductByOrder(int id){
        List<Product> products=new ArrayList<>();
        for (Product product1: ProductInfo.getProduct()){
            if(product1.getProduct_id()==id){
                products.add(product1);
            }
        }

        return products;
    }


    public static List<Order> getOrderByCustomer(Regis customer){
        List<Order>orders=new ArrayList<>();
        for (Order order1:getOrders()){
            if(order1.getCustomer().getId()==(customer.getId())){
                orders.add(order1);
            }
        }
        return orders;
    }


    public static Regis getCustomerByEmail(String email) {
        Regis foundCustomer=new Regis();
        for(Regis customer:getCustomer()){
            if(customer.getEmail().equals(email)){
                foundCustomer=customer;
                break;
            }
        }
        return foundCustomer;
    }
}
