package lotto.view;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.domain.lotto.Lottery;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoRank;
import lotto.domain.price.Price;
import lotto.domain.quantity.Quantity;
import lotto.exception.handler.ExceptionHandler;
import lotto.support.factory.LottoFactory;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoViewHandler {

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final LottoFactory lottoFactory;

    public LottoViewHandler(final InputView inputView, final OutputView outputView,
                            final ExceptionHandler exceptionHandler, final LottoFactory lottoFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.lottoFactory = lottoFactory;
    }

    public Price promptPrice() {
        return exceptionHandler.retryOn(() -> {
            outputView.showCommentForPrice();
            String input = inputView.readLine();
            return lottoFactory.createPrice(input);
        });
    }

    public Lotto promptWinningLotto() {
        return exceptionHandler.retryOn(() -> {
            outputView.showCommentForWinningLotto();
            String input = inputView.readLine();
            return lottoFactory.createWinningLotto(input);
        });
    }

    public LottoNumber promptBonusNumber(final Lotto lotto) {
        return exceptionHandler.retryOn(() -> {
            outputView.showCommentForBonusNumber();
            String input = inputView.readLine();
            return lottoFactory.createBonusNumber(input, lotto);
        });
    }

    public void showLotteryReport(final Lottery lottery) {
        outputView.showCommentForWinningResults();
        showWinningResults(lottery.getResults());
        showProfitRate(lottery.calculateProfitRate());
    }

    public void showQuantity(final Quantity quantity) {
        outputView.showQuantity(quantity.getQuantity());
    }

    public void showLottos(final List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            List<Integer> numbers = lotto.getNumbers().stream()
                    .map(LottoNumber::getNumber)
                    .toList();
            outputView.showLotto(numbers);
        });
    }

    private void showWinningResults(final Map<LottoRank, BigDecimal> results) {
        results.keySet().stream()
                .sorted(Comparator.comparing(LottoRank::getMatchCount))
                .forEach(lottoRank -> outputView.showWinningResult(lottoRank, results.get(lottoRank)));
    }

    private void showProfitRate(final BigDecimal profitRate) {
        outputView.showProfitRate(profitRate);
    }
}
