package servise;

import servise.AccuWeatherClient;

import java.io.IOException;

import static servise.AccuWeatherClient.getForecastFirstDay;
import static servise.AccuWeatherClient.getForecastForFiveDays;

public class Controller {

    public void onUserInput(String result) throws IOException {

        switch (result) {
            case "1":
                getForecastFirstDay();
                break;
            case "5":
                getForecastForFiveDays();
                break;
            default:
                System.out.println("\u001B[96mОшибка ввода. Пожалуйста - выберите один из предложенных выше вариантов\u001B[0m");
        }
    }
}

