package org.Classes;

import Entities.*;
import Entities.Categories;

import java.util.*;
import java.util.logging.Logger;

public class AdminDashboard {
    Admin admin = new Admin();

    Logger logger = Logger.getLogger(AdminDashboard.class.getName());

    String msg="Enter valid number: ";
    String msg2="Invalid Input, try again...";

    String space="\t\t\t\t";

    public void setEmail(String email) {
        this.admin.setEmail(email);
    }
    public void adminDashboard() {
        logger.info("To view all customers details enter number 1 ");
        logger.info("To view all products categories enter number 2 ");
        logger.info("To view all products listings enter number 3 ");
        logger.info("To mange installation times enter number 4");
        logger.info("To logout enter number 5");
    }

    public void adminPage() {
        Scanner in = new Scanner(System.in);
        boolean cond = true;
        while (cond) {
            try {
                adminDashboard();
                int option = in.nextInt();
                if (option == 5) {
                    logger.info("Goodbye");
                    cond = false;
                } else {
                    adminOptions(option);
                }

            } catch (Exception e) {
                logger.info("Please enter a valid option number!! ");
                break;
            }
        }
    }
    public void adminOptions(int select){
        if (select ==1){
            customerMenu();
        }
        else if (select ==2) {

            categoriesMenu();
        }
        else if (select ==3) {

            productsMenu();
        }
        else if (select==4) {
            intallationMenu();
        }

    }

    public void customerMenu() {
        boolean cond=true;
        while (cond) {
            logger.info("If you want to see all customers information enter number 1");
            logger.info("If you want to add new customer enter number 2");
            logger.info("If you want to delete customer enter number 3");
            logger.info("If you want to back enter number 4");
            Scanner in = new Scanner(System.in);
            int x;
            try{
                x= in.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }

            if (x == 4) {
                cond=false;
            }
            else{
                customerOptions(x);
            }
        }
    }
    public void customerOptions(int option) {
        if(option==1){
            List<Regis> customers = Database.getCustomer();
            logger.info("******************************************************* Customers **************************************************************");
            logger.info("CustomerID         Name                             Email                          Phone number                      Address  " );
            for (Regis customer : customers) {
                logger.info(customer.getId() + space +customer.getname() + Spaces(customer.getname()) + customer.getEmail() + Spaces(customer.getEmail())
                        + customer.getPhone() + Spaces(customer.getPhone()) + customer.getAddress()
                );
            }


        } else if (option==2) {

            RegisCust customer=new RegisCust();
            customer.recordCustomer();

        } else if (option==3) {

            deleteCustomer();
        }
    }

    public void deleteCustomer() {
        logger.info("Enter the ID of the customer you want to delete: ");
        Scanner in=new Scanner(System.in);
        Regis customer=new Regis();
        boolean flag = false;
        try {
            int id=in.nextInt();
            customer.setId(id);
            flag=customer.isExitCustomer();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag) {
           deleteCustomer(customer);
            logger.info("Customer removed successfully :)");
        }
        else{
            logger.info("This customer doesn't exist in our customers! :( ");
        }
    }

