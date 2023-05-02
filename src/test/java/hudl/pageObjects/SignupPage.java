package hudl.pageObjects;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.mortbay.log.Log;
import utilities.Hooks;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static utilities.Helpers.clearAndSelectValue;
import static utilities.Helpers.clearAndSendKeys;

public class SignupPage extends PageObject {

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
    @FindBy(id = "ValidMsgFirstName")
    public static WebElementFacade error_firstname;
    @FindBy(id = "ValidMsgEmail")
    public static WebElementFacade error_email;
    @FindBy(id = "ValidMsgPhone")
    public static WebElementFacade error_phone;
    @FindBy(id = "ValidMsgOrg_Classification__c_account")
    public static WebElementFacade error_org_type;

    public void signup(DataTable dataTable) {
        Hooks.getDriver().navigate().refresh(); // REFRESHING TO SELECT THE Select... OPTION
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            //First name
            clearAndSendKeys(input_signup_firstname, row.get("First name"));
            //Last name
            clearAndSendKeys(input_signup_lastname, row.get("Last name"));
            //Email address
            clearAndSendKeys(input_signup_email, row.get("Email address"));
            //Phone number
            clearAndSendKeys(input_signup_phone, row.get("Phone number"));
            //Country
            clearAndSelectValue(input_signup_country, row.get("Country"));
            //State
            clearAndSelectValue(input_signup_state, row.get("State"));
            //Organization name
            clearAndSendKeys(input_signup_org_name, row.get("Org name"));
            //Organization Level:
            clearAndSelectValue(input_signup_org_level, row.get("Org level"));
            //Organization type
            clearAndSelectValue(input_signup_org_type, row.get("Org type"));
            //Sport
            clearAndSelectValue(input_signup_sport, row.get("Sport"));
            //Role on team
            clearAndSelectValue(input_signup_role, row.get("Role"));
            //Message
            clearAndSendKeys(input_signup_message, row.get("Message"));
        }
    }

    public void personalDetails(DataTable dataTable) {
        Hooks.getDriver().navigate().refresh(); // REFRESHING TO SELECT THE Select... OPTION
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            //First name
            clearAndSendKeys(input_signup_firstname, row.get("First name"));
            //Last name
            clearAndSendKeys(input_signup_lastname, row.get("Last name"));
            //Email address
            clearAndSendKeys(input_signup_email, row.get("Email address"));
            //Phone number
            clearAndSendKeys(input_signup_phone, row.get("Phone number"));
            //Country
            clearAndSelectValue(input_signup_country, row.get("Country"));
            //State
            clearAndSelectValue(input_signup_state, row.get("State"));
        }
    }

    public void companyDetails(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            //Organization name
            clearAndSendKeys(input_signup_org_name, row.get("Org name"));
            //Organization Level:
            clearAndSelectValue(input_signup_org_level, row.get("Org level"));
            //Organization type
            clearAndSelectValue(input_signup_org_type, row.get("Org type"));
            //Sport
            clearAndSelectValue(input_signup_sport, row.get("Sport"));
            //Role on team
            clearAndSelectValue(input_signup_role, row.get("Role"));
            //Message
            clearAndSendKeys(input_signup_message, row.get("Message"));
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


}
