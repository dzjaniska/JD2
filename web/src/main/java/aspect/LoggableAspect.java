package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class LoggableAspect {

    private static final Logger LOGGER = Logger.getLogger(LoggableAspect.class);
    private static final String REPORTS_DIVIDER = "<--------------------------------------------------------->";

    @Pointcut("within(service..*)")
    public void services() {
    }

    @Around("services()")
    public Object getServiceLog(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        StringBuilder reportStringBuilder = new StringBuilder();
        reportStringBuilder.append(reportRowFormat("Service", joinPoint.getTarget().getClass().getSimpleName()))
                .append(reportRowFormat("Method", joinPoint.getSignature().getName()))
                .append(reportRowFormat("Args", Arrays.toString(joinPoint.getArgs())))
                .append(reportRowFormat("Result", result != null ? result.toString() : "[]"))
                .append(reportRowFormat("Implementing time (ms)",
                        Long.toString(System.currentTimeMillis() - start)));
        LOGGER.info(wrapReport(reportStringBuilder.toString()));
        return result;
    }

    private String wrapReport(String report) {
        return "\n" + report + REPORTS_DIVIDER;
    }

    private String reportRowFormat(String parameterName, String parameterValue) {
        return String.format("%s: %s\n", parameterName, parameterValue);
    }
}
