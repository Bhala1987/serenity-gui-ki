package hudl.endUserSteps;

import hudl.pageObjects.HudlPage;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Step;

public class EndUserActions {

    HudlPage hudlPage;

    @Step
    public void launchURL(String url) {
        hudlPage.openPage(url);
    }

    @Step
    public void enterEmail() {
        hudlPage.enterEmail();
    }


    @Step
    public void enterEmail(String email) {
        hudlPage.enterEmail(email);
    }

    @Step
    public void enterPassword() {
        hudlPage.enterPassword();
    }

    @Step
    public void enterPassword(String password) {
        hudlPage.enterPassword(password);
    }

    @Step
    public void clickButton(String button) {
        if (button.contentEquals("Log In")) hudlPage.clickLogin();
        else hudlPage.clickButton(button);
    }

    @Step
    public void clickLink(String link) {
        if (link.contentEquals("Log in")) hudlPage.clickLoginLink();
        else if (link.contentEquals("Hudl Log in")) hudlPage.clickHudlLoginLink();
        else hudlPage.clickLink(link);
    }

    @Step
    public void checkLoggedIn() {
        hudlPage.assertHomePage();
    }

    @Step
    public void storeCookiesInSession() {
        hudlPage.storeCookies();
    }

    @Step
    public void getSessionByCookies() {
        hudlPage.getCookies();
    }

    @Step
    public void checkLoginError(String errorMessage) {
        hudlPage.assertLoginError(errorMessage);
    }

    @Step
    public void clearCache() {
        hudlPage.clearCache();
    }

    @Step
    public void signup(DataTable dataTable) {
        hudlPage.signup(dataTable);
    }

    @Step
    public void checkErrorOn(String error, String field) {
        hudlPage.checkErrorOn(error, field);
    }

    @Step
    public void logout() {
        hudlPage.logout();
    }

    @Step
    public void enterEmailInResetPassword(String email) {
        hudlPage.enterEmailInResetPassword(email);
    }

    @Step
    public void errorCheckOnResetPassword(String error) {
        hudlPage.errorCheckOnResetPassword(error);
    }

    @Step
    public void checkYourEmailMessage() {
        hudlPage.checkYourEmailMessage();
    }
}
