package ki.pageObjects;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Hooks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static utilities.Helpers.clearAndSendKeys;

public class QuotePage extends PageObject {

    @FindBy(className = "Uc2NEf")
    public static WebElementFacade emptySpace;

    @FindBy(xpath = "//div[@aria-labelledby='i1']")
    public static WebElementFacade select_PrimaryCountry;

    @FindBy(xpath = "//div[@aria-labelledby='i5']")
    public static WebElementFacade select_PrimaryInsured;

    @FindBy(xpath = "//input[@type='date']")
    public static WebElementFacade datePicker;

    @FindBy(xpath = "//input[@type='text' and @aria-describedby='i7 i8']")
    public static WebElementFacade input_AUMvalue;

    @FindBy(xpath = "//input[@type='text' and @aria-describedby='i11 i12']")
    public static WebElementFacade input_Premium;

    @FindBy(css = ".aG9Vid.vVO4xd.M7eMe")
    public static WebElementFacade message_success;

    @FindBy(css = ".vfQisd.Q8wTDd.OIC90c")
    public static WebElementFacade message_success_paragraph;

    @FindBy(css = ".meSK8.M7eMe")
    public static List<WebElementFacade> list_insureds;
    @FindBy(css = ".spb5Rd.OIC90c")
    public static List<WebElementFacade> list_classes_premiums;
    String expected_message = "Success — Quote created";
    String expected_message_paragraph = "Submission completed. Your quote has been created successfully.\n\nYou will receive an email when the quote is ready. You can check the status of your pending quotes from the Pending Quotes screen.";

    public void selectPrimaryCountry(String country) {
        waitForAngularRequestsToFinish();
        int count = 0;
        do {
            waitFor(select_PrimaryCountry).waitUntilClickable().click();
            count++;
        } while (count > 5 || !select_PrimaryCountry.getAttribute("aria-expanded").contentEquals("true"));
        count = 0;
        do {
            waitFor(Hooks.getDriver().findElement(By.xpath("//div[@data-value='" + country + "' and @role='option']"))).waitUntilClickable().click();
            count++;
        } while (count > 5 || !Hooks.getDriver().findElement(By.xpath("//div[@data-value='" + country + "' and @role='option']")).getAttribute("aria-selected").contentEquals("true"));
        Log.info("***Selected '" + country + "' as a primary country.***");
    }

    public void selectPrimaryInsured(String insured) {
        waitForAngularRequestsToFinish();
        int count = 0;
        do {
            waitFor(select_PrimaryInsured).waitUntilClickable().click();
            count++;
        } while (count > 5 || !select_PrimaryInsured.getAttribute("aria-expanded").contentEquals("true"));
        count = 0;
        do {
            waitFor(Hooks.getDriver().findElement(By.xpath("//div[@data-value='" + insured + "' and @role='option']"))).waitUntilClickable().click();
            count++;
        } while (count > 5 || !Hooks.getDriver().findElement(By.xpath("//div[@data-value='" + insured + "' and @role='option']")).getAttribute("aria-selected").contentEquals("true"));
        Log.info("***Selected '" + insured + "' as a primary insured.***");
    }

    public void clickRadioButton(String radioButton) {
        waitFor(Hooks.getDriver().findElement(By.xpath("//span[text()='" + radioButton + "']"))).waitUntilClickable().click();
        Log.info("***Clicked on the radio button : " + radioButton + ".***");
    }

    public void quoteForm(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            //Primary Country
            if (row.get("Primary Country") != null) selectPrimaryCountry(row.get("Primary Country"));
            //Primary Insured
            if (row.get("Primary Insured") != null) selectPrimaryInsured(row.get("Primary Insured"));
            //Class of Business
            if (row.get("Class of Business") != null) clickRadioButton(row.get("Class of Business"));
        }
    }

    public void setDate(int months) {
        Instant instant = Instant.now().plus(months * 30L, ChronoUnit.DAYS);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = inputFormat.parse(instant.toString().split("T")[0]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String output_date = outputFormat.format(date);
        clearAndSendKeys(datePicker, output_date);
        datePicker.sendKeys(Keys.TAB);
        Log.info("***Entered the required date : " + output_date + ".***");
    }

    public void enterAUMvalue(String AUMvalue) {
        clearAndSendKeys(input_AUMvalue, AUMvalue);
        input_AUMvalue.sendKeys(Keys.TAB);
        Log.info("***Entered the required AUM value : " + AUMvalue + ".***");
    }

    public void enterPremiumvalue(String PremiumValue) {
        clearAndSendKeys(input_Premium, PremiumValue);
        Log.info("***Entered the required Premium value : " + PremiumValue + ".***");
    }

    public void checkSuccessMessage() {
        Assert.assertTrue("'Success — Quote created' message is not shown or incorrect, please check it out!!!", message_success.getText().contentEquals(expected_message));
        Log.info("***" + expected_message + " message is shown successfully.***");
        Assert.assertTrue("'Success — Quote created' paragraph is not shown or incorrect, please check it out!!!", message_success_paragraph.getText().contentEquals(expected_message_paragraph));
        Log.info("***" + expected_message_paragraph + " paragraph is shown successfully.***");
    }

    public void checkInsureds(String insureds) {
        List<String> expected_insureds_list = List.of(insureds.split(","));
        List<String> actual_insureds_list = list_insureds.stream().map(WebElementFacade::getText).toList();
        Assert.assertEquals("Expected & actual insureds list are not matching, please check it out!!! Expected : " + expected_insureds_list + " whereas the actual : " + actual_insureds_list, actual_insureds_list, expected_insureds_list);
        Log.info("***Expected & actual insureds list : " + expected_insureds_list + " are matching for the pending quotes list.***");
    }

    public void checkClasses_Premiums(String classes_premiums) {
        List<String> expected_classes_premiums_list = List.of(classes_premiums.split(","));
        List<String> actual_classes_premiums_list = list_classes_premiums.stream().map(WebElementFacade::getText).toList();
        Assert.assertEquals("Expected & actual classes & premiums list are not matching, please check it out!!! Expected : " + expected_classes_premiums_list + " whereas the actual : " + actual_classes_premiums_list, actual_classes_premiums_list, expected_classes_premiums_list);
        Log.info("***Expected & actual classes & premiums list : " + actual_classes_premiums_list + " are matching for the pending quotes list.***");
    }
}
