package com.example.FilmReview.util.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Implementierung des ConstraintValidators für die Validierung des Passworts.
 */
public class ValidPasswordImpl implements ConstraintValidator<ValidPassword, String> {
    private Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
    private Pattern digitPattern = Pattern.compile("[0-9]");
    
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher specialCharMatcher = specialCharPattern.matcher(value);
        Matcher digitMatcher = digitPattern.matcher(value);
        return specialCharMatcher.find() && digitMatcher.find();
    }
}