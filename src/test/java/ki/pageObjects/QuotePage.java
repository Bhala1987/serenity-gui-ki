package ki.pageObjects;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.mortbay.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static ki.stepDefinitions.Hooks.*;
import static utilities.Helpers.clearAndSendKeys;

public class QuotePage extends CommonPage {
    @FindBy(xpath = "//*[contains(@data-params,'Primary Insured')]//*[text()='Choose']")
    public static WebElementFacade select_PrimaryInsured;
    @FindBy(xpath = "//input[@type='date']")
    public static WebElementFacade datePicker;
    @FindBy(xpath = "//*[contains(@data-params,'AUM Value')]//*/input")
    public static WebElementFacade input_AUMvalue;
    @FindBy(xpath = "//*[contains(@data-params,'Premium')]//*/input")
    public static WebElementFacade input_Premium;
    @FindBy(css = ".meSK8.M7eMe")
    public static List<WebElementFacade> list_insureds;
    @FindBy(css = ".spb5Rd.OIC90c")
    public static List<WebElementFacade> list_classes_premiums;
    @FindBy(xpath = "//input[@aria-label='Day of the month']")
    private static WebElementFacade dayOfTheMonth;
    @FindBy(xpath = "//input[@aria-label='Month']")
    private static WebElementFacade month;
    @FindBy(xpath = "//input[@aria-label='Year']")
    private static WebElementFacade year;
    @FindBy(xpath = "//*[contains(@data-params,'Primary Insured')]//div[@aria-expanded='true']")
    private static WebElementFacade select_PrimaryInsured_checkDropdown;
    @FindBy(xpath = "//*[contains(@data-params,'Primary Country')]//*[text()='Choose']")
    private static WebElementFacade select_PrimaryCountry;
    @FindBy(xpath = "//*[contains(@data-params,'Primary Country')]//div[@aria-expanded='true']")
    private static WebElementFacade select_PrimaryCountry_checkDropdown;
    int counter = 0;
    String expected_message = "Success — Quote created";
    String expected_message_paragraph_1 = "Submission completed. Your quote has been created successfully.";
    String expected_message_paragraph_2 = "You will receive an email when the quote is ready. You can check the status of your pending quotes from the Pending Quotes screen.";

    public void selectPrimaryCountry(String country) {
        do {
            fluentWait.until(ExpectedConditions.elementToBeClickable(select_PrimaryCountry)).click();
            counter++;
        } while (!select_PrimaryCountry_checkDropdown.isPresent() && counter <= 5);
        Assert.assertTrue("Primary Country dropdown is not opened up.", select_PrimaryCountry_checkDropdown.isPresent());
        fluentWait.until(ExpectedConditions.elementToBeClickable(getWebElementOption(country))).click();
        Log.info("***Selected '" + country + "' as a primary country.***");
    }

    public By getWebElementOption(String value) {
        return By.xpath("//div[@role='option' and @data-value='" + value + "']");
    }

    public void selectPrimaryInsured(String insured) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(select_PrimaryInsured)).click();
        Assert.assertTrue("Primary Insured dropdown is not opened up.", select_PrimaryInsured_checkDropdown.isPresent());
        fluentWait.until(ExpectedConditions.elementToBeClickable(getWebElementOption(insured))).click();
        Log.info("***Selected '" + insured + "' as a primary insured.***");
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
        String output_date = null;
        Instant instant = Instant.now().plus(months * 30L, ChronoUnit.DAYS);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = inputFormat.parse(instant.toString().split("T")[0]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        output_date = outputFormat.format(date);
        if (driverName.contentEquals("firefox")) { //Date picker is not available in firefox hence this condition
            if (months > 0) {
                String[] split_output_date = output_date.split("/");
                clearAndSendKeys(dayOfTheMonth, split_output_date[0]);
                clearAndSendKeys(month, split_output_date[1]);
                clearAndSendKeys(year, split_output_date[2]);
            } else {
                clearAndSendKeys(dayOfTheMonth, "12");
                clearAndSendKeys(month, "");
                clearAndSendKeys(year, "");
            }
        } else {
            if (months == 0) clearAndSendKeys(datePicker, "9999999999");
            else clearAndSendKeys(datePicker, output_date);
            datePicker.sendKeys(Keys.TAB);
        }
        Log.info("***Entered the required date.***");
    }

    public void enterAUMvalue(String AUMvalue) {
        input_AUMvalue.sendKeys(AUMvalue);
        input_AUMvalue.sendKeys(Keys.TAB); // for error messages
        Log.info("***Entered the required AUM value : " + AUMvalue + ".***");
    }

    public void enterPremiumvalue(String PremiumValue) {
        input_Premium.sendKeys(PremiumValue);
        input_AUMvalue.sendKeys(Keys.TAB); // for error messages
        Log.info("***Entered the required Premium value : " + PremiumValue + ".***");
    }

    public void checkSuccessMessage() {
        Assert.assertTrue("'" + expected_message + "' message is not shown or incorrect, please check it out!!!", driver.findElement(By.xpath("//div[text()='" + expected_message + "']")).isDisplayed());
        Log.info("***" + expected_message + " message is shown successfully.***");
        Assert.assertTrue("'" + expected_message_paragraph_1 + "' 1st paragraph is not shown or incorrect, please check it out!!!", driver.findElement(By.xpath("//div[text()='" + expected_message_paragraph_1 + "']")).isDisplayed());
        Log.info("***" + expected_message_paragraph_1 + " 1st paragraph is shown successfully.***");
        Assert.assertTrue("'" + expected_message_paragraph_2 + "' 2nd paragraph is not shown or incorrect, please check it out!!!", driver.findElement(By.xpath("//div[text()='" + expected_message_paragraph_2 + "']")).isDisplayed());
        Log.info("***" + expected_message_paragraph_2 + " 2nd paragraph is shown successfully.***");
    }

    public void checkInsureds(String insureds) {
        List<String> expected_insureds_list = List.of(insureds.split(","));
        List<String> actual_insureds_list = list_insureds.stream().map(WebElementFacade::getText).toList();
        Assert.assertEquals("Expected & actual insureds list are not matching, please check it out!!! Expected : " + expected_insureds_list + " whereas the actual : " + actual_insureds_list, actual_insureds_list, expected_insureds_list);
        Log.info("***Expected & actual insureds list : " + expected_insureds_list + " are matching for the pending quotes list.***");
    }

    public QuotePage checkClasses_Premiums(String classes_premiums) {
        List<String> expected_classes_premiums_list = List.of(classes_premiums.split(","));
        List<String> actual_classes_premiums_list = list_classes_premiums.stream().map(WebElementFacade::getText).toList();
        Assert.assertEquals("Expected & actual classes & premiums list are not matching, please check it out!!! Expected : " + expected_classes_premiums_list + " whereas the actual : " + actual_classes_premiums_list, actual_classes_premiums_list, expected_classes_premiums_list);
        Log.info("***Expected & actual classes & premiums list : " + actual_classes_premiums_list + " are matching for the pending quotes list.***");
//        clickNextButton().checkTitle().clickButton("");
        return this;
    }
}
