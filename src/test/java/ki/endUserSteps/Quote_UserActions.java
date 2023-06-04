package ki.endUserSteps;

import io.cucumber.datatable.DataTable;
import ki.pageObjects.QuotePage;
import net.thucydides.core.annotations.Step;

public class Quote_UserActions {
    QuotePage quotePage;

    @Step
    public void fillInForm(DataTable dataTable) {
        quotePage.quoteForm(dataTable);
    }

    @Step
    public void setDate(int months) {
        quotePage.setDate(months);
    }

    @Step
    public void aumValue(String aum) {
        quotePage.enterAUMvalue(aum);
    }

    @Step
    public void premiumValue(String premium) {
        quotePage.enterPremiumvalue(premium);
    }

    @Step
    public void checkSuccessMessage() {
        quotePage.checkSuccessMessage();
    }

    @Step
    public void checkInsureds(String insureds) {
        quotePage.checkInsureds(insureds);
    }

    @Step
    public void checkClasses_Premiums(String classes_premiums) {
        quotePage.checkClasses_Premiums(classes_premiums);
    }


}
