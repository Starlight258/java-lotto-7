package lotto.exception.handler;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import lotto.view.output.OutputView;

public class ExceptionHandler {

    private final OutputView outputView;

    public ExceptionHandler(final OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T retryOn(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                outputView.showException(e);
            }
        }
    }
}
