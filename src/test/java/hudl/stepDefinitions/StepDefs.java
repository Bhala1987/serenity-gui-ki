package hudl.stepDefinitions;

import hudl.endUserSteps.EndUserActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StepDefs {

    @Steps
    EndUserActions endUserActions;

    @Given("launch the url {string}")
    public void launchTheUrl(String url) {
        endUserActions.launchURL(url);
    }


    @And("enter the email")
    public void enterTheEmail() {
        endUserActions.enterEmail();
    }

    @And("enter the password matching the email")
    public void enterThePasswordMatchingTheEmail() {
        endUserActions.enterPassword();
    }

    @When("click on {string} button")
    public void clickOnButton(String button) {
        endUserActions.clickButton(button);
    }

    @Then("should be logged in successfully")
    public void shouldBeLoggedInSuccessfully() {
        endUserActions.checkLoggedIn();
    }

    @And("enter the email {string}")
    public void enterTheEmail(String email) {
        endUserActions.enterEmail(email);
    }

    @And("enter the password {string}")
    public void enterThePassword(String password) {
        endUserActions.enterPassword(password);
    }

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String loginError) {
        endUserActions.checkLoginError(loginError);
    }

    @And("click on {string} link")
    public void clickOnLink(String link) {
        endUserActions.clickLink(link);
    }

    @And("set the cookies")
    public void setTheCookies() {
        endUserActions.storeCookiesInSession();
    }

    @Given("get the cookies")
    public void getTheCookies() {
        endUserActions.getSessionByCookies();
    }

    @Given("clear the cache")
    public void clearTheCache() {
        endUserActions.clearCache();
    }

    @And("enter the details")
    public void enterTheDetails(DataTable dataTable) {
        endUserActions.signup(dataTable);
    }

    @Then("check the error message {string} on the field {string}")
    public void checkTheErrorMessageOnTheField(String error, String field) {
        endUserActions.checkErrorOn(error, field);
    }

    @Then("should be logged out successfully")
    public void shouldBeLoggedOutSuccessfully() {
        endUserActions.logout();
    }

    @When("enter the email {string} in the reset password screen")
    public void enterTheEmailInTheResetPasswordScreen(String email) {
        endUserActions.enterEmailInResetPassword(email);
    }

    @Then("the error message {string} should be displayed on the reset password screen")
    public void theErrorMessageShouldBeDisplayedOnTheResetPasswordScreen(String error) {
        endUserActions.errorCheckOnResetPassword(error);
    }

    @Then("check the check your email message")
    public void checkTheCheckYourEmailMessage() {
        endUserActions.checkYourEmailMessage();
    }
}
