package Tests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.classes.registerCustomer;
import org.entities.Database;
import org.entities.Register;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class RegisTest {
    boolean taken=false;
    Register customer;
    registerCustomer regisCustomer;
    List<Register> customers;

    @Given("that I enter customer name={string}")
    public void that_i_enter_customer_name(String name) {
         customer=new Register();
         regisCustomer =new registerCustomer();
        customer.setName(name);
        customers=new ArrayList<>();
        customers.add(new Register("wafaa","wafaa@gmail.com","0593650775","Nautilus","0000"));
        customers.add(new Register("Ali ","ali@gmail.com","0592110142","Tularemia","0123456"));
    }
    @Given("customer email={string}")
    public void customer_email(String email) {
     customer.setEmail(email);
    }

    @Given("customer phone={string}")
    public void customer_phone(String phone) {
        customer.setPhone(phone);
    }
    @Given("customer address={string}")
    public void customer_address(String address) {
        customer.setAddress(address);
    }
    @Given("customer password={string}")
    public void customer_password(String pass) {
        customer.setPassword(pass);
    }
    @When("I chose to record new customer")
    public void i_chose_to_record_new_customer() {
        for (Register customer : customers) {
            if (customer.getEmail().equals(this.customer.getEmail())) {
                taken = true;
                break;
            }
        }
    }
    @Then("a unique customer ID will be generated for the customer# And the customer will recorded successfully")
    public void a_unique_customer_id_will_be_generated_for_the_customer_and_the_customer_will_recorded_successfully() {
        assertTrue(customer.isValidEmail());
        assertFalse(taken);
        customer.setId(Database.getId());
    }
    @Then("the customer will recorded successfully")
    public void the_customer_will_recorded_successfully() {
        assertTrue(customer.isValidEmail());
        assertFalse(customer.isUnUniqueEmail());
        assertFalse(taken);
        regisCustomer.addNewCustomer(customer);
        System.out.println("Customer recorded successfully");
    }
    @Then("confirmation email will be sent to customer")
    public void confirmation_email_will_be_sent_to_customer() {
        assertTrue(customer.isValidEmail());
        customer.sendEmail("Signup","Hello You are Signed-Up to Car  Company Welcome to our application","We send a confirmation message to customer on email");

    }
    @Then("I show a message that the email is already taken")
    public void i_show_a_message_that_the_email_is_already_taken() {
        assertTrue(customer.isValidEmail());
        assertTrue(taken);
        regisCustomer.takenMsg();
    }

    @Then("I have option to reenter new email")
    public void i_have_option_to_reenter_new_email() {
        assertTrue(customer.isValidEmail());
        assertTrue(taken);
        customer.setEmail("hibah@gmail.com");
        assertFalse(customers.contains(customer));
        customers.add(customer);
    }
}