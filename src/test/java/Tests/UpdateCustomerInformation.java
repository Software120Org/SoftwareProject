package Tests;

import Classes.CustomerDashboard;
import Classes.Login2;
import Entities.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCustomerInformation {

    private String newValue;
    CustomerDashboard customer;


    @Given("that I choose to update customer information")
    public void that_i_choose_to_update_customer_information() {
        customer = new CustomerDashboard();
        customer.setCustomer(Database.getCustomer().get(2));
    }


    @When("I select to update my address then I enter my new address = {string}")
    public void i_select_to_update_my_address_then_i_enter_my_new_address(String address) {
        newValue=address;
        customer.updateInformation("address",newValue);
    }
    @Then("my address will updated successfully")
    public void my_address_will_updated_successfully() {
        assertEquals(customer.getCustomer().getAddress(), newValue);
        customer.updatedMessage();
    }




    @When("I select to update my phone number then I enter my new phone number = {string}")
    public void i_select_to_update_my_phone_number_then_i_enter_my_new_phone_number(String phoneNumber) {
        newValue=phoneNumber;
        customer.updateInformation("phone",newValue);
    }
    @Then("my phone number will updated successfully")
    public void my_phone_number_will_updated_successfully() {
        assertEquals(customer.getCustomer().getPhone(), newValue);
        customer.updatedMessage();
    }


    @When("I select to update my password then I enter my new password = {string}")
    public void i_select_to_update_my_password_then_i_enter_my_new_password(String password) {
        newValue=password;
        customer.updateInformation("password",newValue);
    }


    @Then("my password will updated successfully")
    public void password_number_will_updated_successfully() {
        assertEquals(customer.getCustomer().getPassword(), newValue);
        customer.updatedMessage();
        Database.updateCustomers(Database.getCustomer());
        List<Login2> loginList=Database.users();
        for (Login2 login:loginList){
            if(login.getEmail().equals(customer.getCustomer().getEmail())){
                login.setPassword(newValue);
                break;
            }
        }
        Database.updateLogin(loginList);



    }



}
