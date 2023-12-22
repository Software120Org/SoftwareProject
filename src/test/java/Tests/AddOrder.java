package Tests;
import org.classes.CustomerDashboard;
import org.classes.Order;
import org.entities.Database;
import org.entities.Product;
import org.entities.ProductInfo;
import org.entities.Regis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AddOrder {

   Order order= new Order();;
   Regis customer;

   boolean exist;
    List<Product> products;
    String email;
    List<Regis> customers = new ArrayList<>();

    CustomerDashboard customerMenu;

    @Before
    public void setUp(){
        customers=new ArrayList<>();
        customers.add(new Regis("Khalid","khalid@gmail.com","059823135","Gaza","kha@ga123"));
        customers.add(new Regis("Alaa Hasan","alaa@gmail.com","056932146","Tulkarem","alaa20"));
    }


    @Given("a customer with email {string} and id={int}")
    public void a_customer_with_name_and_id(String email, Integer id) {
        customer=Database.getCustomerByEmail(email);
        this.email=email;
        customer= Database.getCustomerById(id);
        order.setProductID(id);
    }
    @Given("enter the product id1 ={int}")
    public void enter_the_product_id1(Integer id) {
        products=new ArrayList<>();
        Product product1= ProductInfo.getProductByProductId(id);
        products.add(product1);
        order.setProductID(id);
    }

    @Given("enter the product id2 ={int}")
    public void enter_the_product_id2(Integer id) {
        Product product2= ProductInfo.getProductByProductId(id);
        products.add(product2);

    }
    @When("the customer orders the two products")
    public void the_customer_orders_the_two_products() {

        order.setProducts(products);

    }
    @Then("the order should have a total price of {double}")
    public void the_order_should_have_a_total_price_of(Double totalPrice) {
        order.setTotalPrice(totalPrice);

        assertEquals(totalPrice,order.getTotalPrice());
        assertNotEquals(0,customer.getId());
        exist=true;
        order.setCustomer(customer);
    }
    @Then("a unique order Id will generated to the order")
    public void a_unique_order_id_will_generated_to_the_order() {
        assertTrue(exist);
        assertEquals(2,order.getProducts().size());
        order.setId(Database.getOrderId());
        order.setDate(LocalDate.now());
        customerMenu = new CustomerDashboard();
    }
    @Then("the order added successfully")
    public void the_order_added_successfully() {
        assertTrue(exist);
        assertEquals(2,order.getProducts().size());
        customerMenu.addOrder(order);
        System.out.println("The Order added successfully");
        System.out.println(order.getString());
    }





    @Then("I should add the customer details")
    public void i_should_add_the_customer_details() {
        customer=Database.getCustomerByEmail(email);
        assertFalse(Database.getCustomer().contains(customer));
        customer=new Regis("Lara","lara@gmail.com","059872345","Jenin","1234@lara");
        customers.add(customer);
    }


}
