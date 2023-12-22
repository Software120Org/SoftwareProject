package Tests;

import org.classes.AdminDashboard;
import org.entities.Category;
import org.entities.Product;
import org.entities.ProductInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
public class AddProduct {
    Product product;
    AdminDashboard admin;
    String name;
    String description;
    Category category;
    Double price;
    Boolean availability;
    Integer id;
    @Given("that the product isn't added yet")
    public void that_the_product_isn_t_added_yet() {
       product=new Product();
       admin = new AdminDashboard();
    }
    @When("the admin enter the product name = {string}")
    public void the_admin_enter_the_product_name(String name) {
       product.setProductName(name);
       this.name=name;
    }
    @When("the admin enter the product description={string}")
    public void the_admin_enter_the_product_description(String description) {
        product.setDescription(description);
        this.description=description;
    }

    @When("the admin enter the product category={string}")
    public void the_admin_enter_the_product_category(String category) {
        product.setCategory(Category.valueOf(category));
        this.category=Category.valueOf(category);
    }
    @When("the admin enter the product price={string}")
    public void the_admin_enter_the_product_price(String price) {
      product.setPrice(Double.parseDouble(price));
      this.price=Double.parseDouble(price);
    }
    @When("the admin enter the product availability={string}")
    public void the_admin_enter_the_product_availability(String availability) {
     product.setAvailability(Boolean.valueOf(availability));
     this.availability=Boolean.valueOf(availability);
    }
    @Then("the system generate a unique ID for the product")
    public void the_system_generate_a_unique_id_for_the_product() {
      id= ProductInfo.getProductId();
      product.setProduct_id(id);
    }
    @Then("the product will be added")
    public void the_product_will_be_added() {
     assertNotNull(product);
     assertFalse(product.isExitProduct());
        product =  Product
                .builder()
                .setProduct_id(id)
                .setName(name)
                .setDescription(description)
                .setCategory(category)
                .setPrice(price)
                .setAvailability(availability)
                .build();
        assertEquals(product.getProduct_id(),id);
        assertEquals(product.getProductName(),name);
        assertEquals(product.getDescription(),description);
        assertEquals(product.getCategory(),category);
        assertEquals(product.getPrice(),price);
        assertEquals(product.getAvailability(),availability);
        admin.addProduct(product);
       System.out.println("Product added successfully :)");

    }


}
