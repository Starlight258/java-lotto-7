package lotto.exception.converter;

import lotto.exception.CustomIllegalArgumentException;

public class InvalidInputException extends CustomIllegalArgumentException {

    public InvalidInputException(final String message) {
        super(message);
    }
}
