package main.java.main.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class AccuWeatherAPI {
    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("2515330");

        urlBuilder.addQueryParameter("apikey", "oNKdoT8URIEAwVWmmVTjmliuZe33GVif");

        HttpUrl finalUrl = urlBuilder.build();

//        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/2515330?apikey=oNKdoT8URIEAwVWmmVTjmliuZe33GVif";

        Request request = new Request.Builder()
                .url(finalUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);

                // Извлекаем нужные данные
                JsonNode dailyForecasts = root.get("DailyForecasts");
                if (dailyForecasts != null) {
                    for (JsonNode forecast : dailyForecasts) {
                        String date = forecast.get("Date").asText();
                        String dayIconPhrase = forecast.get("Day").get("IconPhrase").asText();
                        String nightIconPhrase = forecast.get("Night").get("IconPhrase").asText();
                        boolean hasDayPrecipitation = forecast.get("Day").get("HasPrecipitation").asBoolean();
                        boolean hasNightPrecipitation = forecast.get("Night").get("HasPrecipitation").asBoolean();
                        int minTemperature = (forecast.get("Temperature").get("Minimum").get("Value").asInt() - 32) * 5 / 9;
                        int maxTemperature = (forecast.get("Temperature").get("Maximum").get("Value").asInt() - 32) * 5 / 9;

                        System.out.println("Дата: " + date);
                        System.out.println("Погода днем: " + dayIconPhrase);
                        System.out.println("Погода ночью: " + nightIconPhrase);
                        System.out.println("Дождь днем: " + hasDayPrecipitation);
                        System.out.println("Дождь ночью: " + hasNightPrecipitation);
                        System.out.println("Min температура в сутках: " + minTemperature);
                        System.out.println("Max температура в сутках: " + maxTemperature);
                        System.out.println("---------------------------");
                    }
                } else {
                    System.out.println("Превышен лимит, приходите завтра");
                }
            } else {
                System.out.println("Нет ответа");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}