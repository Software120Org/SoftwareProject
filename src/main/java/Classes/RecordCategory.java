package Classes;

import Entities.Categories;
import Entities.Database;

import java.util.Scanner;
import java.util.logging.Logger;

public class RecordCategory {
    Logger logger = Logger.getLogger(RecordCategory.class.getName());

    public Categories newCategory(){
        Scanner in=new Scanner(System.in);
        Categories category=new Categories();
        logger.info("Enter category name: ");
        category.setName(in.nextLine());
        category.setId(Database.getCategoryId());
        addNewCategory(category);
        return category;

    }

    public void addNewCategory(Categories category) {
        Database.storeObject("Categories",category);
        logger.info("Category added successfully :)");
    }

    public void takenMsg() {
        logger.info("This category is already exist..");
    }


}
