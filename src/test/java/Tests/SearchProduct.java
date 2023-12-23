package Tests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.classes.AdminDashboard;
import org.entities.Product;
import org.entities.ProductInfo;
import java.util.List;
public class SearchProduct {
    AdminDashboard admin;
    List<Product> products;

    @Given("admin choose search product")
    public void admin_choose_search_product() {
        admin = new AdminDashboard();
    }
    @When("admin enter the product name = {string}")
    public void admin_enter_the_product_name(String name) {
        products = ProductInfo.searchProduct(name);
    }
    @Then("the product will shown")
    public void the_product_will_shown() {
        for (Product product : products) {
            System.out.printf("%s\\t\\t%s\t\t%s\t\t%s\t\t%s%n",
                    product.getProductName(),
                    product.getDescription(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getAvailability());
        }

    }
}
