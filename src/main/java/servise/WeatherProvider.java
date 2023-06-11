package servise;

import java.io.IOException;

public interface WeatherProvider {

    void getForecastForFiveDays() throws IOException;

    void getForecastFirstDay() throws IOException;
}
