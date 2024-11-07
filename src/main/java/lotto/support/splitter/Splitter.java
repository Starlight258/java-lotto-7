package lotto.support.splitter;

import java.util.Arrays;
import java.util.List;
import lotto.util.validator.InputValidator;

public class Splitter {

    private static final String INPUT_TEXT = "입력 문자열";
    private static final int CONTAINS_EMPTY = -1;

    private final String delimiter;

    public Splitter(final String delimiter) {
        this.delimiter = delimiter;
    }

    public List<String> split(final String text) {
        InputValidator.validateNotNullOrBlank(text, INPUT_TEXT);
        return Arrays.asList(text.split(delimiter, CONTAINS_EMPTY));
    }
}
