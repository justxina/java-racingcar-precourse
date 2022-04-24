package racingcar;

import racingcar.controllers.RaceController;
import racingcar.models.RacingCar;
import racingcar.models.RacingCars;
import racingcar.models.RacingTurn;

public class Application {
    public static void main(String[] args) {
        RaceController raceController = new RaceController();

        RacingCars cars = raceController.entry();
        RacingTurn turn = raceController.enterRacingTurn();



        if (cars != null)
            for (RacingCar car : cars.get()) {
                System.out.println(car.getName());
            }
        System.out.println("Turn: " + turn.get());
    }
}
