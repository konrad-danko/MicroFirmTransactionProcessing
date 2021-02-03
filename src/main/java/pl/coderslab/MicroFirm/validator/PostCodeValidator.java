package pl.coderslab.MicroFirm.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostCodeValidator implements ConstraintValidator<PostCode, String> {

    @Override
    public void initialize(PostCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String customerPostCode, ConstraintValidatorContext context) {

        if ( customerPostCode == null || customerPostCode.equals("")) {
            return true;
        }
        return customerPostCode.matches("^\\d{2}\\-\\d{3}$");
    }
}
