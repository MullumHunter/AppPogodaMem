

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        AccuWeatherClientt.getForecastForFiveDays();

        System.out.println("На один день");
        System.out.println();
        
        AccuWeatherClientt.getForecastFirstDay();
    }
}
