package hudl.stepDefinitions;

import hudl.endUserSteps.Signup_UserActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class SignupSteps {

    @Steps
    Signup_UserActions endUserActions;

    @And("enter the details")
    public void enterTheDetails(DataTable dataTable) {
        endUserActions.signup(dataTable);
    }


    @Then("check the error message {string} on the field {string}")
    public void checkTheErrorMessageOnTheField(String error, String field) {
        endUserActions.checkErrorOn(error, field);
    }

    @And("enter the personal details")
    public void enterThePersonalDetails(DataTable dataTable) {
        endUserActions.personalDetails(dataTable);
    }

    @And("enter the company details")
    public void enterTheCompanyDetails(DataTable dataTable) {
        endUserActions.companyDetails(dataTable);
    }
}
