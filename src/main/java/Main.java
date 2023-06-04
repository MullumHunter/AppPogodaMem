

import servise.AccuWeatherClient;
import servise.AppDontBored;
import servise.AppTime;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//        AppDontBored.getIdea();
//        AppTime.getTime();

        System.out.println("На пять дней");
        AccuWeatherClient.getForecastForFiveDays();

        System.out.println("На один день");
        AccuWeatherClient.getForecastFirstDay();
    }
}
