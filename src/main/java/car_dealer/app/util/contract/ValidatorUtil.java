package car_dealer.app.util.contract;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <T> boolean isValid(T object);

    <T> Set<ConstraintViolation<T>> violations(T object);

    <T> boolean ifNotValidPrintViolations(T object);
}
