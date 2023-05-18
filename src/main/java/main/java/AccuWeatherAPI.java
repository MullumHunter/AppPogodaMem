package main.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class AccuWeatherAPI {
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("2515330")
                .addQueryParameter("apikey", "oNKdoT8URIEAwVWmmVTjmliuZe33GVif")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            System.out.println("Нет ответа");
            return;
        }

        String json = responseBody.string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        // Извлекаем нужные данные
        JsonNode dailyForecasts = root.get("DailyForecasts");
        if (dailyForecasts == null) {
            System.out.println("Превышен лимит, приходите завтра");
            return;
        }

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

    }
}