
import weather.service.AccuWeatherClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AccuWeatherClient.getForecastForFiveDays();
    }
}