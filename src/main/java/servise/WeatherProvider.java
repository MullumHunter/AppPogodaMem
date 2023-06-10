package servise;

import java.io.IOException;

public  interface WeatherProvider {
    public  void getForecastForFiveDays() throws IOException;

    public void getForecastFirstDay() throws IOException;
}
