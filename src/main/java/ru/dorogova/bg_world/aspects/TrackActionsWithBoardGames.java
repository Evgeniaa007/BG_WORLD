package ru.dorogova.bg_world.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация TrackActionsWithBoardGames используется для маркировки методов,
 * которые требуется отслеживать в контексте действий пользователя с настольными играми.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackActionsWithBoardGames {
}