package rules;

import dto.CreateBook;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidBookValidator implements ConstraintValidator<ValidBook, CreateBook> {



    @Override
    public boolean isValid(CreateBook createBook, ConstraintValidatorContext context) {
        if (createBook == null || createBook.title() == null || createBook.title().isEmpty()) {
            return true;
        }

        boolean valid = true;
        context.disableDefaultConstraintViolation();

        if (createBook.title().equalsIgnoreCase(createBook.author())) {
            context.buildConstraintViolationWithTemplate("Title must not be the same as author")
                    .addPropertyNode("title")
                    .addConstraintViolation();
            valid = false;
        }

        if (!Character.isUpperCase(createBook.title().charAt(0))) {
            context.buildConstraintViolationWithTemplate("Title must start with an uppercase letter")
                    .addPropertyNode("title")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
//    @Override
//    public void initialize(ValidBook constraintAnnotation) {
//
//    }