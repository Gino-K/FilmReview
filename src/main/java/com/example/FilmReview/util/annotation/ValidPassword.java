package com.example.FilmReview.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation zur Validierung von Passwörtern.
 * Das Passwort muss mindestens ein Sonderzeichen und eine Zahl enthalten.
 */
@Documented
@Constraint(validatedBy = ValidPasswordImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Das Passwort muss mindestens ein Sonderzeichen und eine Zahl enthalten.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
