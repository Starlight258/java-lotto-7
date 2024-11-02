package lotto.support;

import java.util.List;
import lotto.exception.InvalidConvertException;

public class IntegerConverter {

    public List<Integer> convertFrom(List<String> numbers) {
        try {
            return numbers.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException exception) {
            throw new InvalidConvertException("Integer 타입의 정수가 아닙니다");
        } catch (NullPointerException exception) {
            throw new InvalidConvertException("null일 수 없습니다");
        }
    }

    public int convertFrom(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new InvalidConvertException("Integer 타입의 정수가 아닙니다");
        } catch (NullPointerException exception) {
            throw new InvalidConvertException("null일 수 없습니다");
        }
    }
}
