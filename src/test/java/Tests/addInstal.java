package Tests;

import org.classes.AdminDashboard;
import org.classes.addInstall;
import org.entities.Database;
import org.entities.addInstallDate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class addInstal {

    addInstallDate installDate;
    AdminDashboard admin;
    addInstall install;






    @Given("that the Date isn't added yet")
    public void that_the_date_isn_t_added_yet() {
        admin=new AdminDashboard();
        installDate=new addInstallDate();
        install=new addInstall();


    }
    @When("the admin enter the Date = {string}")
    public void the_admin_enter_the_date(String date) {
        installDate.setDate(date);

    }
    @When("the admin enter the Time = {string}")
    public void the_admin_enter_the_time(String time) {
        installDate.setTime(time);

    }
    @Then("the system generate a unique ID for the installation date")
    public void the_system_generate_a_unique_id_for_the_installation_date() {
    assertTrue(installDate.isValidDate());
    assertTrue(installDate.isValidTime());
    assertFalse(installDate.isUnUniqueDate(installDate.getDate(),installDate.getTime()));
    installDate.setStatus("Available");
    installDate.setId(Database.get_install_id());

    }
    @Then("the installation date will be added")
    public void the_installation_date_will_be_added() {
        install.addNewDate(installDate);

    }




}

