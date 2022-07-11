package aspects;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Slf4j
public class ConsoleLogAspect {

    @Around("@annotation(io.qameta.allure.Step) && execution(* *(..))")
    public Object log(final ProceedingJoinPoint joinPoint) throws Throwable {
        String text = getLogText(joinPoint);
        log.info("Step: " + text);
        return joinPoint.proceed();
    }

    private String getLogText(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
            Step stepClass = methodSignature.getMethod().getAnnotation(Step.class);
            if (Objects.nonNull(stepClass)) {
                String mainStepText = stepClass.value();
                String stepParamRegexp = "[{].*?[}]";
                if (mainStepText.matches(".*" + stepParamRegexp + ".*")) {
                    Object[] methodArgs = joinPoint.getArgs();
                    for (int methodArgOrderIndex = 0; methodArgOrderIndex < methodArgs.length; methodArgOrderIndex++) {
                        if (methodArgs[methodArgOrderIndex] != null && methodArgs[methodArgOrderIndex].getClass().isArray()) {
                            mainStepText =
                                    mainStepText.replace("{" + codeSignature.getParameterNames()[methodArgOrderIndex] + "}",
                                            Arrays.toString((Object[]) methodArgs[methodArgOrderIndex]));
                        } else {
                            mainStepText =
                                    mainStepText.replace("{" + codeSignature.getParameterNames()[methodArgOrderIndex] + "}",
                                            methodArgs[methodArgOrderIndex].toString());
                        }
                    }
                }
                return mainStepText;
            } else {
                return methodSignature.getMethod().getName();
            }
        } catch (Exception e) {
            String errorStepMessage = "Cannot retrieve step text information";
            log.error(errorStepMessage);
            return errorStepMessage;
        }
    }
}
