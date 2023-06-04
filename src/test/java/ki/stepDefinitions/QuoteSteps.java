package ki.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import ki.endUserSteps.Quote_UserActions;
import net.thucydides.core.annotations.Steps;

public class QuoteSteps {

    @Steps
    Quote_UserActions endUserActions;

    @And("enter the following details")
    public void enterTheFollowingDetails(DataTable dataTable) {
        endUserActions.fillInForm(dataTable);
    }

    @And("the inception date is {int} month from now")
    public void theInceptionDateIsMonthFromNow(int months) {
        endUserActions.setDate(months);
    }

    @And("enter the AUM value as {string}")
    public void enterTheAUMValueAs(String aum) {
        endUserActions.aumValue(aum);
    }

    @And("enter the Premium value as {string}")
    public void enterThePremiumValueAs(String premium) {
        endUserActions.premiumValue(premium);
    }

    @Then("verify the successful creation of quote")
    public void verifyTheSuccessfulCreationOfQuote() {
        endUserActions.checkSuccessMessage();
    }

    @Then("I should see the insureds {string} in the pending quotes")
    public void iShouldSeeTheInsuredsInThePendingQuotes(String insureds) {
        endUserActions.checkInsureds(insureds);
    }

    @And("I should see the classes & premiums {string} in the pending quotes")
    public void iShouldSeeTheClassesPremiumsInThePendingQuotes(String classes_premiums) {
        endUserActions.checkClasses_Premiums(classes_premiums);
    }
}
