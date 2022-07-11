package web.engine.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class ChromeOptionsProvider {

    private static final ChromeOptions CHROME_OPTIONS;

    static {
        CHROME_OPTIONS = new ChromeOptions();
        CHROME_OPTIONS.setPageLoadStrategy(PageLoadStrategy.NONE);
        CHROME_OPTIONS.addArguments("disable-infobars");
        CHROME_OPTIONS.addArguments("--disable-notifications");
        CHROME_OPTIONS.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        CHROME_OPTIONS.addArguments("--no-sandbox"); // Bypass OS security model
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        CHROME_OPTIONS.setCapability("goog:loggingPrefs", logPrefs);
    }

    public static ChromeOptions getChromeOptions() {
        return CHROME_OPTIONS;
    }
}
