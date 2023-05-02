package hudl.endUserSteps;

import hudl.pageObjects.LoginPage;
import net.thucydides.core.annotations.Step;

public class Login_UserActions {

    LoginPage loginPage;

    @Step
    public void enterEmail() {
        loginPage.enterEmail();
    }


    @Step
    public void enterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @Step
    public void enterPassword() {
        loginPage.enterPassword();
    }

    @Step
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }


    @Step
    public void storeCookiesInSession() {
        loginPage.storeCookies();
    }

    @Step
    public void getSessionByCookies() {
        loginPage.getCookies();
    }

    @Step
    public void clearCache() {
        loginPage.clearCache();
    }

    @Step
    public void checkLoggedIn() {
        loginPage.assertHomePage();
    }

    @Step
    public void checkLoginError(String errorMessage) {
        loginPage.assertLoginError(errorMessage);
    }

}
