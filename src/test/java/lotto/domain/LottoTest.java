package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import lotto.exception.InvalidLottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Nested
    @DisplayName("로또 다중 생성 테스트")
    class 로또_다중_생성_테스트 {

        @Test
        @DisplayName("구매 수량만큼 로또를 뽑는다")
        void 성공_생성() {
            // Given
            Quantity quantity = new Quantity(BigDecimal.TEN);
            NumberGenerator<Integer> generator = () -> List.of(1, 2, 3, 4, 5, 6);

            // When
            List<Lotto> lottos = Lotto.createAsMuchAs(quantity, generator);

            // Then
            assertThat(lottos).hasSize(10);
        }
    }

    @Nested
    @DisplayName("로또 자동 생성 테스트")
    class 로또_자동_생성_테스트 {

        @Test
        @DisplayName("로또를 생성한다")
        void 성공_자동생성() {
            // Given
            NumberGenerator<Integer> generator = () -> List.of(1, 2, 3, 4, 5, 6);

            // When
            Lotto lotto = new Lotto(generator);

            // Then
            assertThat(lotto).extracting("numbers").isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        }

        @Test
        @DisplayName("생성시 로또 번호는 정렬되어 저장된다")
        void 성공_자동생성_정렬() {
            // Given
            NumberGenerator<Integer> generator = () -> List.of(1, 9, 3, 7, 5, 6);

            // When
            Lotto lotto = new Lotto(generator);

            // Then
            assertThat(lotto).extracting("numbers").isEqualTo(List.of(1, 3, 5, 6, 7, 9));
        }
    }

    @Nested
    @DisplayName("로또 수동 생성 테스트")
    class 로또_수동_생성_테스트 {

        @Test
        @DisplayName("로또를 생성한다")
        void 성공_수동생성() {
            // Given

            // When
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // Then
            assertThat(lotto).extracting("numbers").isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        }

        @Test
        @DisplayName("생성시 로또 번호는 정렬되어 저장된다")
        void 성공_수동생성_정렬() {
            // Given

            // When
            Lotto lotto = new Lotto(List.of(1, 11, 3, 4, 10, 6));

            // Then
            assertThat(lotto).extracting("numbers").isEqualTo(List.of(1, 3, 4, 6, 10, 11));
        }

        @Test
        @DisplayName("로또 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
        void 실패_수동생성_범위X() {
            // Given

            // When & Then
            assertThatThrownBy(() -> new Lotto(List.of(91, 1, 3, 4, 5, 6)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .isExactlyInstanceOf(InvalidLottoException.class)
                    .hasMessageStartingWith("[ERROR] ")
                    .hasMessageContaining("로또 번호는 1 이상 45 이하여야 합니다");
        }

        @ParameterizedTest
        @MethodSource
        @DisplayName("로또 번호가 6개가 아닌 경우 예외가 발생한다")
        void 실패_수동생성_6개X(List<Integer> numbers) {
            // Given

            // When & Then
            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .isExactlyInstanceOf(InvalidLottoException.class)
                    .hasMessageStartingWith("[ERROR] ")
                    .hasMessageContaining("로또 번호는 중복되지 않은 6개의 숫자여야 합니다");
        }

        private static Stream<Arguments> 실패_수동생성_6개X() {
            return Stream.of(
                    Arguments.of(List.of(1)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
            );
        }

        @Test
        @DisplayName("로또 번호가 중복될 경우 예외가 발생한다")
        void 실패_수동생성_중복() {
            // Given

            // When & Then
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .isExactlyInstanceOf(InvalidLottoException.class)
                    .hasMessageStartingWith("[ERROR] ")
                    .hasMessageContaining("로또 번호는 중복되지 않은 6개의 숫자여야 합니다");
        }
    }
}
