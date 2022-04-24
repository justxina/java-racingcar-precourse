package racingcar.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import jdk.jfr.Description;

public class Racing {

    private final RacingTurn turnCount;
    private final RacingCars cars;
    private final RaceRecords records;

    public Racing(List<Car> cars, RacingTurn racingTurn) {
        this.cars = new RacingCars(cars);
        this.turnCount = racingTurn;
        this.records = new RaceRecords();
    }

    @Description("전체 턴의 레이스가 이루어짐")
    public void race() {
        for (int turn = 1; turn <= this.turnCount.get(); turn += 1) {
            this.race(turn);
        }
    }

    @Description("한 턴의 레이스가 이루어짐")
    private void race(int turn) {
        for (RacingCar car: this.cars.get()) {
            this.records.add(
                new RaceRecord(turn, car, car.race())
            );
        }
    }

    @Description("레이싱 종료 여부")
    public boolean isEnded() {
        return (this.records.count() == (this.turnCount.get() * this.cars.get().size()));
    }

    @Description("우승 레이싱카 목록")
    public List<RacingCar> getWinners() {
        if (!isEnded())
            throw new IllegalStateException("아직 레이스가 끝나지 않아, 우승자를 알 수 없어요.");

        HashMap<RacingCar, Integer> stepsByCarName = getStepsByRacingCar();
        int maxStep = Collections.max(stepsByCarName.values());

        List<RacingCar> winners = new ArrayList<>();
        for (Entry<RacingCar, Integer> record : stepsByCarName.entrySet()) {
            this.setWinnerNames(winners, record, maxStep);
        }
        return winners;
    }


    private HashMap<RacingCar, Integer> getStepsByRacingCar() {
        HashMap<RacingCar, Integer> stepsByCarName = new HashMap<>();
        for (RacingCar car : this.cars.get()) {
            stepsByCarName.put(car, this.records.getStepsByCarName(car.getName()));
        }
        return stepsByCarName;
    }

    private void setWinnerNames(List<RacingCar> winners, Entry<RacingCar, Integer> record, int maxStep) {
        if (record.getValue() == maxStep) {
            winners.add(record.getKey());
        }
    }


}
