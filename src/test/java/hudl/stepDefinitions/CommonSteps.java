package hudl.stepDefinitions;

import hudl.endUserSteps.Common_UserActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CommonSteps {

    @Steps
    Common_UserActions endUserActions;

    @Given("launch the url")
    public void launchTheUrl() {
        endUserActions.launchURL();
    }

    @Given("launch the base url")
    public void launchTheBaseUrl() {
        endUserActions.launchBaseURL();
    }

    @And("click on {string} link")
    public void clickOnLink(String link) {
        endUserActions.clickLink(link);
    }

    @When("click on {string} button")
    public void clickOnButton(String button) {
        endUserActions.clickButton(button);
    }


}
