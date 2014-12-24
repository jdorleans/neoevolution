package org.neoevolution.mvc;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.neoevolution.util.Times;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@Aspect
@Component
public class EvolutionLog {

    private static final Logger LOG = Logger.getLogger(EvolutionLog.class);

    @Around("execution(public * org.neoevolution.mvc.service.EvolutionService.* (..))")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        StopWatch watch = logBefore(point);
        Object result = point.proceed();
        logAfter(point, watch, result);
        return result;
    }

    private StopWatch logBefore(ProceedingJoinPoint point)
    {
        StopWatch watch = null;

        if (LOG.isDebugEnabled())
        {
            StringBuilder builder = new StringBuilder();
            builder.append("Executing ");
            appendName(point, builder);
            builder.append(" with arguments: ");
            builder.append(Arrays.toString(point.getArgs()));
            LOG.debug(builder.toString());

            watch = new StopWatch();
            watch.start();
        }
        return watch;
    }

    private void logAfter(ProceedingJoinPoint point, StopWatch watch, Object result)
    {
        if (watch != null && LOG.isDebugEnabled())
        {
            watch.stop();
            long time = watch.getLastTaskTimeMillis();

            StringBuilder builder = new StringBuilder();
            appendName(point, builder);
            builder.append(" in ");

            if (time >= Times.MILLIS_SECOND) {
                builder.append(time / Times.MILLIS_SECOND).append(" sec");
            } else {
                builder.append(time).append(" millis");
            }
            builder.append(" with result: ").append(result);
            LOG.debug(builder.toString());
        }
    }

    private void appendName(JoinPoint point, StringBuilder builder) {
        Signature signature = point.getSignature();
        builder.append(signature.getDeclaringTypeName());
        builder.append(".");
        builder.append(signature.getName());
    }

}
