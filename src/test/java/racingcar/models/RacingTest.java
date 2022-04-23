package racingcar.models;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RacingTest {

    private Racing racing;
    private List<String> carNames = Arrays.asList("pobi", "crong", "honux");

    @BeforeEach
    void setUp() {
        List<Car> cars = new ArrayList<>();
        for (String name: carNames) {
            cars.add(new Car(name));
        }
        racing = new Racing(cars, "5");
    }

    @Test
    @DisplayName("모든 턴이 끝나면 레이스가 종료된다.")
    void end_when_completed_turns() {
        assertThat(racing.isEnded()).isFalse();
        racing.race();
        assertThat(racing.isEnded()).isTrue();
    }

    @Test
    @DisplayName("레이스가 종료되면 최종 우승자가 생긴다.")
    void get_winners_when_end() {
        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> {
                List<RacingCar> winners = racing.getWinners();
            })
            .withMessageMatching("아직 레이스가 끝나지 않아, 우승자를 알 수 없어요.");

        racing.race();
        assertThat(racing.getWinners().size()).isGreaterThan(0);
    }

}
