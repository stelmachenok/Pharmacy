package by.samsolution.pharmacy.formvalidator;

import by.samsolution.pharmacy.dto.MedicamentDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MedicamentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MedicamentDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        MedicamentDto medicamentDto = (MedicamentDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brandName", "NotEmpty.medicament.brandName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "activeIngredient", "NotEmpty.medicament.activeIngredient");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dosage", "NotEmpty.medicament.dosage");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "internationalNonproprietaryName", "NotEmpty.medicament.internationalNonproprietaryName");

        if (medicamentDto.getDosage() != null && medicamentDto.getDosage() < 0) {
            errors.rejectValue("dosage", "Pattern.medicament.negativeDosage");
        }
    }
}
