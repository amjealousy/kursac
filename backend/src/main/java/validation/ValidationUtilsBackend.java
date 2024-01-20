package validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class ValidationUtilsBackend {
    private final static ValidatorFactory factory=Validation.buildDefaultValidatorFactory();;
    private final static Validator validator =factory.getValidator();

    public  <T> Set<ConstraintViolation<T>> getValid(T get){
        Set<ConstraintViolation<T>> validatee = validator.validate(get);

        if (!validatee.isEmpty()) {
            for (ConstraintViolation<T> violation : validatee) {
                System.out.println("Validated Object "+violation.getMessage());

            }
            return validatee;
        }
        return null;
    }

    private static void checkMatches(String path,String opt) {

    }


}
