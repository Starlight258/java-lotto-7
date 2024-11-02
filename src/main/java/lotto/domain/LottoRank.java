package lotto.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public enum LottoRank {

    FIRST(6, BigDecimal.valueOf(2_000_000_000)),
    SECOND(5, BigDecimal.valueOf(30_000_000)),
    THIRD(5, BigDecimal.valueOf(1_500_000)),
    FOURTH(4, BigDecimal.valueOf(50_000)),
    FIFTH(3, BigDecimal.valueOf(5_000)),
    NON_MATCH(0, BigDecimal.ZERO);

    private final int matchingCount;
    private final BigDecimal award;

    LottoRank(final int matchingCount, final BigDecimal award) {
        this.matchingCount = matchingCount;
        this.award = award;
    }

    public static LottoRank findRank(final int matchingCount, final boolean isBonus) {
        if (matchingCount == LottoRank.THIRD.matchingCount && !isBonus) {
            return LottoRank.THIRD;
        }
        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank.matchingCount == matchingCount)
                .findFirst()
                .orElse(NON_MATCH);
    }

    public BigDecimal getAward() {
        return award;
    }
}
