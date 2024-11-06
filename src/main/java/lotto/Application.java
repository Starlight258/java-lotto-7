package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.config.LottoConfig;
import lotto.controller.LottoController;

public class Application {

    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        LottoConfig config = new LottoConfig(DELIMITER);
        LottoController lottoController = config.createController();
        try {
            lottoController.process();
        } finally {
            Console.close();
        }
    }
}
