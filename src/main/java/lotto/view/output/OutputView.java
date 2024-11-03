package lotto.view.output;

import java.math.BigDecimal;
import lotto.domain.LottoNumberDto;

public interface OutputView {
    void showCommentForPurchasePrice();

    void showQuantity(final BigDecimal quantity);

    void showCommentForWinningLotto();

    void showCommentForBonusNumber();

    void showCommentForWinningResult();

    void showCommentForMatchingCount(int matchingCount);

    void showWinningResultForSecond(BigDecimal award, BigDecimal count);

    void showWinningResult(BigDecimal award, BigDecimal count);

    void showProfitRate(BigDecimal profitRate);

    void showLotto(LottoNumberDto numbers);
}