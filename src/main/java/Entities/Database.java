package Entities;

import Classes.Login2;

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
    public static List<Login2> users() {
        List<Login2> list = new ArrayList<>();
        List<String> objects = getObjects("Login");

        if (objects != null) {
            for (String value : objects) {
                String[] arr = value.split(" ");

                if (arr.length >= 3) {
                    Login2 login = new Login2(arr[0], arr[1], arr[2]);
                    list.add(login);
                } else {

                }
            }
        } else {

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
    public static List<addInstallDate> gitDate() {
        List<addInstallDate> installDates = new ArrayList<>();
        for (String install : getObjects("dates")) {
            String[] arr = install.split("      ");
            try {
                if (arr.length >= 4) {
                    addInstallDate addInstallDate = new addInstallDate(
                            Integer.parseInt(arr[0]),
                            arr[1],
                            arr[2],
                            arr[3] // Assuming status is at index 3 in your data
                    );
                    installDates.add(addInstallDate);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Handle parsing or index out of bounds exception if necessary
            }
        }
        return installDates;
    }

    public static int get_install_id() {
        List<Entities.addInstallDate> gitDates = gitDate();

        if (gitDates != null && !gitDates.isEmpty()) {
            int id = gitDates.get(gitDates.size() - 1).getId();
            return id + 1;
        } else {
            // Handle case where gitDate() returns null or an empty list
            return 0;
        }
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
    public static addInstallDate getDateById(int id){
        addInstallDate addInstallDate=new addInstallDate();
        for (addInstallDate add:gitDate() ){
            if (add.getId()==id){
                addInstallDate =add;
                break;
            }
        }return addInstallDate;
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

    public static void updateInstallation(List<addInstallDate>addInstallDates){
        try (RandomAccessFile raf = new RandomAccessFile("src/main/resources/BE/dates.txt", "rw")
        ){
            removeFileContent("dates");
            raf.seek(0);
            for (addInstallDate install : addInstallDates) {
                raf.write(install.toString().getBytes());
            }
        }
        catch(Exception e){ //ignored

        }
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
    public static List<Entities.Installation> getInstall() {
        List<Entities.Installation> Instal = new ArrayList<>();
        for (String inst : getObjects("install")) {
            String[] arr = inst.split(",,");
            try {
                if (arr.length >= 7) {
                    int instId = Integer.parseInt(arr[0]);
                    Entities.Installation installation = new Entities.Installation(instId, arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
                    Instal.add(installation);
                } else {
                    System.err.println("Incomplete data: " + inst);
                    // Log or handle incomplete data as needed
                }
            } catch (NumberFormatException e) {
                System.err.println("Error parsing data: " + inst);
                e.printStackTrace(); // Log the exception or handle it appropriately
            }
        }
        return Instal;
    }

    public static int getReqId() {
        List<Entities.Installation> installations = getInstall();

        int maxId = 0;
        for (Entities.Installation installation : installations) {
            if (installation.getId() > maxId) {
                maxId = installation.getId();
            }
        }

        return maxId + 1;
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
