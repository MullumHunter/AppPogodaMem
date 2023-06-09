package servise;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

import static servise.AccuWeatherClient.getForecastFirstDay;
import static servise.AccuWeatherClient.getForecastForFiveDays;
import static servise.AppCityGet.getCityResult;

public class UserInterface {
    private final Controller controller = new Controller();


    public void runApp() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("\u001B[38;5;206m");
            System.out.println("\n"+"Ваш город (используйте английский)" + "\n");
            String sity = scanner.nextLine();

            System.out.println("\n"+"Выбирете вариант прогноза: 1, 5 или exit."+"\n");
            String result = scanner.nextLine();

            checkIsExit(result);

            try {
                getCityResult(sity);
            } catch (IOException e) {
                System.out.println("\u001B[96mНе удалось получить ключ города\u001B[0m");
                continue;
            }

            try {
                cheсkController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkIsExit(String result) {
        if (result.equalsIgnoreCase("exit")) {
            System.out.print("\u001B[32m");
            System.out.println("Завершение программы" + "\n" + "Хорошего вам дня:)");
            System.exit(0);
        }
    }

    private void cheсkController(String result) throws IOException {
        controller.onUserInput(result);
    }
}

