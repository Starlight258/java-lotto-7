package lotto.domain.lotto;

import static lotto.domain.lotto.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static lotto.exception.constants.ExceptionMessage.INVALID_LOTTO_DUPLICATED;

import camp.nextstep.edu.missionutils.Randoms;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lotto.domain.quantity.Quantity;
import lotto.exception.argument.lotto.InvalidLottoException;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final TreeSet<LottoNumber> numbers;

    public Lotto(final List<Integer> numbers) {
        TreeSet<Integer> set = new TreeSet<>(numbers);
        validateUnique(set);
        this.numbers = toLottoNumbers(set);
    }

    public static List<Lotto> createMultipleLottos(Quantity quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (BigDecimal count = BigDecimal.ZERO; count.compareTo(quantity.getQuantity()) < 0;
             count = count.add(BigDecimal.ONE)) {
            lottos.add(new Lotto(generateRandomLottoNumbers()));
        }
        return lottos;
    }

    private static List<Integer> generateRandomLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_SIZE);
    }

    public int countMatchingNumber(final Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean doesMatchBonusNumber(final LottoNumber bonusNumber) {
        return numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public boolean contains(int number) {
        return numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.getNumber() == number);
    }

    private void validateUnique(final Set<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoException(INVALID_LOTTO_DUPLICATED.getMessage(LOTTO_SIZE));
        }
    }

    private TreeSet<LottoNumber> toLottoNumbers(final Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
