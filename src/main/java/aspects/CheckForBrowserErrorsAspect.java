package aspects;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.logging.LogType;
import utils.AllureUtils;

import java.util.List;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.getWebDriverLogs;

@Aspect
@Slf4j
public class CheckForBrowserErrorsAspect {

	//for web navigation tests only
	@After("execution(* *(..)) && @annotation(mobymax.configuration.annotations.CheckBrowserErrors)")
	public void checkBrowserConsoleErrors(JoinPoint jp) {
		if (WebDriverRunner.hasWebDriverStarted()) {
			//https://advancedweb.hu/detecting-errors-in-the-browser-with-selenium/
			//https://selenide.org/2019/12/16/advent-calendar-browser-logs/
				log.info("Checking for Web Browser errors");
				List<String> errorLogs = getWebDriverLogs(LogType.BROWSER, Level.SEVERE);
				System.out.println(errorLogs);
				if (!errorLogs.isEmpty()) {
					Allure.step("Check browser for console error logs", () -> {
						try {
							Assertions.fail("Browser console errors were detected: " + "\n" + String.join("\n", errorLogs));
						} finally {
							try {
								Allure.getLifecycle().updateStep(consumer -> new StepResult().setStatus(Status.FAILED));
								Allure.getLifecycle().updateTestCase(consumer -> new TestResult().setStatus(Status.FAILED));
							} finally {
								AllureUtils.attachBrowserConsoleErrorLogs(errorLogs);
							}
						}
					});
				}
		}
	}
}