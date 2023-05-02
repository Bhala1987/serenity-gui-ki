package hudl.endUserSteps;

import hudl.pageObjects.CommonHudlPage;
import net.thucydides.core.annotations.Step;

public class Common_UserActions {

    CommonHudlPage hudlPage;

    @Step
    public void launchBaseURL() {
        hudlPage.launchBaseURL();
    }

    @Step
    public void launchURL() {
        hudlPage.launchURL();
    }

    @Step
    public void clickLink(String link) {
        if (link.contentEquals("Log in")) hudlPage.clickLoginLink();
        else if (link.contentEquals("Hudl Log in")) hudlPage.clickHudlLoginLink();
        else hudlPage.clickLink(link);
    }

    @Step
    public void clickButton(String button) {
        if (button.contentEquals("Log In")) hudlPage.clickLogin();
        else hudlPage.clickButton(button);
    }
}
