package hudl.stepDefinitions;

import hudl.endUserSteps.Logout_UserActions;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class LogoutSteps {

    @Steps
    Logout_UserActions endUserActions;

    @Then("should be logged out successfully")
    public void shouldBeLoggedOutSuccessfully() {
        endUserActions.logout();
    }
}
