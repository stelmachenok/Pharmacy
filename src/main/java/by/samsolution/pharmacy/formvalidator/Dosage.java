package by.samsolution.pharmacy.formvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented

@Constraint(validatedBy = DosageConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dosage {
    String message() default "{Pattern.medicament.dosageNotNumber}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

