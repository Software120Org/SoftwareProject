package Tests;

import Classes.AdminDashboard;
import Entities.Categories;
import Entities.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static org.junit.Assert.*;


public class AddCategory {
    Categories categories;
    AdminDashboard admin;
    String name;

    @Given("that the category isn't added yet")
    public void that_the_category_isn_t_added_yet() {
    categories=new Categories();
    admin=new AdminDashboard();

    }

    @When("the admin enter the category name = {string}")
    public void the_admin_enter_the_category_name(String name) {
    categories.setName(name);
    this.name=name;

    }
    @Then("the system generate a unique ID for the Category")
    public void the_system_generate_a_unique_id_for_the_worker() {
        int id= Database.getCategoryId();
        categories.setId(id);
    }
    @Then("the category will be added")
    public void the_category_will_be_added() {
        assertNotNull(categories);
        categories=new Categories(name);
        assertEquals(categories.getName(),name);
        admin.addCategory(categories);
        System.out.println("Category added successfully :)");



    }



}
