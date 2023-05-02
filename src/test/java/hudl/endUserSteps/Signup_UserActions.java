package hudl.endUserSteps;

import hudl.pageObjects.SignupPage;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Step;

public class Signup_UserActions {
    SignupPage signupPage;

    @Step
    public void signup(DataTable dataTable) {
        signupPage.signup(dataTable);
    }

    @Step
    public void personalDetails(DataTable dataTable) {
        signupPage.personalDetails(dataTable);
    }

    @Step
    public void companyDetails(DataTable dataTable) {
        signupPage.companyDetails(dataTable);
    }

    @Step
    public void checkErrorOn(String error, String field) {
        signupPage.checkErrorOn(error, field);
    }

}
