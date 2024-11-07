package lotto.domain.lotto;

import static lotto.exception.constants.ExceptionMessage.INVALID_BONUS_NUMBER;
import static lotto.exception.constants.ExceptionMessage.INVALID_LOTTO_RANGE;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import lotto.exception.argument.lotto.InvalidLottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final List<LottoNumber> CACHE;

    static {
        CACHE = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .mapToObj(LottoNumber::new)
                .toList();
    }

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(final int number) {
        validateRange(number);
        return CACHE.get(number - 1);
    }

    public static LottoNumber makeBonusNumber(final int number, final Lotto lotto) {
        validateRange(number);
        validateContains(number, lotto);
        return CACHE.get(number - 1);
    }

    private static void validateRange(final int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumberException(INVALID_LOTTO_RANGE.getMessage(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    private static void validateContains(final int number, final Lotto lotto) {
        if (lotto.contains(number)) {
            throw new InvalidLottoNumberException(INVALID_BONUS_NUMBER.getMessage());
        }
    }

    @Override
    public int compareTo(final LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
