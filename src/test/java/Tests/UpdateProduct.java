package Tests;

import org.Classes.AdminDashboard;
import org.entities.Product;
import org.entities.ProductInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class UpdateProduct {
    AdminDashboard admin;
    Product product;
    String attribute;
    String value;
    @Given("that the admin choose to update product info")
    public void that_the_admin_choose_to_update_product_info() {
        admin = new AdminDashboard();
        product= ProductInfo.getProductByProductId(11);
    }
    @When("admin select to update name")
    public void admin_select_to_update_name() {
         attribute = "Name";
    }
    @When("admin enter the new name = {string}")
    public void admin_enter_the_new_name(String name) {
       admin.updateProduct(attribute,name,product);
       value=name;
    }
    @Then("the product name will updated successfully")
    public void the_product_name_will_updated_successfully() {
       assertEquals(product.getProductName(),value);
        System.out.println("Product name updated successfully :)");
    }



    @When("admin select to update description")
    public void admin_select_to_update_description() {
      attribute="Description";
    }
    @When("admin enter the new description = {string}")
    public void admin_enter_the_new_description(String description) {
        admin.updateProduct(attribute,description,product);
        value=description;
    }
    @Then("the product description will updated successfully")
    public void the_product_description_will_updated_successfully() {
        assertEquals(product.getDescription(),value);
        System.out.println("Product description updated successfully :)");
    }




    @When("admin select to update category")
    public void admin_select_to_update_category() {
      attribute="Category";
    }
    @When("admin enter the new category = {string}")
    public void admin_enter_the_new_category(String category) {
        admin.updateProduct(attribute,category,product);
        value=category;

    }
    @Then("the product category will updated successfully")
    public void the_product_category_will_updated_successfully() {
        assertEquals(String.valueOf(product.getCategory()),value);
        System.out.println("Product category updated successfully :)");

    }



    @When("admin select to update price")
    public void admin_select_to_update_price() {
       attribute="Price";
    }
    @When("admin enter the new price = {string}")
    public void admin_enter_the_new_price(String price) {
        admin.updateProduct(attribute,price,product);
        value=price;
    }
    @Then("the product price will updated successfully")
    public void the_product_price_will_updated_successfully() {
        assertEquals(String.valueOf(product.getPrice()),value);
        System.out.println("Product price updated successfully :)");

    }



    @When("admin select to update availability")
    public void admin_select_to_update_availability() {
     attribute="availability";
    }
    @When("admin enter the new availability = {string}")
    public void admin_enter_the_new_availability(String availability) {
        admin.updateProduct(attribute,availability,product);
        value=availability;
    }
    @Then("the product availability will updated successfully")
    public void the_product_availability_will_updated_successfully() {
        assertEquals(String.valueOf(product.getAvailability()),value);
        System.out.println("Product availability updated successfully :)");
    }


}
