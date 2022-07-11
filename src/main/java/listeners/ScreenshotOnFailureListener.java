package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.util.Objects;

@Slf4j
public class ScreenshotOnFailureListener implements IInvokedMethodListener {
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (Objects.nonNull(testResult.getThrowable())) {
                if (WebDriverRunner.hasWebDriverStarted()) {
                    log.info("Taking screenshot on test failure");
                    try {
                        Allure.addAttachment(
                                "Screenshot on test failure",
                                "image/png",
                                new ByteArrayInputStream(((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(
                                        OutputType.BYTES)),
                                "png");
                    } catch (Exception e) {
                        log.error("Error taking screenshot on test failure: " + e.getMessage());
                    }
                }
            }
        }
    }
}