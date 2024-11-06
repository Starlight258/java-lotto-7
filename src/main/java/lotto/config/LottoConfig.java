package lotto.config;

import lotto.controller.LottoController;
import lotto.exception.handler.ExceptionHandler;
import lotto.support.LottoFactory;
import lotto.support.converter.Converter;
import lotto.support.splitter.Splitter;
import lotto.view.LottoViewHandler;
import lotto.view.input.ConsoleInputView;
import lotto.view.input.InputView;
import lotto.view.output.ConsoleOutputView;
import lotto.view.output.OutputView;

public class LottoConfig {

    private final String delimiter;

    public LottoConfig(final String delimiter) {
        this.delimiter = delimiter;
    }

    public LottoController createController() {
        OutputView outputView = new ConsoleOutputView();
        InputView inputView = new ConsoleInputView();
        LottoFactory lottoFactory = createLottoFactory();
        LottoViewHandler lottoView = createLottoViewHandler(inputView, outputView, lottoFactory);
        return new LottoController(lottoFactory, lottoView);
    }

    private LottoFactory createLottoFactory() {
        return new LottoFactory(new Converter(), new Splitter(delimiter));
    }

    private LottoViewHandler createLottoViewHandler(final InputView inputView, final OutputView outputView,
                                                    final LottoFactory lottoFactory) {
        return new LottoViewHandler(inputView, outputView, new ExceptionHandler(outputView), lottoFactory);
    }
}
