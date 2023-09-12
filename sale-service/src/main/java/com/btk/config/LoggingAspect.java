package com.btk.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //Sınıfın bir Aspect olduğunu belirtir. Aspect, belirli metotlar veya sınıflar üzerinde ek işlemleri
// gerçekleştiren yapıdır.(bknz. interceptor)
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /*
    Bu anotasyon ile belirtilen pointcut'a sahip metotlardan önce bu metot çalıştırılır.
    com.btk.service paketi içindeki tüm sınıfların tüm metotlarına uygulanır.
     */
    @Before("execution(* com.btk.service.*.*(..))")
    public void beforeServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String methodArgs = Arrays.toString(joinPoint.getArgs());

        logger.info("Entering method: {} with arguments: {}", methodName, methodArgs);
    }

    /*
    Bu anotasyon ile belirtilen pointcut'a sahip metotlar normal bir şekilde tamamlandıktan sonra bu metot çalıştırılır.
    returning parametresi ile metotun dönüş değeri alınır.
     */
    @AfterReturning(pointcut = "execution(* com.btk.service.*.*(..))", returning = "result")
    public void afterReturningServiceMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();

        logger.info("Running Method: {}, returned: {}", methodName, result);
    }

    /*
    Bu anotasyon ile belirtilen pointcut'a sahip metotlar hatalı bir şekilde tamamlandığında bu metot çalıştırılır.
    throwing parametresi ile fırlatılan istisna alınır.
     */
    @AfterThrowing(pointcut = "execution(* com.btk.service.*.*(..))", throwing = "exception")
    public void afterThrowingServiceMethods(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();

        logger.error("Exception in method: {}", methodName, exception);
    }

    /*
    Bu anotasyon ile belirtilen pointcut'a sahip metotlar her ihtimâlde tamamlandıktan sonra bu metot çalıştırılır.
     */
    @After("execution(* com.btk.service.*.*(..))")
    public void afterServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        String methodArgs = Arrays.toString(joinPoint.getArgs());

        logger.info("Exiting method: {} with arguments: {}", methodName, methodArgs);
    }
}

