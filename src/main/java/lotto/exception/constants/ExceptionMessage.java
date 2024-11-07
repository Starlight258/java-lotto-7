package lotto.exception.constants;

public enum ExceptionMessage {

    ERROR_PREFIX("[ERROR] "),
    NULL_VALUE("%s은(는) null일 수 없습니다."),
    BLANK_VALUE("%s은(는) 빈 문자열이거나 공백일 수 없습니다."),
    EMPTY_VALUE("%s은(는) 빈 문자열일 수 없습니다."),
    INVALID_NUMBER_FORMAT("유효한 정수형식이어야 합니다."),
    INVALID_BIGDECIMAL_FORMAT("유효한 실수형식이어야 합니다."),
    INVALID_LOTTO_STATUS("구매한 로또가 없습니다."),
    INVALID_LOTTO_DUPLICATED("로또는 중복되지 않은 %d개의 숫자여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 %d 이상 %d 이하여야 합니다."),
    INVALID_BONUS_NUMBER("보너스 번호가 로또에 포함되어서는 안됩니다.");

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String format(final String content) {
        return message + content;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }

    public String getMessage() {
        return message;
    }
}
