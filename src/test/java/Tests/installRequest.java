package Tests;

import org.classes.CustomerDashboard;
import org.classes.InstallRequests;
import org.entities.Database;
import org.entities.Installation;
import org.entities.addInstallDate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class installRequest {
    Installation installation;
    CustomerDashboard customer;
    addInstallDate add;
    InstallRequests installRequests ;

    @Given("that the user chooses to request an installation")
    public void that_the_user_chooses_to_request_an_installation() {
        installation =new Installation();
        customer=new CustomerDashboard();
    }
    @Given("enters Product = {string}")
    public void enters_product(String Product) {
        installation.setProduct(Product);
    }
    @Given("enters car make={string}")
    public void enters_car_make(String make) {
        installation.setMake(make);
    }
    @Given("enters car model={string}")
    public void enters_car_model(String model) {
        installation.setModel(model);
    }
    @Given("user email={string}")
    public void user_email(String email) {
        installation.setEmail(email);
    }
    @Given("Enter the ID for the available installation appointment")
    public void enter_the_id_for_the_available_installation_appointment() {
        add=new addInstallDate();
        installRequests=new InstallRequests();
        add.setId(3);
        assertTrue(add.isExitDate());
        String[] dateAndTime=installRequests.installBYid(add);
        installation.setDate(dateAndTime[0]);
        installation.setTime(dateAndTime[1]);
    }
    @Then("a unique ID should be generated for the Installation request")
    public void a_unique_id_should_be_generated_for_the_installation_request() {
        installation.setId(Database.getReqId());
    }
    @Then("the Installation request should be recorded successfully")
    public void the_installation_request_should_be_recorded_successfully() {
        installRequests.addNewInstall(installation);
    }
    @Then("confirmation email should be sent to the customer")
    public void confirmation_email_should_be_sent_to_the_customer() {
        installation.sendEmail("Installation Requests","Hello, you have successfully submitted an installation request","We send a confirmation message to customer on email");

    }
    @Given("Enter the ID for the unavailable installation appointment")
    public void enter_the_id_for_the_unavailable_installation_appointment() {
        add=new addInstallDate();
        installRequests=new InstallRequests();
        add.setId(10);

    }
    @Then("a message should be displayed indicating that this id is not available")
    public void a_message_should_be_displayed_indicating_that_this_id_is_not_available() {
        add.setId(10);
        assertTrue(add.isExitDate());
        System.out.println("this id is not available");
    }




}
