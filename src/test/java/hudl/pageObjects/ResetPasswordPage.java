package hudl.pageObjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.mortbay.log.Log;
import utilities.ConfigFileReader;
import utilities.Hooks;

import static org.hamcrest.MatcherAssert.assertThat;

public class ResetPasswordPage extends PageObject {

    @FindBy(xpath = "//h3")
    public static WebElementFacade message_reset_password_h3;
    @FindBy(xpath = "//p[1]")
    public static WebElementFacade message_reset_password_p1;
    @FindBy(xpath = "//p[2]")
    public static WebElementFacade message_reset_password_p2;
    @FindBy(xpath = "//input[@data-qa-id='password-reset-input']")
    public static WebElementFacade input_email_reset_password;
    @FindBy(xpath = "//p[@data-qa-id='password-reset-error-display']")
    public static WebElementFacade error_email_reset_password;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public void enterEmailInResetPassword(String email) {
        waitFor(input_email_reset_password).clear();
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
        waitFor(message_reset_password_p1);
        waitFor(message_reset_password_p2);
        assertThat("Expected h3 message is : " + configFileReader.getProps().getProperty("message_reset_password_h3") + " but it is : " + message_reset_password_h3.getText(), message_reset_password_h3.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_h3")));
        assertThat("Expected p1 message is : " + configFileReader.getProps().getProperty("message_reset_password_p1") + " but it is : " + message_reset_password_p1.getText(), message_reset_password_p1.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_p1")));
        assertThat("Expected p2 message is : " + configFileReader.getProps().getProperty("message_reset_password_p2") + " but it is : " + message_reset_password_p2.getText(), message_reset_password_p2.getText().contentEquals(configFileReader.getProps().getProperty("message_reset_password_p2")));
        Log.info("***Reset password messages are as expected.***");
        assertThat("Expected URL is : " + configFileReader.getProps().getProperty("message_reset_password_url") + " but it is : " + Hooks.getDriver().getCurrentUrl(), Hooks.getDriver().getCurrentUrl().contentEquals(configFileReader.getProps().getProperty("message_reset_password_url")));
        Log.info("***Reset password URL is as expected.***");
    }
}
