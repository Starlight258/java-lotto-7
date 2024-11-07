package lotto.exception.handler;

import static lotto.exception.constants.ExceptionMessage.ERROR_PREFIX;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import lotto.exception.base.CustomIllegalArgumentException;
import lotto.exception.base.CustomIllegalStateException;
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
            } catch (CustomIllegalArgumentException | CustomIllegalStateException e) {
                outputView.showException(e.getMessage());
            } catch (RuntimeException e) {
                outputView.showException(ERROR_PREFIX.getMessage() + e.getMessage());
            }
        }
    }
}
