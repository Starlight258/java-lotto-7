package lotto.support.factory;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.price.Price;
import lotto.domain.quantity.Quantity;
import lotto.util.converter.Converter;
import lotto.support.splitter.Splitter;

public class LottoFactory {

    private final Splitter splitter;

    public LottoFactory(final Splitter splitter) {
        this.splitter = splitter;
    }

    public Quantity createQuantity(Price price) {
        return price.calculateQuantity();
    }

    public Price createPrice(String input) {
        return new Price(Converter.convertToBigDecimal(input));
    }

    public List<Lotto> createLottos(final Quantity quantity) {
        return Lotto.createMultipleLottos(quantity);
    }

    public Lotto createWinningLotto(String input) {
        List<String> numbers = splitter.split(input);
        return new Lotto(Converter.convertToInteger(numbers));
    }

    public LottoNumber createBonusNumber(final String inputBonusNumber, final Lotto lotto) {
        return LottoNumber.makeBonusNumber(Converter.convertToInteger(inputBonusNumber), lotto);
    }
}
