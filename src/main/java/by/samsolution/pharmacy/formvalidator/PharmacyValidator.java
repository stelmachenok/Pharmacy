package by.samsolution.pharmacy.formvalidator;

import by.samsolution.pharmacy.dto.PharmacyDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PharmacyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PharmacyDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PharmacyDto pharmacyDto = (PharmacyDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmacyName", "NotEmpty.pharmacy.pharmacyName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.pharmacy.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmacistName", "NotEmpty.pharmacy.pharmacistName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNumber", "NotEmpty.pharmacy.contactNumber");

        Pattern mobilePhonePattern = Pattern.compile("\\+375-(\\d){2}-((\\d){3})-(\\d){2}-(\\d){2}");
        Pattern homePhonePattern = Pattern.compile("8\\s(\\d){3}\\s((\\d){3})-(\\d){2}-(\\d){2}");
        Matcher mobilePhoneMatcher = mobilePhonePattern.matcher(pharmacyDto.getContactNumber());
        Matcher homePhoneMatcher = homePhonePattern.matcher(pharmacyDto.getContactNumber());
        if (!mobilePhoneMatcher.matches() && !homePhoneMatcher.matches()) {
            errors.rejectValue("contactNumber", "Pattern.pharmacy.incorrectContactNumber");
        }
    }
}
