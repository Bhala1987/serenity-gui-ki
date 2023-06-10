package ki.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.scheduling.SerenityFluentWait;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static SerenityFluentWait fluentWait;
    public static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    public static String baseUrl = environmentVariables.getProperty("webdriver.base.url");
    public static String driverName = environmentVariables.getProperty("webdriver.driver");
    static Dimension size = null;

    ChromeOptions chromeOptions = new ChromeOptions();

    @Before
    public void setUp() {
        if (Platform.getCurrent().is(Platform.LINUX)) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--profile-directory=Default");
            chromeOptions.addArguments("--user-data-dir=~/.config/google-chrome");
        }
        if (Objects.nonNull(System.getProperty("mobile.emulator"))) {
            // Set the size of the browser window for mobile emulator
            switch (System.getProperty("mobile.emulator").toLowerCase()) {
                case "iphonese" -> size = new Dimension(375, 667);
                case "iphone12pro" -> size = new Dimension(390, 844);
                case "samsunggalaxys20ultra" -> size = new Dimension(412, 915);
            }
        }
        if (Objects.nonNull(System.getProperty("tablet.emulator"))) {
            // Set the size of the browser window for mobile emulator
            switch (System.getProperty("tablet.emulator").toLowerCase()) {
                case "ipadair" -> size = new Dimension(820, 1180);
                case "surfacepro7" -> size = new Dimension(912, 1368);
                case "nesthubmax" -> size = new Dimension(1280, 800);
            }
        }
        driver = Serenity.getDriver();
        if (Objects.nonNull(size)) driver.manage().window().setSize(size);
        else driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(environmentVariables.getProperty("serenity.explicit.wait"))));
        fluentWait = new SerenityFluentWait(driver).withTimeoutOf(Integer.parseInt(environmentVariables.getProperty("serenity.fluent.wait"))).seconds().pollingEvery(Integer.parseInt(environmentVariables.getProperty("serenity.fluentpoll.wait"))).seconds();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(environmentVariables.getProperty("serenity.implicit.wait"))));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
