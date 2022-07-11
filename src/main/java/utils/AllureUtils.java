package utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AllureUtils {
    public static void logTextInAllureAttachment(String resourceName) {
        if (WebDriverRunner.hasWebDriverStarted()) {
            log.info("Log texts in to attachment section");
            try {
                Allure.addAttachment("Log text from test",
                        "text/plain",
                        resourceName,
                        ".log");
            } catch (Exception e) {
                log.error("Error log test text: " + e.getMessage());
            }
        }
    }

    public static void attachBrowserConsoleErrorLogs(List<String> errorLogs) {
        if (WebDriverRunner.hasWebDriverStarted()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String logEntry : errorLogs) {
                stringBuilder.append(logEntry).append("\n");
            }
            log.info("Taking current browser console js error logs");
            try {
                Allure.addAttachment("Browser Console js error logs", "text/plain", stringBuilder.toString(), ".log");
            } catch (Exception e) {
                log.error("Error taking current browser console js error logs: " + e.getMessage());
            }
        }
    }
}
