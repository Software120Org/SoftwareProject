package org.classes;
import org.entities.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;


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
        boolean condition1 = true;
        while (condition1) {
            try {
                adminDashboard();
                int option1 = in.nextInt();
                if (option1 == 5) {
                    logger.info("Goodbye");
                    condition1 = false;
                } else {
                    adminOptions(option1);
                }

            } catch (Exception e) {
                logger.info("Please enter a valid option number!! ");
                break;
            }
        }
    }
    public void adminOptions(int select1){
        if (select1 ==1){
            customerMenu();
        }
        else if (select1 ==2) {

            categoriesMenu();
        }
        else if (select1 ==3) {

            productsMenu();
        }
        else if (select1==4) {
            installationMenu();
        }

    }

    public void customerMenu() {
        boolean condition2=true;
        while (condition2) {
            logger.info("If you want to see all customers information enter number 1");
            logger.info("If you want to add new customer enter number 2");
            logger.info("If you want to delete customer enter number 3");
            logger.info("If you want to back enter number 4");
            Scanner in = new Scanner(System.in);
            int x1;
            try{
                x1= in.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }

            if (x1 == 4) {
                condition2=false;
            }
            else{
                customerOptions(x1);
            }
        }
    }
    public void customerOptions(int option2) {
        if(option2==1){
            List<Register> customers = Database.getCustomer();
            logger.info("******************************************************* Customers **************************************************************");
            logger.info("CustomerID         Name                             Email                          Phone number                      Address  " );
            printCustomers(customers);
        } else if (option2==2) {
            RegisterCustomer customer=new RegisterCustomer();
            customer.recordCustomer();

        } else if (option2==3) {
            deleteCustomer();
        }
    }
    private void printCustomers(List<Register> customers) {
        customerInfo(customers);
    }
    private void customerInfo(List<Register> customers) {
        for (Register customer : customers) {
            if (logger.isLoggable(Level.INFO)) {
                String logMessage = String.format(
                        "%s%s%s%s%s%s%s%s",
                        customer.getId(),
                        space,
                        customer.getName(),
                        spaces(customer.getName()),
                        customer.getEmail(),
                        spaces(customer.getEmail()),
                        customer.getPhone(),
                        spaces(customer.getPhone()) + customer.getAddress());
                logger.info(logMessage);
            }
        }
    }
    public void deleteCustomer() {
        logger.info("Enter the ID of the customer you want to delete: ");
        Scanner in=new Scanner(System.in);
        Register customer=new Register();
        boolean flag11 = false;
        try {
            int id=in.nextInt();
            customer.setId(id);
            flag11=customer.isExitCustomer();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag11) {
           deleteCustomer(customer);
            logger.info("Customer removed successfully :)");
        }
        else{
            logger.info("This customer doesn't exist in our customers! :( ");
        }
    }
    public void deleteCustomer(Register customer){
        List<Register>customers=Database.getCustomer();
        for(Register customer1:customers){
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
        boolean condition3=true;
        while (condition3) {
            logger.info("If you want to see all categories enter number 1");
            logger.info("If you want to add new category enter number 2");
            logger.info("If you want to delete category enter number 3");
            logger.info("If you want to edit category enter number 4");
            logger.info("If you want to back enter number 5");
            Scanner input2 = new Scanner(System.in);
            int k22;
            try{
                k22= input2.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }

            if (k22 == 5) {
                condition3=false;
            }
            else{
                categoriesOptions(k22);
            }
        }
    }
    public void categoriesOptions(int select3){
        if(select3==1){
            printCategories();
        }
         else if(select3==2){
           addCategory();

        } else if (select3==3) {
            deleteCategory();

        } else if (select3==4) {
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
        boolean flag44 = false;
        try {
            int id =in.nextInt();
            categories.setId(id);
            flag44=categories.isExitCategories();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag44) {
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
            if(categories1.getId().equals(category.getId())){
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
        boolean flag4 = false;
        int id=0;
        try {
            id = input.nextInt();
            category.setId(id);
            flag4 = category.isExitCategories();
        }
        catch (InputMismatchException e) {
            logger.info(msg2);
        }
        if (!flag4) {
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
                if (categories1.getId().equals(category.getId())) {
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
            for (Categories categories1 : categories) {
                logger.info(categories1.getId() + " " + categories1.getName());
            }
        }
    public boolean isExistCustomer(int id){
        int flag5=0;
        for(Register customer:Database.getCustomer()){
            if(customer.getId()==id){
                flag5=1;
                break;
            }
        }
        return flag5==1;
    }
    public boolean isExistCategory(int id){
        int flag11=0;
        for(Categories categories:Database.getCategories()){
            if(categories.getId()==id){
                flag11=1;
                break;
            }
        }
        return flag11==1;
    }
    public void productsMenu() {
        boolean condition4=true;
        while (condition4) {
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
                condition4=false;
            }
            else{
                productsOptions(k);
            }
        }
    }
    public void productsOptions(int select55){
        if(select55==1){
            List<Product> products = ProductInfo.getProduct();
            logger.info("***************************************************************** Products ****************************************************************************");
            logger.info("ProductId      ProductName                             Description                                              CategoryName      Price      Availability ");
            for (Product product : products) {
                Boolean availability = product.getAvailability();
                if (availability != null && availability) {
                    String formattedInfo = String.format(
                            "%s\t\t%s%s\t\t%s\t\t%s\t\t%s%n",
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getCategory(),
                            product.getPrice(),
                            availability
                    );
                    logger.info(formattedInfo);
                }
            }

        }

        else if(select55==2){
            addProduct();

        } else if (select55==3) {
          searchProduct();

        }
        else if (select55==4) {
            updateProduct();

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
        product.setProductId(ProductInfo.getProductId());
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
        for (Product product : products) {
            if (product.getAvailability() != null) {
                logger.info(
                        String.format(
                                "%s\\t\\t%s\t\t%s\t\t%s\t\t%s%n",
                                product.getProductName(),
                                product.getDescription(),
                                product.getCategory(),
                                product.getPrice(),
                                product.getAvailability()
                        )
                );
            }
        }


    }
    public void updateProduct() {
        logger.info("Enter the id of the product you want to update:");
        Scanner input = new Scanner(System.in);
        Product product=new Product();
        boolean flag77 = false;
        int id=0;
        try {
            id= input.nextInt();
            product.setProductId(id);
            flag77 = product.isExitProduct();
        }
        catch (InputMismatchException e) {
            logger.info(msg2);
        }

        if (!flag77) {
            logger.info("This product doesn't exist");
        }
        else {
            product=ProductInfo.getProductByProductId(id);
            product.setProductId(id);
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
            if(product1.getProductId().equals(product.getProductId())){
                products.remove(ind);
                products.add(ind,product);
                logger.info("Product updated successfully :)");
                break;
            }
        }
        Database.updateProducts(products);
    }
    public void installationMenu(){
        boolean condition5=true;
        while (condition5){
            logger.info("If you want to see all installation dates enter number 1");
            logger.info("If you want to add new installation dates enter number 2");
            logger.info("If you want to delete installation dates enter number 3");
            logger.info("If you want to back enter number 4");
            Scanner input = new Scanner(System.in);
            int k88;
            try{
                k88= input.nextInt();
            }
            catch (Exception ignored){
                logger.info(msg);
                break;
            }if (k88 == 4) {
                condition5=false;
            }
            else{
                installationOptions(k88);
            }
        }
    }
    public void installationOptions(int option87){
        if(option87==1){
            printInstallations();
        } else if (option87==2) {
            AddInstall.recordInstall();

        } else if (option87==3) {
            deleteInstallations();
        }
    }
    public void printInstallations() {
        List<AddInstallDate>addInstallDates=Database.gitDate();
        logger.info("******************* Installation dates ********************");
        logger.info("ID            Date            Time");
        for (AddInstallDate installDate : addInstallDates) {
            String formattedOutput = String.format("%s %s\t\t%s",
                    installDate.getId(),
                    installDate.getDate(),
                    installDate.getTime());
            logger.info(formattedOutput);
        }
    }
    public void deleteInstallations() {
        logger.info("Enter the id of the Installations date you want to delete:  ");
        Scanner in=new Scanner(System.in);
        AddInstallDate addInstallDate=new AddInstallDate();
        boolean flag67 = false;
        try {
            int id =in.nextInt();
            addInstallDate.setId(id);
            flag67= addInstallDate.isExitDate();

        }
        catch (InputMismatchException e){
            logger.info(msg2);
        }
        if(flag67) {
            deleteDate(addInstallDate);
            logger.info("The category removed successfully :)");
        }
        else{
            logger.info("This category doesn't exist !!!");
        }
    }
    public void deleteDate(AddInstallDate addInstallDate){
        List<AddInstallDate>addInstallDates=Database.gitDate();
        for(AddInstallDate install:addInstallDates){
            if(install.getId()==addInstallDate.getId()){
                addInstallDates.remove(install);
                break;
            }
        }
        Database.updateInstallation(addInstallDates);
    }
    public static boolean isExitDate(int id){
        boolean flag=false;
        for(AddInstallDate addInstallDate:Database.gitDate()){
            if(addInstallDate.getId()==id){
                flag=true;
                break;
            }
        }
        return flag;
    }
    public String spaces(String att){
        return " ".repeat(Math.max(0, 35 - att.length()));
    }
    }




