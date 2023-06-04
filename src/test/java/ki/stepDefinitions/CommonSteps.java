package ki.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ki.endUserSteps.Common_UserActions;
import net.thucydides.core.annotations.Steps;

public class CommonSteps {
    @Steps
    Common_UserActions endUserActions;

    @Given("launch the base url")
    public void launchTheBaseUrl() {
        endUserActions.launchBaseURL();
    }

    @When("click on {string} button")
    public void clickOnButton(String button) {
        endUserActions.clickButton(button);
    }

    @And("click on {string} radio button")
    public void clickOnRadioButton(String radioButton) {
        endUserActions.clickRadioButton(radioButton);
    }

    @Then("the clear form dialog box should appear")
    public void theClearFormDialogBoxShouldAppear() {
        endUserActions.clearFormDialogBoxChecks();
    }

    @Then("the filled in details should be cleared")
    public void theFilledInDetailsShouldBeCleared() {
        endUserActions.clearedForm();
    }

    @And("the error message {string} should be displayed against the field")
    public void theErrorMessageShouldBeDisplayedAgainstTheField(String error_message) {
        endUserActions.checkFieldErrorMessage(error_message);
    }
}
