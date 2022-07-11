package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

public class WaitUtils {

    public static void waitUntilPageReady(){
        Runnable runnable = () -> {
            boolean pageStage = Boolean.TRUE.equals(Selenide.executeJavaScript("return (document.readyState === 'complete')"));
            if (!pageStage) {
                throw new RuntimeException("Page wasn't loaded");
            }
        };
        until(runnable);
    }

    public static void until(Duration duration, Duration polling, Runnable runnable) {
        long end = laterBy(duration.toMillis());
        while (true) {
            try {
                runnable.run();
                return;
            } catch (AssertionError | RuntimeException e) {
                if (!isNowBefore(end)) {
                    throw new RuntimeException(e);
                }
            }
            sleep(polling.toMillis());
        }
    }

    public static void until(Runnable runnable) {
        until(Duration.ofMillis(Configuration.timeout), Duration.ofMillis(200), runnable);
    }

    private static long laterBy(long durationInMillis) {
        return System.currentTimeMillis() + durationInMillis;
    }

    private static boolean isNowBefore(long endInMillis) {
        return System.currentTimeMillis() < endInMillis;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
