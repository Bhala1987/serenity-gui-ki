package hudl.stepDefinitions;

import hudl.endUserSteps.ResetPassword_UserActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ResetPasswordSteps {

    @Steps
    ResetPassword_UserActions endUserActions;

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
