package lotto.controller;

import java.util.List;
import lotto.domain.lotto.Lottery;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.price.Price;
import lotto.domain.quantity.Quantity;
import lotto.support.factory.LottoFactory;
import lotto.view.LottoViewHandler;

public class LottoController {

    private final LottoFactory lottoFactory;
    private final LottoViewHandler lottoViewHandler;

    public LottoController(final LottoFactory lottoFactory, final LottoViewHandler lottoViewHandler) {
        this.lottoFactory = lottoFactory;
        this.lottoViewHandler = lottoViewHandler;
    }

    public void process() {
        Lottery lottery = createLottery();
        lottoViewHandler.showLotteryReport(lottery);
    }

    private Lottery createLottery() {
        List<Lotto> lottos = purchaseLottos();
        Lotto winningLotto = lottoViewHandler.promptWinningLotto();
        LottoNumber bonusNumber = lottoViewHandler.promptBonusNumber(winningLotto);
        return new Lottery(winningLotto, bonusNumber, lottos);
    }

    private List<Lotto> purchaseLottos() {
        Price price = lottoViewHandler.promptPrice();
        Quantity quantity = lottoFactory.createQuantity(price);
        lottoViewHandler.showQuantity(quantity);

        List<Lotto> lottos = lottoFactory.createLottos(quantity);
        lottoViewHandler.showLottos(lottos);
        return lottos;
    }
}
