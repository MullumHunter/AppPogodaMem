

import servise.AccuWeatherClient;
import servise.AppDontBored;
import servise.AppTime;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        AppDontBored.getIdea();

        AppTime.getTime();

//        AccuWeatherClientt.getForecastForFiveDays();

        System.out.print("На один день");

        AccuWeatherClient.getForecastFirstDay();
    }
}
