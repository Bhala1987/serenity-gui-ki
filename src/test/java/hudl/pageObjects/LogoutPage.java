package hudl.pageObjects;

import net.serenitybdd.core.pages.PageObject;
import org.mortbay.log.Log;
import org.openqa.selenium.By;

import static hudl.pageObjects.CommonHudlPage.link_login;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogoutPage extends PageObject {

    public void logout() {
        waitFor(link_login);
        assertThat("Not logged out", isElementVisible(By.xpath("//a[contains(text(),'Log in')]")));
        Log.info("***Logged off successfully.***");
    }
}
