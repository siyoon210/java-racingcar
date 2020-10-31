package racingcar;

import java.util.Scanner;

public class ConsoleInputDevice implements InputDevice {
    private final Scanner scanner;

    public ConsoleInputDevice() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int getIntNumber() {
        return scanner.nextInt();
    }
}
