package utilities;

import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class Hooks {
    static WebDriver driver;
    static Dimension size = null;

    public static WebDriver getDriver() {
        if (Objects.nonNull(size)) driver.manage().window().setSize(size);
        else Serenity.getDriver().manage().window().maximize();
        return driver;
    }

    @Before
    public void before() {
        if (Objects.nonNull(System.getProperty("mobile.emulator"))) {
            // Set the size of the browser window for mobile emulator
            switch (System.getProperty("mobile.emulator").toLowerCase()) {
                case "iphonese":
                    size = new Dimension(375, 667);
                    break;
                case "iphone12pro":
                    size = new Dimension(390, 844);
                    break;
                case "samsunggalaxys20ultra":
                    size = new Dimension(412, 915);
                    break;
            }
        }
        if (Objects.nonNull(System.getProperty("tablet.emulator"))) {
            // Set the size of the browser window for mobile emulator
            switch (System.getProperty("tablet.emulator").toLowerCase()) {
                case "ipadair":
                    size = new Dimension(820, 1180);
                    break;
                case "surfacepro7":
                    size = new Dimension(912, 1368);
                    break;
                case "nesthubmax":
                    size = new Dimension(1280, 800);
                    break;
            }
        }
        driver = Serenity.getDriver();
        if (Objects.nonNull(size)) driver.manage().window().setSize(size);
    }
}
