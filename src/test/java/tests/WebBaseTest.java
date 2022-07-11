package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigProvider;
import listeners.ScreenshotOnFailureListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import web.engine.WebDriverFactory;

import java.time.Duration;

@Slf4j
@Listeners(ScreenshotOnFailureListener.class)
public class WebBaseTest {

    @BeforeSuite(alwaysRun = true, description = "Download web driver binaries")
    protected void beforeSuite(ITestContext context) {
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        Configuration.timeout = Duration.ofSeconds(ConfigProvider.CONFIG_PROPS.driverTimeout()).toMillis();
    }

    @BeforeMethod(alwaysRun = true, description = "Initialize web driver configuration")
    protected void setUp() {
        final WebDriverFactory webDriverFactory = new WebDriverFactory();
        final WebDriver driver = webDriverFactory.createDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterMethod(alwaysRun = true, description = "Close driver")
    protected void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}