package lotto.view.output;

import java.math.BigDecimal;
import java.util.List;
import lotto.domain.lotto.LottoRank;

public interface OutputView {
    void showCommentForPrice();

    void showQuantity(BigDecimal quantity);

    void showCommentForWinningLotto();

    void showCommentForBonusNumber();

    void showCommentForWinningResults();

    void showWinningResult(LottoRank lottoRank, BigDecimal count);

    void showProfitRate(BigDecimal profitRate);

    void showLotto(List<Integer> numbers);

    void showException(String message);
}
