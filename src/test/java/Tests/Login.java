package Tests;

import org.entities.Admin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.classes.AdminDashboard;
import org.classes.Login2;


import static org.junit.Assert.*;

public class Login {
    boolean logged;
   Login2 login;

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        login = new Login2();
        logged = false;

    }

    String email;

    @When("the user  enter email {string}")
    public void the_user_enter_email(String email) {
        this.email = email;
        login.setEmail(email);
    }

    @When("the enter password {string}")
    public void the_enter_password(String password) {
        login.setPassword(password);
    }

    @When("the rul is {string}")
    public void the_rul_is(String rul) {
        login.setRul(rul);
    }

    @Then("the message will be display {string}")
    public void the_message_will_be_display(String msg) {
        assertEquals(login.msg(), msg);
        System.out.println(msg);
    }

    @Then("the user move to the {string}")
    public void the_user_move_to_the(String page) {

        if (page.equals("adminPage")) {
            assertTrue(login.isCorrectInfo());
            Admin admin = new Admin();
            AdminDashboard adminLogin = new AdminDashboard();
            admin.setEmail(login.getEmail());
            assertEquals(admin.getEmail(), email);
            adminLogin.setAdmin(admin);
            login.adminLogin();
            //    test();
        } else if (page.equals("customerPage")) {
            login.customerLogin();
            login.setRul("customer");
            String rul = login.getRul();
            //  test2();
        } else {
            assertFalse(login.isCorrectInfo());
            login.login();
            login.loginPage();

        }

    }
}