    public void deleteCustomer(Regis customer){

        List<Regis>customers=Database.getCustomer();
        for(Regis customer1:customers){
            if(customer1.getId()==(customer.getId())){
                customers.remove(customer1);
                break;
            }
        }
        Database.updateCustomers(customers);

    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public void categoriesMenu() {
        boolean cond=true;
        while (cond) {
            logger.info("If you want to see all categories enter number 1");
            logger.info("If you want to add new category enter number 2");
            logger.info("If you want to delete category enter number 3");
            logger.info("If you want to edit category enter number 4");
            logger.info("If you want to back enter number 5");
            Scanner input2 = new Scanner(System.in);
            int k;
            try{
                k= input2.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }

            if (k == 5) {
                cond=false;
            }
            else{
                categoriesOptions(k);
            }
        }
    }


    public void categoriesOptions(int select){
        if(select==1){
            printCategories();
        }
         else if(select==2){
           addCategory();

        } else if (select==3) {
            deleteCategory();

        } else if (select==4) {
            updateCategory();
        }
    }


    public void addCategory( ) {
     Scanner input = new Scanner(System.in);
     Categories categories1=new Categories();
     logger.info("Enter category name you want to add: ");
     categories1.setName(input.nextLine());

     categories1.setId(Database.getCategoryId());

     addCategory(categories1);

    }

    public void addCategory(Categories categories){
        Database.storeObject("Categories",categories);
        logger.info("The category added successfully :)");
    }
    public void deleteCategory() {
        logger.info("Enter the id of the category you want to delete:  ");
        Scanner in=new Scanner(System.in);
        Categories categories=new Categories();
        boolean flag = false;
        try {
            int id =in.nextInt();
            categories.setId(id);
            flag=categories.isExitCategories();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag) {
            deleteCategory(categories);
            logger.info("The category removed successfully :)");
        }
        else{
            logger.info("This category doesn't exist !!!");
        }
    }

    public void deleteCategory(Categories category){
        List<Categories>categories=Database.getCategories();
        for(Categories categories1:categories){
            if(categories1.getId() == category.getId()){
                categories.remove(categories1);
                break;
            }
        }
       Database.updateCategories(categories);

    }

    public void updateCategory() {
        logger.info("Enter the id of the category you want to update:");
        Scanner input = new Scanner(System.in);
        Categories category = new Categories();
        boolean flag = false;
        int id=0;
        try {
            id = input.nextInt();
            category.setId(id);
            flag = category.isExitCategories();
        }
        catch (InputMismatchException e) {
            logger.info(msg2);
        }

        if (!flag) {
            logger.info("This category doesn't exist");
        }
        else {
            category = Database.getCategoriesById(id);
            category.setId(id);
            logger.info("Enter the new name: ");
            Scanner input2 = new Scanner(System.in);
            String value = input2.nextLine();
            updateCategory(value,category);



        }
    }

        public void updateCategory(String name, Categories category){
            category.setName(name);
            List<Categories> categories = Database.getCategories();
            for (Categories categories1 : categories) {
                int ind = categories.indexOf(categories1);
                if (categories1.getId() == category.getId()) {
                    categories.remove(ind);
                    categories.add(ind,category);
                    logger.info("Category updated successfully :)");
                    break;
                }
            }
            Database.updateCategories(categories);


        }

        public void printCategories(){
        List<Categories> categories = Database.getCategories();
        logger.info("******************* Categories ********************");
        logger.info("ID            Name ");
        for (Categories categories1:categories){
            logger.info(categories1.getId()+space+ categories1.getName());
        }
        }

    public boolean isExistCustomer(int id){
        int flag=0;
        for(Regis customer:Database.getCustomer()){
            if(customer.getId()==id){
                flag=1;
                break;
            }
        }
        return flag==1;
    }
    public boolean isExistCategory(int id){
        int flag=0;
        for(Categories categories:Database.getCategories()){
            if(categories.getId()==id){
                flag=1;
                break;
            }
        }
        return flag==1;
    }




    public void productsMenu() {
        boolean cond=true;
        while (cond) {
            logger.info("If you want to see all products enter number 1");
            logger.info("If you want to add new product enter number 2");
            logger.info("If you want to search for any product enter number 3");
            logger.info("If you want to update product information enter number 4");
            logger.info("If you want to back enter number 5");
            Scanner input2 = new Scanner(System.in);
            int k;
            try{
                k= input2.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }

            if (k == 5) {
                cond=false;
            }
            else{
                productsOptions(k);
            }
        }
    }


    public void productsOptions(int select){
        if(select==1){
            printProducts();
        }
        else if(select==2){
            addProduct();

        } else if (select==3) {
          searchProduct();

        }
        else if (select==4) {
            updateProduct();

        }
    }


    public void printProducts(){
        List<Product> products = ProductInfo.getProduct();
        logger.info("***************************************************************** Products ****************************************************************************");
        logger.info("ProductId      ProductName                             Description                                              CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProduct_id()+"\t\t"+product.getProductName()+space+
                    product.getDescription()+"\t\t"+product.getCategory()+
                    "\t\t"+product.getPrice()+"\t\t"+product.getAvailability());

        }
    }

    public void addProduct( ) {
        Scanner input = new Scanner(System.in);
        Product product = new Product();

        logger.info("Enter product name: ");
        product.setProductName(input.nextLine());
        logger.info("Enter product description: ");
        product.setDescription(input.nextLine());
        logger.info("Enter product category (Interior, Exterior, Electronics): ");
        product.setCategory(Category.valueOf(input.nextLine()));
        logger.info("Enter product price: ");
        product.setPrice(Double.parseDouble(input.nextLine()));
        logger.info("Enter product availability (true,false): ");
        product.setAvailability(Boolean.getBoolean(input.nextLine()));
        product.setProduct_id(ProductInfo.getProductId());
        addProduct(product);

    }

    public void addProduct(Product product){
        Database.storeObject("Products",product);
        logger.info("The product added successfully :)");
    }


