

import servise.AccuWeatherClientt;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AccuWeatherClientt.getForecastForFiveDays();

        System.out.println("На один день");
        System.out.println();
        
        AccuWeatherClientt.getForecastFirstDay();
    }
}
