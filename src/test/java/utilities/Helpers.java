package utilities;

import net.serenitybdd.core.pages.WebElementFacade;

import java.util.Objects;

public class Helpers {

    public static void clearAndSendKeys(WebElementFacade webElementFacade, String data) {
        webElementFacade.clear();
        webElementFacade.sendKeys(data);
    }

    public static void clearAndSelectValue(WebElementFacade webElementFacade, String data) {
        if (Objects.isNull(data)) webElementFacade.deselect();
        else webElementFacade.selectByValue(data);
    }
}
