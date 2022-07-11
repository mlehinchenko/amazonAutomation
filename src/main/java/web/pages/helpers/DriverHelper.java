package web.pages.helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

public class DriverHelper {
    @Step("Switch to window")
    public static void switchToWindow() {
        String currentWindowID = WebDriverRunner.getWebDriver().getWindowHandle();
        for (String winHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!winHandle.equals(currentWindowID)) {
                WebDriverRunner.getWebDriver().switchTo().window(winHandle);
            }
        }
    }
}
