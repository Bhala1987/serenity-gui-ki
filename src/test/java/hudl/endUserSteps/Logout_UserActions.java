package hudl.endUserSteps;

import hudl.pageObjects.LogoutPage;
import net.thucydides.core.annotations.Step;

public class Logout_UserActions {

    LogoutPage logoutPage;

    @Step
    public void logout() {
        logoutPage.logout();
    }


}
