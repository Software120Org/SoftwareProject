package Tests;

import Classes.AdminDashboard;
import Entities.Categories;
import Entities.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;



public class UpdateCategory {
    AdminDashboard admin;
    Categories categories;

    String value;

    @Given("that the admin choose to update category")
    public void that_the_admin_choose_to_update_category() {
        admin=new AdminDashboard();
        categories=Database.getCategoriesById(4);

    }
    @When("i select to update category name = {string}")
    public void i_select_to_update_category_name(String name) {

        admin.updateCategory(name,categories);
        value=name;
    }
    @Then("category name will updated successfully")
    public void category_name_will_updated_successfully() {
        assertEquals(categories.getName(), value);
        System.out.println("Category updated successfully :)");
    }




}
