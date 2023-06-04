package ki.pageObjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.mortbay.log.Log;
import utilities.Hooks;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CommonPage extends PageObject {

    @FindBy(id = "i7")
    public static WebElementFacade radioButton_pendingQuotes;
    @FindBy(id = "i10")
    public static WebElementFacade radioButton_newQuote;
    @FindBy(xpath = "//span[text()='Next']")
    public static WebElementFacade button_Next;
    @FindBy(xpath = "//span[text()='Clear form']")
    public static WebElementFacade button_Clearform;
    @FindBy(xpath = "//div[@class='uArJ5e UQuaGc kCyAyd ARrCac M9Bg4d']/../div/span/span[text()='Clear form']")
    public static WebElementFacade buttonOnDialog_Clearform;
    @FindBy(xpath = "//span[text()='Back']")
    public static WebElementFacade button_Back;
    @FindBy(xpath = "//div[@class='uArJ5e UQuaGc kCyAyd ARrCac M9Bg4d']/span/span[text()='Cancel']")
    public static WebElementFacade button_Cancel;
    @FindBy(css = ".Shk6y.bEd2J")
    public static WebElementFacade dialog_clearForm_heading;
    @FindBy(css = ".UYUfn.oJeWuf.wnIM7.fb0g6")
    public static WebElementFacade dialog_clearForm_body;
    @FindBy(xpath = "//div[@aria-checked='true']")
    public static List<WebElementFacade> radioButtons_selected;
    @FindBy(xpath = "//div[@aria-selected='true']")
    public static List<WebElementFacade> dropdowns_selected;
    @FindBy(className = "md0UAd")
    public static WebElementFacade topErrorMessage;
    @FindBy(className = "RHiWt")
    public static WebElementFacade fieldErrorMessage;
    String expectedTitle = "Ki Mock Platform — Take Home Exercise";
    String expectedTitle_reason = "Ki Mock Platform — Take Home Exercise page has not been loaded.";
    // Get the EnvironmentVariables object
    EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    // Get the value of webdriver.base.url from serenity.properties
    String baseUrl = environmentVariables.getProperty("webdriver.base.url");

    public void launchBaseURL() {
        Hooks.getDriver().get(baseUrl);
        assertThat("Couldn't open up the page as the expected base URL is : " + baseUrl + " but the current url is : " + Hooks.getDriver().getCurrentUrl(), Hooks.getDriver().getCurrentUrl().contains(baseUrl));
        Log.info("Launched the base URL : " + baseUrl);
    }

    public void checkTitle() {
        waitForTitleToAppear(expectedTitle);
        assertThat(expectedTitle_reason, Hooks.getDriver().getTitle().contentEquals(expectedTitle));
        Log.info("***" + expectedTitle + " page has been loaded successfully.***");
    }

    public void clickButton(String button) {
        if (button.contentEquals("Next")) waitFor(button_Next).click();
        else if (button.contentEquals("Clear form")) waitFor(button_Clearform).click();
        else if (button.contentEquals("Clear form on dialog")) waitFor(buttonOnDialog_Clearform).click();
        else if (button.contentEquals("Cancel")) waitFor(button_Cancel).click();
        else if (button.contentEquals("Back")) waitFor(button_Back).click();
        else throw new IllegalArgumentException("Need to write a condition for a button : " + button);
        Log.info("***Clicked on " + button + " button.***");
    }

    public void clickRadioButton(String radioButton) {
        if (radioButton.contentEquals("Create a new quote")) waitFor(radioButton_newQuote).click();
        else if (radioButton.contentEquals("See my pending quotes")) waitFor(radioButton_pendingQuotes).click();
        else
            throw new IllegalArgumentException("Either no such radio button available or need to write a condition for a radio button : " + radioButton);
        Log.info("***Clicked on " + radioButton + " radio button.***");
    }

    public void clearFormDialogBoxChecks() {
        Assert.assertTrue("Clear form? dialog box heading has not been displayed.", dialog_clearForm_heading.isCurrentlyVisible() && dialog_clearForm_heading.getText().contentEquals("Clear form?"));
        Log.info("***Clear form? dialog box heading has been displayed..***");
        Assert.assertTrue("Clear form? dialog box body has not been displayed.", dialog_clearForm_body.isCurrentlyVisible() && dialog_clearForm_body.getText().contentEquals("This will remove your answers from all questions and cannot be undone."));
        Log.info("***Clear form? dialog box body has been displayed..***");
    }

    public void clearedForm() {
        Assert.assertTrue("Form is not cleared as some radio buttons are still selected, please check it out!!!", radioButtons_selected.isEmpty() && dropdowns_selected.size() == 0);
        Assert.assertFalse("Form is not cleared as didn't return to first page since Back button is available", button_Back.isCurrentlyVisible());
        Log.info("***Form is cleared successfully.***");
    }

    public void checkFieldErrorMessage(String error_message) {
        Assert.assertTrue("Expected field error message is : " + error_message + " but the actual one is : " + fieldErrorMessage.getText(), error_message.contentEquals(fieldErrorMessage.getText()));
        Log.info("***Expected & actual field error message are same : '" + error_message + "'.***");
    }
}
