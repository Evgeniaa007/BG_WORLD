package ru.dorogova.bg_world.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;


/**
 * Аспект UserActionAspect предоставляет механизм отслеживания и логирования
 * действий пользователя в методах, отмеченных аннотацией TrackUserAction.
 */
@Aspect
@Component
@Slf4j //логирование в ломбоке
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    /**
     *Метод trackBGActions выполняется вокруг методов, отмеченных аннотацией TrackActionsWithBoardGames,
     *для логирования информации о действиях с настольными играми.
     *@param joinPoint Объект ProceedingJoinPoint для выполнения соединенной точки (вызываемого метода).
     *@return Результат выполнения вызываемого метода.
     *@throws Throwable Исключение, которое может быть брошено при выполнении соединенной точки.
     */
    @Around("@annotation(ru.dorogova.bg_world.aspects.TrackActionsWithBoardGames)")
    public Object trackBGActions(ProceedingJoinPoint joinPoint) throws Throwable {

        // Получение названия, класса  и аргументов в вызываемых методах
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] methodArgs = joinPoint.getArgs();


        String ANSI_RESET = "\u001B[0m"; // ANSI Escape Code для сброса цвета
        String myColorStart = "\u001B[43;30m";  // Черный текст на жёлтом фоне
        String myColor1 = "\u001B[32m";


        // Логирование начала вызываемого метода
        // с помощью класса Logger
        logger.info(myColorStart + "Метод: " + methodName + " в классе: " + className + ANSI_RESET +
                myColor1 + " вызван c аргументами" + Arrays.toString(methodArgs) + ANSI_RESET);

        // Замер времени работы метода
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // выполнение вызываемого метода
        long elapsedTime = System.currentTimeMillis() - start;

        // Логирование завершения вызываемого метода
        // с помощью Lombok Slf4j
        log.info(myColor1 + "Метод {} выполнился за время {} mc с результатом {}",methodName, elapsedTime, result + ANSI_RESET);
        return result;
    }
}
