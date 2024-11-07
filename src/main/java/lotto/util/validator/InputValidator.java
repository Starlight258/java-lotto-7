package lotto.util.validator;

import java.util.List;
import lotto.exception.argument.validator.InvalidInputException;
import lotto.exception.constants.ExceptionMessage;

public class InputValidator {

    public static void validateNotNullOrBlank(final String input, final String fieldName) {
        if (input == null) {
            throw new InvalidInputException(ExceptionMessage.NULL_VALUE.getMessage(fieldName));
        }
        if (input.isBlank()) {
            throw new InvalidInputException(ExceptionMessage.BLANK_VALUE.getMessage(fieldName));
        }
    }

    public static void validateNotNullOrEmpty(final List<?> input, final String fieldName) {
        if (input == null) {
            throw new InvalidInputException(ExceptionMessage.NULL_VALUE.getMessage(fieldName));
        }
        if (input.isEmpty()) {
            throw new InvalidInputException(ExceptionMessage.EMPTY_VALUE.getMessage(fieldName));
        }
    }
}
