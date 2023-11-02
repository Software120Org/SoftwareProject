package Tests;

import Classes.AdminDashboard;
import Entities.Categories;
import Entities.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import static Classes.RegisCust.logger;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


public class DeleteCategory {
    AdminDashboard admin;
    Categories category;

    @Given("that the admin choose to delete category")
    public void that_the_admin_choose_to_delete_category() {
      admin=new AdminDashboard();
      category=new Categories();

    }

    @When("the entered category id is exist")
    public void the_entered_category_id_is_exist() {
    category.setId(2);
    }
    @Then("the category will deleted")
    public void the_category_will_deleted() {
        assertTrue(admin.isExistCategory(1));
        admin.deleteCategory(category);
        logger.info("Category deleted successfully :)");
    }

    @When("the entered category id is not exist")
    public void the_entered_category_id_is_not_exist() {
        category=new Categories();
        category=Database.getCategoriesById(200);
    }
    @Then("the msg that the category not exist will be shown")
    public void the_msg_that_the_category_not_exist_will_be_shown() {
        assertFalse(admin.isExistCategory(200));
        logger.info("Category doesn't removed :( ");
        admin.deleteCategory(category);
    }


}
