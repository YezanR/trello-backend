package com.yezan.trello.validator;

import com.yezan.trello.security.PasswordMatchable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements
        ConstraintValidator<PasswordMatches, PasswordMatchable> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(PasswordMatchable object, ConstraintValidatorContext constraintValidatorContext) {
        String password = object.getPassword();
        String matchedPassword = object.getMatchingPassword();
        return password.equals(matchedPassword);
    }
}