    public void searchProduct(){
        logger.info("Enter the name of the product you want to search:");
        Scanner in=new Scanner(System.in);
        String name = in.nextLine();
        List<Product> products = ProductInfo.searchProduct(name);
        logger.info("**************************************************************** Products **********************************************************");
        logger.info("ProductName                             Description                                          CategoryName      Price      Availability ");
        for (Product product:products){
            logger.info(product.getProductName()+space+
                    product.getDescription()+"\t\t"+product.getCategory()+
                    "\t\t"+product.getPrice()+"\t\t"+product.getAvailability());

        }
    }

    public void updateProduct() {
        logger.info("Enter the id of the product you want to update:");
        Scanner input = new Scanner(System.in);
        Product product=new Product();
        boolean flag = false;
        int id=0;
        try {
            id= input.nextInt();
            product.setProduct_id(id);
            flag = product.isExitProduct();
        }
        catch (InputMismatchException e) {
            logger.info(msg2);
        }

        if (!flag) {
            logger.info("This product doesn't exist");
        }
        else {


            product=ProductInfo.getProductByProductId(id);
            product.setProduct_id(id);
            Scanner input2 = new Scanner(System.in);
            logger.info("Enter the attribute you want to update (Name, Description, Category, Price, Availability) ");
            String attribute = input2.nextLine();
            Scanner input3 = new Scanner(System.in);
            logger.info("Enter the new value: ");
            String value=input3.nextLine();
            updateProduct(attribute,value,product);


        }
    }


    public void updateProduct(String attribute, String value, Product product) {
        if(attribute.equalsIgnoreCase("Name")){
          product.setProductName(value);

        } else if (attribute.equalsIgnoreCase("Description")) {
            product.setDescription(value);
        } else if (attribute.equalsIgnoreCase("Category")) {
           product.setCategory(Category.valueOf(value));
        } else if (attribute.equalsIgnoreCase("Price")) {
          product.setPrice(Double.parseDouble(value));
        }
        else if (attribute.equalsIgnoreCase("Availability")) {
           product.setAvailability(Boolean.valueOf(value));
        }
        List<Product>products=ProductInfo.getProduct();
        for (Product product1:products){
            int ind=  products.indexOf(product1);
            if(product1.getProduct_id() == product.getProduct_id()){
                products.remove(ind);
                products.add(ind,product);
                logger.info("Product updated successfully :)");
                break;
            }
        }

        Database.updateProducts(products);
    }



    public void intallationMenu(){
        boolean cond=true;
        while (cond){
            logger.info("If you want to see all installation dates enter number 1");
            logger.info("If you want to add new installation dates enter number 2");
            logger.info("If you want to delete installation dates enter number 3");
            logger.info("If you want to back enter number 4");
            Scanner inputt = new Scanner(System.in);
            int k;
            try{
                k= inputt.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }if (k == 4) {
                cond=false;
            }
            else{
                installationOptions(k);
            }
        }
    }

    public void installationOptions(int option){
        if(option==1){
            printInstallations();
        } else if (option==2) {
            addInstall.recordInstall();

        } else if (option==3) {
            deleteInstallations();
        }
    }

    public void printInstallations() {
        List<addInstallDate>addInstallDates=Database.gitDate();
        logger.info("******************* Installation dates ********************");
        logger.info("ID            Date            Time");
        for (addInstallDate installDate:addInstallDates){
            logger.info(installDate.getId()+space+ installDate.getDate()+"\t\t"+installDate.getTime());
        }
    }
    public void deleteInstallations() {
        logger.info("Enter the id of the Installations date you want to delete:  ");
        Scanner in=new Scanner(System.in);
        addInstallDate addInstallDate=new addInstallDate();
        boolean flag = false;
        try {
            int id =in.nextInt();
            addInstallDate.setId(id);
            flag= addInstallDate.isExitdate();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag) {
            deletedate(addInstallDate);
            logger.info("The category removed successfully :)");
        }
        else{
            logger.info("This category doesn't exist !!!");
        }
    }

    public void deletedate(addInstallDate addInstallDate){
        List<addInstallDate>addInstallDates=Database.gitDate();
        for(addInstallDate install:addInstallDates){
            if(install.getId()==addInstallDate.getId()){
                addInstallDates.remove(install);
                break;
            }
        }
        Database.updateInstallation(addInstallDates);

    }


    public static boolean isExitDate(int id){
        boolean flag=false;
        for(addInstallDate addInstallDate:Database.gitDate()){
            if(addInstallDate.getId()==id){
                flag=true;
                break;
            }
        }
        return flag;
    }


    public String Spaces(String att){
        return " ".repeat(Math.max(0, 35 - att.length()));
    }




    }




