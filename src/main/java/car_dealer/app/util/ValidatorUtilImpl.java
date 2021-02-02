package car_dealer.app.util;

import car_dealer.app.util.contract.ValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    public ValidatorUtilImpl(){
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T object) {
        return this.validator.validate(object).size() == 0;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T object) {
        return this.validator.validate(object);
    }

    @Override
    public <T> boolean ifNotValidPrintViolations(T object) {
        if(this.isValid(object)){
            return false;
        }else {
            for (ConstraintViolation<T> violation : this.violations(object)) {
                System.out.println(violation.getMessage());
            }
            return true;
        }
    }


}
