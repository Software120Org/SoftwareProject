package Tests;
import org.Classes.AdminDashboard;
import org.entities.Database;
import org.entities.Regis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class DeleteCustomer {


    AdminDashboard admin;
    Regis customer;

    @Given("that the admin choose to delete customer")
    public void that_the_admin_choose_to_delete_customer() {
        admin = new AdminDashboard();
        customer = new Regis();
    }
    @When("the admin entered customer ID is exist")
    public void the_admin_entered_customer_id_is_exist() {
        customer.setId(7);
    }
    @Then("Customers will be deleted")
    public void customers_will_be_deleted() {
       assertTrue(admin.isExistCustomer(7));
       admin.deleteCustomer(customer);
    }




    @When("the admin entered customer ID doesn't exist in the recorded customers")
    public void the_admin_entered_customer_id_doesn_t_exist_in_the_recorded_customers() {
      customer = new Regis();
      customer = Database.getCustomerById(200);
    }
    @Then("the message that the customer doesn't exist will be printed")
    public void the_message_that_the_customer_doesn_t_exist_will_be_printed() {
        assertFalse(admin.isExistCustomer(200));
        admin.deleteCustomer(customer);
    }

}
