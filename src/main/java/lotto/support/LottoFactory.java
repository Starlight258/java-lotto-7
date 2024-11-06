package lotto.support;

import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.price.Price;
import lotto.domain.quantity.Quantity;
import lotto.support.converter.Converter;
import lotto.support.splitter.Splitter;

public class LottoFactory {

    private final Converter converter;
    private final Splitter splitter;

    public LottoFactory(final Converter converter, final Splitter splitter) {
        this.converter = converter;
        this.splitter = splitter;
    }

    public Quantity createQuantity(Price price) {
        return price.calculateQuantity();
    }

    public Price createPrice(String input) {
        return new Price(converter.convertToBigDecimal(input));
    }

    public List<Lotto> createLottos(final Quantity quantity) {
        return Lotto.createMultipleLottos(quantity);
    }

    public Lotto createWinningLotto(String input) {
        List<String> numbers = splitter.split(input);
        return new Lotto(converter.convertToInteger(numbers));
    }

    public LottoNumber createBonusNumber(final String inputBonusNumber, final Lotto lotto) {
        return LottoNumber.makeBonusNumber(converter.convertToInteger(inputBonusNumber), lotto);
    }
}
