package hudl.stepDefinitions;

import hudl.endUserSteps.Login_UserActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class LoginSteps {

    @Steps
    Login_UserActions endUserActions;

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

    @And("enter the email")
    public void enterTheEmail() {
        endUserActions.enterEmail();
    }

    @And("enter the password matching the email")
    public void enterThePasswordMatchingTheEmail() {
        endUserActions.enterPassword();
    }

    @And("enter the email {string}")
    public void enterTheEmail(String email) {
        endUserActions.enterEmail(email);
    }

    @And("enter the password {string}")
    public void enterThePassword(String password) {
        endUserActions.enterPassword(password);
    }

    @Then("should be logged in successfully")
    public void shouldBeLoggedInSuccessfully() {
        endUserActions.checkLoggedIn();
    }

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String loginError) {
        endUserActions.checkLoginError(loginError);
    }

}
