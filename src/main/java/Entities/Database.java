package Entities;

import Classes.Login2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Database {
    static String msg= "Error";
    private Database(){
    }
    static String path="src/main/resources/BE/";
    static Logger logger = Logger.getLogger(Database.class.getName());
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
            String []arr=   COTM.split(",,");
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
               if(findCategories.getId()==id){
                   findCategories=cat;
                   break;
               }
        }
        return findCategories;
    }

    public static Categories getCategoriesByName(String name) {
        Categories findCategories=new Categories();
        for(Categories cat: getCategories()){
            if(findCategories.getName().equalsIgnoreCase(name)){
                findCategories=cat;
                break;
            }
        }
        return findCategories;
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
            Categories category=new Categories(Integer.parseInt(arr[0]),arr[1]);
            categories.add(category);
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
}
