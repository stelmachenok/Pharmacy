package by.samsolution.pharmacy.formvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DosageConstraintValidator implements ConstraintValidator<Dosage, Double> {

    @Override
    public void initialize(Dosage dosage) {

    }

    @Override
    public boolean isValid(Double dosage, ConstraintValidatorContext constraintValidatorContext) {
        if(dosage == null) {
            return false;
        }
        return dosage > 0;
    }
}
