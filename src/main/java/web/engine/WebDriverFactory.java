package web.engine;

import config.ConfigProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import web.engine.drivers.ChromeOptionsProvider;
import web.engine.drivers.SeleniumGridCapabilitiesProvider;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class WebDriverFactory {

    public WebDriver createDriver() {
        switch (ExecutionType.getFromConfig()) {
            case local:
                return createLocalDriver();
            case docker_grid:
                return createDockerDriver();
            default:
                throw new IllegalArgumentException(String.format(
                        "No implementation for provided execution type: " + "Execution Type[%s]",
                        ConfigProvider.CONFIG_PROPS.executionType()));
        }
    }

    private WebDriver createLocalDriver() {
        switch (DriverType.getFromConfig()) {
            case chrome:
                log.info("Creating local chrome driver");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(ChromeOptionsProvider.getChromeOptions());
            default:
                throw new IllegalArgumentException(String.format(
                        "No implementation for provided driver type: " + "Driver Type[%s]",
                        ConfigProvider.CONFIG_PROPS.driverType()));
        }
    }

    @SneakyThrows(MalformedURLException.class)
    private WebDriver createDockerDriver() {
        String urlStr = ConfigProvider.CONFIG_PROPS.getHubUrl();
        if (!(System.getProperty("HUB_HOST").isEmpty() || System.getProperty("HUB_HOST") == null)) {
            urlStr = System.getProperty("HUB_HOST");
        }
        final URL urlObj = new URL("http://" + urlStr + ":4444");
        log.info("Selenoid URL: " + urlStr);
        switch (DriverType.getFromConfig()) {
            case chrome:
                log.info("Creating remote chrome driver");
                final DesiredCapabilities caps = SeleniumGridCapabilitiesProvider.getCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, ChromeOptionsProvider.getChromeOptions());
                return new RemoteWebDriver(urlObj, caps);
            default:
                throw new IllegalArgumentException(String.format(
                        "No implementation for provided driver type: " + "Driver Type[%s]",
                        ConfigProvider.CONFIG_PROPS.driverType()));
        }
    }
}
