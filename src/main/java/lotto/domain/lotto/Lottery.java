package lotto.domain.lotto;

import static lotto.domain.price.Price.LOTTO_UNIT_PRICE;
import static lotto.exception.constants.ExceptionMessage.INVALID_LOTTO_STATUS;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import lotto.exception.state.InvalidStateException;

public class Lottery {

    private static final int PROFIT_DECIMAL_SCALE = 1;

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;
    private final List<Lotto> lottos;
    private final Map<LottoRank, BigDecimal> results;

    public Lottery(final Lotto winningLotto, final LottoNumber bonusNumber, final List<Lotto> lottos) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
        this.lottos = lottos;
        this.results = calculateWinningResults();
    }

    public BigDecimal calculateProfitRate() {
        if (lottos.isEmpty()) {
            throw new InvalidStateException(INVALID_LOTTO_STATUS.getMessage());
        }
        BigDecimal profit = calculateProfit();
        BigDecimal purchaseAmount = LOTTO_UNIT_PRICE.multiply(BigDecimal.valueOf(lottos.size()));
        return profit.divide(purchaseAmount)
                .multiply(BigDecimal.valueOf(100))
                .setScale(PROFIT_DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    private Map<LottoRank, BigDecimal> calculateWinningResults() {
        Map<LottoRank, BigDecimal> results = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            results.put(lottoRank, BigDecimal.ZERO);
        }
        for (Lotto lotto : lottos) {
            getRank(lotto).ifPresent(lottoRank ->
                    results.put(lottoRank, results.get(lottoRank).add(BigDecimal.ONE)));
        }
        return results;
    }

    private BigDecimal calculateProfit() {
        BigDecimal profit = BigDecimal.ZERO;
        for (Entry<LottoRank, BigDecimal> entry : results.entrySet()) {
            profit = profit.add(entry.getKey().getAward().multiply(entry.getValue()));
        }
        return profit;
    }

    private Optional<LottoRank> getRank(final Lotto lotto) {
        int matchingCount = lotto.countMatchingNumber(winningLotto);
        boolean isBonus = lotto.doesMatchBonusNumber(bonusNumber);
        return LottoRank.findRank(matchingCount, isBonus);
    }

    public Map<LottoRank, BigDecimal> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
