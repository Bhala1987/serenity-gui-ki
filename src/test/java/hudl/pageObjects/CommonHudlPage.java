package hudl.pageObjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import utilities.ConfigFileReader;
import utilities.Hooks;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

public class CommonHudlPage extends PageObject {

    @FindBy(id = "logIn")
    public static WebElementFacade button_login;
    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    public static WebElementFacade link_login;
    @FindBy(xpath = "//a[@data-qa-id='login-hudl']/span[text()='Hudl']")
    public static WebElementFacade link_login_hudl;
    @FindBy(linkText = "Sign up")
    public static WebElementFacade link_signup;
    @FindBy(linkText = "High Schools, Clubs & Colleges")
    public static WebElementFacade link_signup_educationalInstitutions;
    @FindBy(id = "onetrust-accept-btn-handler")
    public static WebElementFacade button_acceptAllCookies;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElementFacade button_send_or_submit;
    @FindBy(className = "hui-globaluseritem__display-name")
    public static WebElementFacade link_profile_name;
    @FindBy(className = "hui-secondarynav__open-menu")
    public static WebElementFacade mobile_emulator_profile_ellipsis;
    @FindBy(linkText = "Log Out")
    public static WebElementFacade mobile_emulator_logout_link;
    @FindBy(xpath = "//a[@data-qa-id='webnav-usermenu-logout']")
    public static WebElementFacade link_logout;
    @FindBy(linkText = "Need help?")
    public static WebElementFacade link_need_help_reset_password;
    String expectedTitle_Signup = "Sign up for Hudl";
    String expectedTitle_Signup_reason = "Sign up page has not been loaded.";
    String expectedTitle_Register = "Register";
    String expectedTitle_Register_reason = "Registration page has not been loaded.";
    ConfigFileReader configFileReader = new ConfigFileReader();
    String url = configFileReader.getProps().getProperty("url");
    String email;

    // Get the EnvironmentVariables object
    EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    // Get the value of webdriver.base.url from serenity.properties
    String baseUrl = environmentVariables.getProperty("webdriver.base.url");


    public void clickLogin() {
        waitFor(button_login).click();
        Log.info("Clicked on the Log In button.");
    }

    public void clickLoginLink() {
        waitFor(link_login).click();
        Log.info("Clicked on the Log in link.");
    }

    public void clickHudlLoginLink() {
        waitFor(link_login_hudl).click();
        Log.info("Clicked on the Hudl Log in link.");
    }


    public void clickLink(String linkText) {
        if (linkText.contentEquals("Sign up")) {
            waitFor(link_signup).click();
            Log.info("Clicked on the " + linkText + " link.");
            waitForTitleToAppear(expectedTitle_Signup);
            assertThat(expectedTitle_Signup_reason, Hooks.getDriver().getTitle().contentEquals(expectedTitle_Signup));
            Log.info("***Sign up page has been loaded successfully.***");
        } else if (linkText.contentEquals("High Schools, Clubs & Colleges")) {
            waitFor(link_signup_educationalInstitutions).click();
            Log.info("Clicked on the Sign up link for " + linkText + ".");
            waitForTitleToAppear(expectedTitle_Register);
            assertThat(expectedTitle_Register_reason, Hooks.getDriver().getTitle().contentEquals(expectedTitle_Register));
            Log.info("***Registration page has been loaded successfully.***");
        } else if (linkText.contentEquals("Log Out")) {
            if (Objects.nonNull(System.getProperty("mobile.emulator"))) {
                waitFor(mobile_emulator_profile_ellipsis).click();
                waitFor(mobile_emulator_logout_link).click();
            } else {
                waitFor(link_profile_name).click();
                waitFor(link_logout).click();
            }
            Log.info("Clicked on the " + linkText + " link.");
        } else if (linkText.contentEquals("Need help?")) {
            waitFor(link_need_help_reset_password).click();
            Log.info("Clicked on the " + linkText + " link.");
        } else
            throw new IllegalArgumentException("Either no such link available or need to write a condition for a link : " + linkText);
    }

    public void clickButton(String button) {
        if (button.contentEquals("Accept All Cookies")) {
            if (button_acceptAllCookies.isClickable()) waitFor(button_acceptAllCookies).click();
            if (Hooks.getDriver().findElement(By.linkText("Skip to main content")).isDisplayed()) {
                Hooks.getDriver().findElement(By.linkText("Skip to main content")).click();
                Hooks.getDriver().navigate().refresh();
            }
        } else if (button.contentEquals("Send")) waitFor(button_send_or_submit).click();
        else
            throw new IllegalArgumentException("Either no such button available or need to write a condition for a button : " + button);
        Log.info("***Clicked on " + button + " button.***");
    }


    public void launchBaseURL() {
        Hooks.getDriver().get(baseUrl);
        assertThat("Couldn't open up the page as the expected base URL is : " + url + " but the current url is : " + Hooks.getDriver().getCurrentUrl(), Hooks.getDriver().getCurrentUrl().contains(baseUrl));
        Log.info("Launched the base URL : " + baseUrl);
    }

    public void launchURL() {
        Hooks.getDriver().get(url);
        assertThat("Couldn't open up the page as the expected URL is : " + url + " but the current url is : " + Hooks.getDriver().getCurrentUrl(), Hooks.getDriver().getCurrentUrl().contains(url));
        Log.info("Launched the URL : " + url);
    }
}
