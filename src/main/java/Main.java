
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //проверка
        AccuWeatherClient.getForecastForFiveDays();

        JsonReader jsonReader = new JsonReader("pogoda.json");
        String json = jsonReader.readJson();

        //на 5 дней
        JsonNode weatherData = AccuWeatherClient.getWeatherData(json);
        WeatherObject weatherObject = new WeatherObject(weatherData);
        System.out.println(weatherObject);

        //на один день
        JsonNode weatherData2 = AccuWeatherClient.getWeatherData(json).get(0);
        WeatherObjectSingleDay weatherSingleDay = new WeatherObjectSingleDay(weatherData2);
        System.out.println(weatherSingleDay);
    }
}