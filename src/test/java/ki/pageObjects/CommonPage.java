package ki.pageObjects;

import ki.stepDefinitions.Hooks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ki.stepDefinitions.Hooks.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommonPage extends PageObject {
    @FindBy(xpath = "//*[@data-value='See my pending quotes']")
    private static WebElementFacade radioButton_pendingQuotes;
    @FindBy(xpath = "//*[@data-value='Create a new quote']")
    private static WebElementFacade radioButton_newQuote;
    @FindBy(xpath = "//span[text()='Clear form']")
    private static WebElementFacade button_Clearform;
    @FindBy(xpath = "//*[@role='alertdialog']/div/div[@role='button']//*[text()='Clear form']")
    private static WebElementFacade buttonOnDialog_Clearform;
    @FindBy(xpath = "//*[@role='alertdialog']/div/div[@role='button']//*[text()='Cancel']")
    private static WebElementFacade button_Cancel;
    @FindBy(xpath = "//span[text()='Back']")
    private static WebElementFacade button_Back;
    @FindBy(xpath = "//div[@aria-checked='true']")
    private static List<WebElementFacade> radioButtons_selected;
    @FindBy(xpath = "//div[@aria-selected='true']")
    private static List<WebElementFacade> dropdowns_selected;
    @FindBy(className = "RHiWt")
    private static WebElementFacade fieldErrorMessage;
    @FindBy(xpath = "//*[text()='Next']")
    private static WebElementFacade button_Next;
    String expectedTitle = "Ki Mock Platform — Take Home Exercise";
    String expectedTitle_reason = "Ki Mock Platform — Take Home Exercise page has not been loaded.";
    String dialog_clearForm_heading = "Clear form?";
    String dialog_clearForm_body = "This will remove your answers from all questions and cannot be undone.";

    public void launchBaseURL() {
        driver.get(baseUrl);
        assertThat("Couldn't open up the page as the expected base URL is : " + baseUrl + " but the current url is : " + Hooks.driver.getCurrentUrl(), Hooks.driver.getCurrentUrl().contains(baseUrl));
        Log.info("Launched the base URL : " + baseUrl);
    }

    public CommonPage checkTitle() {
        waitForTitleToAppear(expectedTitle);
        assertThat(expectedTitle_reason, Hooks.driver.getTitle().contentEquals(expectedTitle));
        Log.info("***" + expectedTitle + " page has been loaded successfully.***");
        return this;
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
        waitFor(Hooks.driver.findElement(getWebElementOptionForRadioButton(radioButton))).waitUntilClickable().click();
        Log.info("***Clicked on " + radioButton + " radio button.***");
    }

    public By getWebElementOptionForRadioButton(String value) {
        return By.xpath("//*[@data-value='" + value + "']");
    }

    public void clearFormDialogBoxChecks() {
        Assert.assertTrue("'" + dialog_clearForm_heading + "' dialog box heading has not been popped up.", driver.findElement(By.xpath("//div[text()='" + dialog_clearForm_heading + "']")).isDisplayed());
        Log.info("***" + dialog_clearForm_heading + " dialog box heading has been displayed..***");
        Assert.assertTrue("'" + dialog_clearForm_body + "' dialog box body has not been popped up.", driver.findElement(By.xpath("//span[text()='" + dialog_clearForm_body + "']")).isDisplayed());
        Log.info("***" + dialog_clearForm_body + " dialog box body has been displayed..***");
    }

    public void clearedForm() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@aria-checked='true']"), 0));
        Assert.assertTrue("Form is not cleared as some radio buttons are still selected, please check it out!!!", radioButtons_selected.isEmpty() && dropdowns_selected.size() == 0);
        Assert.assertFalse("Form is not cleared as didn't return to first page since Back button is available", button_Back.isCurrentlyVisible());
        Log.info("***Form is cleared successfully.***");
    }

    public void checkFieldErrorMessage(String error_message) {
        Assert.assertTrue("Expected field error message is : " + error_message + " but the actual one is : " + fieldErrorMessage.getText(), error_message.contentEquals(fieldErrorMessage.getText()));
        Log.info("***Expected & actual field error message are same : '" + error_message + "'.***");
    }

    public CommonPage clickNextButton() {
        button_Next.click();
        Log.info("");
        Assert.assertTrue("", true);
        return this;
    }
}
