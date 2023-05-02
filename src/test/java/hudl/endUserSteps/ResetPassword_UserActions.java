package hudl.endUserSteps;

import hudl.pageObjects.ResetPasswordPage;
import net.thucydides.core.annotations.Step;

public class ResetPassword_UserActions {

    ResetPasswordPage resetPasswordPage;

    @Step
    public void enterEmailInResetPassword(String email) {
        resetPasswordPage.enterEmailInResetPassword(email);
    }

    @Step
    public void errorCheckOnResetPassword(String error) {
        resetPasswordPage.errorCheckOnResetPassword(error);
    }

    @Step
    public void checkYourEmailMessage() {
        resetPasswordPage.checkYourEmailMessage();
    }
}
