package Classes;

import Entities.*;
import Entities.Categories;

import java.util.*;
import java.util.logging.Logger;

public class AdminDashboard {
    Admin admin = new Admin();

    Logger logger = Logger.getLogger(AdminDashboard.class.getName());

    String msg="Enter valid number: ";
    String msg2="Invalid Input, try again...";
    String status ="waiting...";
    private boolean logged;
    public void setEmail(String email) {
        this.admin.setEmail(email);
    }
    public void adminDashboard() {
        logger.info("To see all customers details enter number 1 ");
        logger.info("To see all  products categories enter number 2 ");
        logger.info("To see all products listings enter number 3 ");
        logger.info("To see all orders details enter number 4 ");
        logger.info("To add new order enter number 5");
        logger.info("To update the cost of category enter 6");
        logger.info("To logout enter number 7");
    }

    public void adminPage() {
        Scanner in = new Scanner(System.in);
        boolean cond = true;
        while (cond) {
            try {
                adminDashboard();
                int option = in.nextInt();
                if (option == 7) {
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

        }
        else if ( select ==2) {

            categoriesMenu();
        }
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
            if(categories1.getId()==category.getId()){
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
                if (categories1.getId()==category.getId()) {
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
            logger.info(categories1.getId()+"\t\t\t\t"+ categories1.getName());
        }
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



    }




