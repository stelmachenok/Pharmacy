package by.samsolution.pharmacy.formvalidator;

import by.samsolution.pharmacy.dto.AvailabilityDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AvailabilityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AvailabilityDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AvailabilityDto availabilityDto = (AvailabilityDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "count", "NotEmpty.availability.count");



        if (availabilityDto.getCount() != null && availabilityDto.getCount() <= 0) {
            errors.rejectValue("count", "Pattern.availability.negativeCount");
        }
    }
}
