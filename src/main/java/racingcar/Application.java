package racingcar;

import racingcar.controllers.RaceController;
import racingcar.models.RacingCar;
import racingcar.models.RacingCars;

public class Application {
    public static void main(String[] args) {
        RaceController raceController = new RaceController();
        RacingCars cars = raceController.entry();

        if (cars != null)
            for (RacingCar car : cars.get()) {
                System.out.println(car.getName());
            }
    }
}
