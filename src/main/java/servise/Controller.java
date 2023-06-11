package servise;

import dto.Periods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Controller {
    private final WeatherProvider weatherProvider = new AccuWeatherClient();
    private final Map<Integer, Periods> variant = new HashMap<>();

    public Controller() {
        variant.put(1, Periods.ONE);
        variant.put(5, Periods.FIVE);
    }

    public void onUserInput(String result) throws IOException {
        int command = Integer.parseInt(result);

        try {
            switch (variant.get(command)) {
                case ONE:
                    weatherProvider.getForecastFirstDay();
                    break;
                case FIVE:
                    weatherProvider.getForecastForFiveDays();
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("\u001B[96mОшибка ввода. Пожалуйста - выберите один из предложенных выше вариантов\u001B[0m");
        }
    }
}

