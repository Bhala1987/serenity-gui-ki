package ki.endUserSteps;

import ki.pageObjects.CommonPage;
import net.thucydides.core.annotations.Step;

public class Common_UserActions {

    CommonPage commonPage;

    @Step
    public void launchBaseURL() {
        commonPage.launchBaseURL();
        commonPage.checkTitle();
    }

    @Step
    public void clickButton(String button) {
        commonPage.clickButton(button);
    }

    @Step
    public void clickRadioButton(String radioButton) {
        commonPage.clickRadioButton(radioButton);
    }

    @Step
    public void clearFormDialogBoxChecks() {
        commonPage.clearFormDialogBoxChecks();
    }

    @Step
    public void clearedForm() {
        commonPage.clearedForm();
    }

    @Step
    public void checkFieldErrorMessage(String error_message) {
        commonPage.checkFieldErrorMessage(error_message);
    }
}
