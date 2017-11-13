package by.samsolution.pharmacy.formvalidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {NotNullOrWhiteSpaceConstraintValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullOrWhiteSpace{
    String message() default "NotEmpty.medicament.brandName";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

