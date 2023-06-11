package servise;

import java.io.IOException;
import java.util.Scanner;

import static extra.AppDontBored.getIdea;
import static servise.AppCityGet.getCityResult;

public class UserInterface {
    private final Controller controller = new Controller();

    public void runApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\u001B[92m");
        System.out.println("Что бы выйти в любой момент введите: \"exit\"");

        while (true) {
            System.out.print("\u001B[38;5;206m");
            System.out.println("\n" + "Ваш город (используйте английский)" + "\n");
            String cityName = scanner.nextLine();

            checkIsExit(cityName);

            System.out.println("\n" + "Выбирете вариант прогноза: 1, 5" + "\n");
            String result = scanner.nextLine();

            checkIsExit(result);

            System.out.print("Выбранный город: ");
            System.out.print("\u001B[92m");
            System.out.println(cityName);
            System.out.print("\u001B[38;5;206m");

            try {
                validateCityName(cityName);
                getCityResult(cityName);
                checkController(result);
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[96mОшибка: " + e.getMessage() + "\u001B[0m");
            } catch (IOException e) {
                System.out.println("\u001B[96mНе удалось получить ключ города\u001B[0m");
            }
        }
    }

    private void validateCityName(String cityName) throws IllegalArgumentException {
        final String cityNamePattern = ".*\\d.*";

        if (cityName.matches(cityNamePattern)) {
            throw new IllegalArgumentException("Неверный формат названия города");
        }
    }

    private void checkIsExit(String result) {

        if (result.equalsIgnoreCase("exit")) {
            System.out.print("\u001B[0m");
            System.out.println(getIdea());
            System.out.print("\u001B[32m");
            System.out.println("Завершение программы" + "\n" + "Хорошего вам дня:)");
            System.exit(0);
        }
    }

    //TODO: fix exception
    private void checkController(String result) throws IOException {
        controller.onUserInput(result);
    }
}


