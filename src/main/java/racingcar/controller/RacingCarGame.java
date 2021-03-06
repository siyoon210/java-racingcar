package racingcar.controller;

import racingcar.domain.RacingCar;
import racingcar.dto.Input;
import racingcar.view.inputview.InputHandler;
import racingcar.view.resultview.ResultView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarGame {
    private final InputHandler inputHandler;
    private final ResultView resultView;

    public RacingCarGame(InputHandler inputHandler, ResultView resultView) {
        this.inputHandler = inputHandler;
        this.resultView = resultView;
    }

    public void start() {
        Input input = inputHandler.getInput();
        List<RacingCar> racingCars = input.toRacingCars();

        runRacing(input, racingCars);

        List<RacingCar> winners = getWinners(racingCars);
        resultView.printWinners(winners);
    }

    private void runRacing(Input input, List<RacingCar> racingCars) {
        validateNumberOfCountToTry(input);

        for (int i = 0; i < input.numberOfCountToTry; i++) {
            tryToMoveRacingCars(racingCars);
            resultView.printProgress(racingCars);
        }
    }

    private void validateNumberOfCountToTry(Input input) {
        if (input.numberOfCountToTry <= 0) {
            throw new IllegalStateException("The 'numberOfCountToTry' must be greater than 0.");
        }
    }

    private void tryToMoveRacingCars(List<RacingCar> racingCars) {
        for (RacingCar racingCar : racingCars) {
            racingCar.tryToMove();
        }
    }

    private List<RacingCar> getWinners(List<RacingCar> racingCars) {
        Collections.sort(racingCars);
        RacingCar winner = racingCars.get(0);
        return racingCars.stream()
                .filter(rc -> rc.isSameMoveCount(winner))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
