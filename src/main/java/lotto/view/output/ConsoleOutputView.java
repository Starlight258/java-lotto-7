package lotto.view.output;

import java.math.BigDecimal;
import java.util.List;
import lotto.domain.lotto.LottoRank;

public class ConsoleOutputView implements OutputView {

    @Override
    public void showCommentForPrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void showQuantity(final BigDecimal quantity) {
        System.out.println(System.lineSeparator() + quantity + "개를 구매했습니다.");
    }

    @Override
    public void showCommentForWinningLotto() {
        System.out.println(System.lineSeparator() + "당첨 번호를 입력해 주세요.");
    }

    @Override
    public void showCommentForBonusNumber() {
        System.out.println(System.lineSeparator() + "보너스 번호를 입력해 주세요.");
    }

    @Override
    public void showCommentForWinningResults() {
        System.out.println(System.lineSeparator() + "당첨 통계");
    }

    @Override
    public void showWinningResult(final LottoRank lottoRank, final BigDecimal count) {
        System.out.printf(lottoRank.format() + System.lineSeparator(), lottoRank.getMatchCount(),
                lottoRank.getAward(), count);
    }

    @Override
    public void showProfitRate(final BigDecimal profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

    @Override
    public void showLotto(final List<Integer> numbers) {
        System.out.println(numbers);
    }

    @Override
    public void showException(final String message) {
        System.out.println(message);
    }
}
