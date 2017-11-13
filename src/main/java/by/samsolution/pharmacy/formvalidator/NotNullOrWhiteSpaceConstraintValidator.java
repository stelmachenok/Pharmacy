package by.samsolution.pharmacy.formvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullOrWhiteSpaceConstraintValidator implements ConstraintValidator<NotNullOrWhiteSpace, String> {

    @Override
    public void initialize(NotNullOrWhiteSpace notNullOrWhiteSpace) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.trim().isEmpty();
    }
}
