package by.samsolution.pharmacy.formvalidator;

import by.samsolution.pharmacy.dto.CategoryDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryName", "NotEmpty.category.categoryName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.category.description");
    }
}
