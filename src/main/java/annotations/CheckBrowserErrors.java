package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*If the annotation @CheckBrowserErrors is present on the method,
*then it will look for all js errors in the console
*and if it finds something then it will fail the test
*and log all console js errors in to allure report.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckBrowserErrors {
}
