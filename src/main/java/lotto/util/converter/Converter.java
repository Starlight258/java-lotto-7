package lotto.util.converter;

import static lotto.exception.constants.ExceptionMessage.INVALID_BIGDECIMAL_FORMAT;
import static lotto.exception.constants.ExceptionMessage.INVALID_NUMBER_FORMAT;

import java.math.BigDecimal;
import java.util.List;
import lotto.exception.argument.converter.InvalidConvertException;
import lotto.util.validator.InputValidator;

public class Converter {

    private static final String NUMBERS = "숫자 목록";
    private static final String NUMBER = "숫자";
    private static final String REAL_NUMBER = "실수";

    public static List<Integer> convertToInteger(List<String> numbers) {
        InputValidator.validateNotNullOrEmpty(numbers, NUMBERS);
        return numbers.stream()
                .map(Converter::convertToInteger)
                .toList();
    }

    public static int convertToInteger(final String input) {
        InputValidator.validateNotNullOrBlank(input, NUMBER);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new InvalidConvertException(INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    public static BigDecimal convertToBigDecimal(final String input) {
        InputValidator.validateNotNullOrBlank(input, REAL_NUMBER);
        try {
            return new BigDecimal(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidConvertException(INVALID_BIGDECIMAL_FORMAT.getMessage());
        }
    }
}
