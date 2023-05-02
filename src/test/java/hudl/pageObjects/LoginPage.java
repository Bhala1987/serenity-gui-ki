package hudl.pageObjects;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.mortbay.log.Log;
import org.openqa.selenium.Cookie;
import utilities.ConfigFileReader;
import utilities.DecryptHelper;
import utilities.Hooks;

import java.util.Objects;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage extends PageObject {

    @FindBy(id = "email")
    public static WebElementFacade input_email;

    @FindBy(id = "password")
    public static WebElementFacade input_password;

    @FindBy(xpath = "//span[text()='Home']")
    public static WebElementFacade homePage;

    @FindBy(css = "[data-qa-id='error-display']")
    public static WebElementFacade error_login;
    ConfigFileReader configFileReader = new ConfigFileReader();
    String email;
    String url = configFileReader.getProps().getProperty("url");

    public void enterEmail() {
        waitForTitleToAppear("Log In");
        this.email = DecryptHelper.decryptUsingBase64(configFileReader.getProps().getProperty("email"));
        waitFor(input_email).clear();
        input_email.sendKeys(email);
        Log.info("Entered the email address : " + email + " onto the Email input field.");
    }

    public void enterEmail(String email) {
        this.email = email;
        input_email.clear();
        input_email.sendKeys(email);
        Log.info("Entered the required email address onto the Email input field.");
    }

    public void enterPassword() {
        waitFor(input_password).clear();
        String password = null;
        password = DecryptHelper.decryptUsingBase58(configFileReader.getProps().get("password").toString().split("#######")[0].split("email=")[1]).contentEquals(email) ? password = configFileReader.getProps().get("password").toString().split("#######")[1] : password;
        password = DecryptHelper.decryptUsingBase64(password);
        input_password.sendKeys(password);
        Log.info("Entered the required password onto the Password input field.");
        assertThat("Password field is not masked, inform the FE DEV team ASAP!!!", input_password.getAttribute("type").contentEquals("password"));
        Log.info("***Password field is masked & it's secured.***");
    }

    public void enterPassword(String password) {
        input_password.clear();
        input_password.sendKeys(password);
        Log.info("Entered the required password onto the Password input field.");
    }

    public void storeCookies() {
        Serenity.setSessionVariable("cookies").to(Hooks.getDriver().manage().getCookies());
    }

    public void getCookies() {
        Hooks.getDriver().close();
        Hooks.getDriver().manage().deleteAllCookies();
        Hooks.getDriver().get(url);
        // retrieve the cookies from the session variable and set them as cookies in the web driver
        Set<Cookie> cookies = Serenity.sessionVariableCalled("cookies");
        for (Cookie cookie : cookies) {
            Hooks.getDriver().manage().addCookie(cookie);
        }
        Hooks.getDriver().get(url + "home");
        Hooks.getDriver().navigate().refresh();
    }

    public void clearCache() {
        Hooks.getDriver().manage().deleteAllCookies();
    }

    public void assertHomePage() {
        waitForTitleToAppear("Home - Hudl");
        if (Objects.isNull(System.getProperty("mobile.emulator")))
            assertThat("Not logged in as the Home link is not present, please check it out!!!", homePage.isPresent() && homePage.isClickable() && homePage.isCurrentlyVisible());
        assertThat("Not logged in as the expected url is https://www.hudl.com/home but it is " + Hooks.getDriver().getCurrentUrl(), Hooks.getDriver().getCurrentUrl().contentEquals("https://www.hudl.com/home"));
        Log.info("***Logged in successfully.***");
    }

    public void assertLoginError(String error) {
        assertThat("Expected login error message is : " + error + " but it is : " + error_login.getText(), error_login.getText().contentEquals(error));
        Log.info("***Login error message is as expected : " + error + ".***");
    }
}
