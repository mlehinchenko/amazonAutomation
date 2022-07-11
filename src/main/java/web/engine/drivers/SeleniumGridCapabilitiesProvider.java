package web.engine.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumGridCapabilitiesProvider {

    private static final DesiredCapabilities CAPABILITIES;

    static {
        CAPABILITIES = new DesiredCapabilities();
        //set here some common capabilities for selenium grid driver
        CAPABILITIES.setCapability("acceptInsecureCerts", true);
    }

    public static DesiredCapabilities getCapabilities() {
        return CAPABILITIES;
    }
}
