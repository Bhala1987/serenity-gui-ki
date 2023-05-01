package hudl.pageObjects;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import utilities.ConfigFileReader;
import utilities.helpers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class HudlPage extends PageObject {

    @FindBy(id = "email")
    public static WebElementFacade input_email;
    @FindBy(id = "password")
    public static WebElementFacade input_password;
    @FindBy(id = "logIn")
    public static WebElementFacade button_login;
    @FindBy(xpath = "//span[text()='Home']")
    public static WebElementFacade homePage;
    @FindBy(css = "[data-qa-id='error-display']")
    public static WebElementFacade error_login;
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
    @FindBy(id = "FirstName")
    public static WebElementFacade input_signup_firstname;
    @FindBy(id = "LastName")
    public static WebElementFacade input_signup_lastname;
    @FindBy(id = "Email")
    public static WebElementFacade input_signup_email;
    @FindBy(id = "Phone")
    public static WebElementFacade input_signup_phone;
    @FindBy(id = "Country")
    public static WebElementFacade input_signup_country;
    @FindBy(id = "State")
    public static WebElementFacade input_signup_state;
    @FindBy(id = "Company")
    public static WebElementFacade input_signup_org_name;
    @FindBy(name = "organizationLevel_c")
    public static WebElementFacade input_signup_org_level;
    @FindBy(name = "Org_Classification__c_account")
    public static WebElementFacade input_signup_org_type;
    @FindBy(name = "Team_Sport__c_contact")
    public static WebElementFacade input_signup_sport;
    @FindBy(name = "Role__c")
    public static WebElementFacade input_signup_role;
    @FindBy(name = "message_c")
    public static WebElementFacade input_signup_message;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElementFacade button_send_or_submit;
    @FindBy(id = "ValidMsgFirstName")
    public static WebElementFacade error_firstname;
    @FindBy(id = "ValidMsgEmail")
    public static WebElementFacade error_email;
    @FindBy(id = "ValidMsgPhone")
    public static WebElementFacade error_phone;
    @FindBy(id = "ValidMsgOrg_Classification__c_account")
    public static WebElementFacade error_org_type;
    @FindBy(className = "hui-globaluseritem__display-name")
    public static WebElementFacade link_profile_name;
    @FindBy(xpath = "//a[@data-qa-id='webnav-usermenu-logout']")
    public static WebElementFacade link_logout;

    @FindBy(linkText = "Need help?")
    public static WebElementFacade link_need_help_reset_password;

    @FindBy(xpath = "//input[@data-qa-id='password-reset-input']")
    public static WebElementFacade input_email_reset_password;

    @FindBy(xpath = "//p[@data-qa-id='password-reset-error-display']")
    public static WebElementFacade error_email_reset_password;

    @FindBy(xpath = "//h3")
    public static WebElementFacade message_reset_password_h3;

    @FindBy(xpath = "//p[1]")
    public static WebElementFacade message_reset_password_p1;

    @FindBy(xpath = "//p[2]")
    public static WebElementFacade message_reset_password_p2;

    ConfigFileReader configFileReader = new ConfigFileReader();
    String email;
    String url = configFileReader.getProps().getProperty("url");

    public void openPage(String url) {
        getDriver().get(url);
        assertThat("Couldn't open up the page as the expected URL is : " + url + " but the current url is : " + getDriver().getCurrentUrl(), getDriver().getCurrentUrl().contains(url));
        Log.info("Launched the URL : " + url);
        getDriver().manage().window().maximize();
        Log.info("Maximized the window");
    }

    public void enterEmail() {
        waitForTitleToAppear("Log In");
        this.email = helpers.decrypt_technique_1(configFileReader.getProps().getProperty("email"));
        waitFor(input_email);
        input_email.clear();
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
        waitFor(input_password);
        input_password.clear();
        String password = null;
        password = helpers.decrypt_technique_2(configFileReader.getProps().get("password").toString().split("#######")[0].split("email=")[1]).contentEquals(email) ? password = configFileReader.getProps().get("password").toString().split("#######")[1] : password;
        password = helpers.decrypt_technique_1(password);
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

    public void clickLogin() {
        waitFor(button_login);
        button_login.click();
        Log.info("Clicked on the Log In button.");
    }

    public void clickLoginLink() {
        waitFor(link_login);
        link_login.click();
        Log.info("Clicked on the Log in link.");
    }

    public void clickHudlLoginLink() {
        waitFor(link_login_hudl);
        link_login_hudl.click();
        Log.info("Clicked on the Hudl Log in link.");
    }

    public void assertHomePage() {
        waitForTitleToAppear("Home - Hudl");
        assertThat("Not logged in as the Home link is not present, please check it out!!!", homePage.isPresent() && homePage.isClickable() && homePage.isCurrentlyVisible());
        assertThat("Not logged in as the expected url is https://www.hudl.com/home but it is " + getDriver().getCurrentUrl(), getDriver().getCurrentUrl().contentEquals("https://www.hudl.com/home"));
        Log.info("***Logged in successfully.***");
    }

    public void storeCookies() {
        Serenity.setSessionVariable("cookies").to(getDriver().manage().getCookies());
    }

    public void getCookies() {
        getDriver().close();
        getDriver().manage().deleteAllCookies();
        getDriver().get(url);
        // retrieve the cookies from the session variable and set them as cookies in the web driver
        Set<Cookie> cookies = Serenity.sessionVariableCalled("cookies");
        for (Cookie cookie : cookies) {
            getDriver().manage().addCookie(cookie);
        }
        getDriver().get(url + "home");
        getDriver().navigate().refresh();
        getDriver().manage().window().maximize();
    }

    public void assertLoginError(String error) {
        assertThat("Expected login error message is : " + error + " but it is : " + error_login.getText(), error_login.getText().contentEquals(error));
        Log.info("***Login error message is as expected : " + error + ".***");
    }

    public void clickLink(String linkText) {
        if (linkText.contentEquals("Sign up")) {
            waitFor(link_signup);
            link_signup.click();
            Log.info("Clicked on the " + linkText + " link.");
            waitForTitleToAppear("Sign up for Hudl");
            assertThat("Sign up page has not been loaded.", getDriver().getTitle().contentEquals("Sign up for Hudl"));
            Log.info("***Sign up page has been loaded successfully.***");
        } else if (linkText.contentEquals("High Schools, Clubs & Colleges")) {
            waitFor(link_signup_educationalInstitutions);
            link_signup_educationalInstitutions.click();
            Log.info("Clicked on the Sign up link for " + linkText + ".");
            waitForTitleToAppear("Register");
            assertThat("Registration page has not been loaded.", getDriver().getTitle().contentEquals("Register"));
            Log.info("***Registration page has been loaded successfully.***");
        } else if (linkText.contentEquals("Log Out")) {
            waitFor(link_profile_name);
            link_profile_name.click();
            waitFor(link_logout);
            link_logout.click();
            Log.info("Clicked on the " + linkText + " link.");
        } else if (linkText.contentEquals("Need help?")) {
            waitFor(link_need_help_reset_password);
            link_need_help_reset_password.click();
            Log.info("Clicked on the " + linkText + " link.");
        } else
            throw new IllegalArgumentException("Either no such link available or need to write a condition for a link : " + linkText);
    }

    public void clickButton(String button) {
        if (button.contentEquals("Accept All Cookies")) {
            waitFor(button_acceptAllCookies);
            button_acceptAllCookies.click();
            if (getDriver().findElement(By.linkText("Skip to main content")).isDisplayed()) {
                getDriver().findElement(By.linkText("Skip to main content")).click();
                getDriver().navigate().refresh();
            }
        } else if (button.contentEquals("Send")) {
            waitFor(button_send_or_submit);
            button_send_or_submit.click();
        } else
            throw new IllegalArgumentException("Either no such button available or need to write a condition for a button : " + button);
        Log.info("***Clicked on " + button + " button.***");
    }

    public void clearCache() {
        getDriver().manage().deleteAllCookies();
    }

    public void signup(DataTable dataTable) {
        getDriver().navigate().refresh(); // REFRESHING TO SELECT THE Select... OPTION
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            //First name
            input_signup_firstname.clear();
            input_signup_firstname.sendKeys(row.get("First name"));
            //Last name
            input_signup_lastname.clear();
            input_signup_lastname.sendKeys(row.get("Last name"));
            //Email address
            input_signup_email.clear();
            input_signup_email.sendKeys(row.get("Email address"));
            //Phone number
            input_signup_phone.clear();
            input_signup_phone.sendKeys(row.get("Phone number"));
            //Country
            if (Objects.isNull(row.get("Country"))) input_signup_country.deselect();
            else input_signup_country.selectByValue(row.get("Country"));
            //State
            if (Objects.isNull(row.get("State"))) input_signup_state.deselect();
            else input_signup_state.selectByValue(row.get("State"));
            //Organization name
            input_signup_org_name.clear();
            input_signup_org_name.sendKeys(row.get("Org name"));
            //Organization Level:
            if (Objects.isNull(row.get("Org level"))) input_signup_org_level.deselect();
            else input_signup_org_level.selectByValue(row.get("Org level"));
            //Organization type
            if (Objects.isNull(row.get("Org type"))) input_signup_org_type.deselect();
            else input_signup_org_type.selectByValue(row.get("Org type"));
            //Sport
            if (Objects.isNull(row.get("Sport"))) input_signup_sport.deselect();
            else input_signup_sport.selectByValue(row.get("Sport"));
            //Role on team
            if (Objects.isNull(row.get("Role"))) input_signup_role.deselect();
            else input_signup_role.selectByValue(row.get("Role"));
            //Message
            input_signup_message.clear();
            input_signup_message.sendKeys(row.get("Message"));
        }
    }

    public void checkErrorOn(String error, String field) {
        if (field.contentEquals("First name"))
            assertThat("Expected error message on the field " + field + " is : " + error, error_firstname.getText().contentEquals(error));
        else if (field.contentEquals("Email address"))
            assertThat("Expected error message on the field " + field + " is : " + error, error_email.getText().contentEquals(error));
        else if (field.contentEquals("Phone"))
            assertThat("Expected error message on the field " + field + " is : " + error, error_phone.getText().contentEquals(error));
        else if (field.contentEquals("Org type"))
            assertThat("Expected error message on the field " + field + " is : " + error, error_org_type.getText().contentEquals(error));
        else
            throw new IllegalArgumentException("Either no such error " + error + " on the field " + field + " is available or need to write a condition for an error message on the field.");
        Log.info("***Error message " + error + " on the field " + field + " is as expected.***");
    }

    public void logout() {
        waitFor(link_login);
        assertThat("Not logged out", isElementVisible(By.xpath("//a[contains(text(),'Log in')]")));
        Log.info("***Logged off successfully.***");
    }

    public void enterEmailInResetPassword(String email) {
        waitFor(input_email_reset_password);
        input_email_reset_password.clear();
        if (email.contentEquals("") || email.isEmpty() || email.isBlank()) {
            input_email_reset_password.sendKeys(" ");
            assertThat("Expected value on email field in reset password screen is : " + email + " " + " but it is : " + input_email_reset_password.getValue(), input_email_reset_password.getValue().contentEquals(email + " "));
        } else {
            input_email_reset_password.sendKeys(email);
            assertThat("Expected value on email field in reset password screen is : " + email + " but it is : " + input_email_reset_password.getValue(), input_email_reset_password.getValue().contentEquals(email));
        }
        Log.info("***Email field value " + email + " on reset password screen is as expected.***");
    }

    public void errorCheckOnResetPassword(String error) {
        waitFor(error_email_reset_password);
        assertThat("Expected error message is : " + error + " but it is neither present nor it is : " + error_email_reset_password.getText(), error_email_reset_password.getText().contentEquals(error));
        Log.info("***Error message " + error + " on reset password screen is as expected.***");
    }

    public void checkYourEmailMessage() {
        waitFor(message_reset_password_h3);
        assertThat("Expected h3 message is : " + configFileReader.getProps().getProperty("message_reset_password_h3") + " but it is : " + message_reset_password_h3.getText(), message_reset_password_h3.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_h3")));
        assertThat("Expected p1 message is : " + configFileReader.getProps().getProperty("message_reset_password_p1") + " but it is : " + message_reset_password_p1.getText(), message_reset_password_p1.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_p1")));
        assertThat("Expected p2 message is : " + configFileReader.getProps().getProperty("message_reset_password_p2") + " but it is : " + message_reset_password_p2.getText(), message_reset_password_p2.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_p2")));
        Log.info("***Reset password messages are as expected.***");
        assertThat("Expected URL is : " + configFileReader.getProps().getProperty("message_reset_password_url") + " but it is : " + getDriver().getCurrentUrl(), getDriver().getCurrentUrl().contentEquals(configFileReader.getProps().getProperty("message_reset_password_url")));
        Log.info("***Reset password URL is as expected.***");
    }
}
