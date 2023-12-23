package Tests;

import org.classes.AdminDashboard;
import org.entities.Database;
import org.entities.AddInstallDate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.classes.registerCustomer.logger;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteInstal {
    AddInstallDate installDate;
    AdminDashboard admin;
    @Given("that the admin choose to delete installation date")
    public void that_the_admin_choose_to_delete_installation_date() {
        admin=new AdminDashboard();
        installDate=new AddInstallDate();


    }
    @When("the entered installation date id is exist")
    public void the_entered_installation_date_id_is_exist() {
        installDate.setId(5);

    }
    @Then("the installation date will deleted")
    public void the_installation_date_will_deleted() {
        assertTrue(admin.isExitDate(6));
        admin.deleteDate(installDate);
        logger.info("Installation date deleted successfully :)");
    }
    @When("the entered installation date id is not exist")
    public void the_entered_installation_date_id_is_not_exist() {
        installDate=new AddInstallDate();
        installDate= Database.getDateById(100);

    }
    @Then("the msg that the installation date not exist will be shown")
    public void the_msg_that_the_installation_date_not_exist_will_be_shown() {
        assertFalse(AdminDashboard.isExitDate(100));
        System.out.println("the installation date not exist :( ");
        admin.deleteDate(installDate);

    }
}
