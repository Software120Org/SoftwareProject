package Tests;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.classes.RegisCust;
import org.entities.Database;
import org.entities.Regis;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisTest {
    boolean taken=false;
    Regis customer;
    RegisCust regisCust;
    List<Regis> customers;


    @Given("that I enter customer name={string}")
    public void that_i_enter_customer_name(String name) {
         customer=new Regis();
         regisCust=new RegisCust();
        customer.setname(name);
        customers=new ArrayList<>();
        customers.add(new Regis("wafaa","wafaa@gmail.com","0593650775","Nablus","0000"));
        customers.add(new Regis("Ali ","ali@gmail.com","0592110142","Tulkarem","0123456"));



    }
    @Given("customer email={string}")
    public void customer_email(String email) {
     customer.setEmail(email);


    }
    String email;
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
        for (Regis cust : customers) {
            if (cust.getEmail().equals(customer.getEmail())) {
                taken = true;  // Set taken to true when an email is already taken.
                break;
            }
        }
    }
    @Then("a unique customer ID will be generated for the customer# And the customer will recorded successfully")
    public void a_unique_customer_id_will_be_generated_for_the_customer_and_the_customer_will_recorded_successfully() {
        assertTrue(customer.isValidEmail());
        assertFalse(taken);
        customer.setId(Database.get_Id());
    }




    @Then("the customer will recorded successfully")
    public void the_customer_will_recorded_successfully() {
        assertTrue(customer.isValidEmail());
        assertFalse(customer.isUnUniqueEmail());
        assertFalse(taken);
        regisCust.addNewCustomer(customer);
        customer.hashCode();
     //   test();
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
        regisCust.takenMsg();

    }

  /*  @Then("I show the customer details who has this email")
    public void i_show_the_customer_details_who_has_this_email() {
        assertTrue(customer.isValidEmail());
        assertTrue(customer.isUnUniqueEmail());
        System.out.println(customer.getExistCustomer());


    }*/
    @Then("I have option to reenter new email")
    public void i_have_option_to_reenter_new_email() {
        assertTrue(customer.isValidEmail());
        assertTrue(taken);
        customer.setEmail("hibah@gmail.com");
        assertFalse(customers.contains(customer));
        customers.add(customer);
    }
}
