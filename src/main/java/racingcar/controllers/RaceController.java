package racingcar.controllers;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Description;
import racingcar.common.exceptions.IllegalArgumentException;
import racingcar.common.exceptions.IllegalStateException;
import racingcar.models.Car;
import racingcar.models.Racing;
import racingcar.models.RacingCars;
import racingcar.models.RacingTurn;

public class RaceController {

    public RaceController() {}


    @Description("사용자에게 자동차 이름을 정확하게 입력할 때까지 입력받음")
    public RacingCars entry() {
        RacingCars cars = null;
        while (cars == null) {
            cars = entryCars();
        }
        return cars;
    }

    @Description("사용자에게 레이싱 경기의 턴 수를 정확하게 입력할 때까지 입력받음")
    public RacingTurn enterRacingTurn() {
        RacingTurn turn = null;
        while (turn == null) {
            turn = this.enterTurnCount();
        }
        return turn;
    }


    @Description("사용자에게 자동차 이름을 입력받고, 이름이 올바르지 않을 때는 유효성 검사 결과를 알려줌")
    private RacingCars entryCars() {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            return new RacingCars(mapToCars(Console.readLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Description("사용자에게 레이싱 경기 턴 수를 입력받고, 턴 수가 올바르지 않을 때는 유효성 검사 결과를 알려줌")
    private RacingTurn enterTurnCount() {
        try {
            System.out.println("시도할 회수는 몇회인가요?");
            return new RacingTurn(Console.readLine());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static List<Car> mapToCars(String carNames) {
        List<Car> cars = new ArrayList<>();
        for (String name : carNames.split(",")) {
            cars.add(new Car(name));
        }
        return cars;
    }

}
