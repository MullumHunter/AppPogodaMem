package servise;

import java.util.Scanner;

import static extra.AppDontBored.getIdea;
import static servise.AppCityService.getCityKey;

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
                GlobalStateApp.getInstance().setCity(cityName);
//                 это не относится к получению ключа города, предлагаю посмотреть где заполнять параметр этот
//                String cityKey = getCityKey(cityName);
                checkController(result);

                // TODO: а разве это не было сделано при получении ключа города?
//                GlobalStateApp.getInstance().setCity(cityName);
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[96mОшибка : " + e.getMessage() + "\u001B[0m");
            }
        }
    }

    private void validateCityName(String cityName) {

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
    private void checkController(String result) {
        controller.onUserInput(result);
    }
}


