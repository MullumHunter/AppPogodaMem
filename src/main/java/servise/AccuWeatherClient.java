package servise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.WeatherResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;

public class AccuWeatherClient {
    public static void getForecastForFiveDays() throws IOException {
        final String day = "5day/";

        List<WeatherResponse> forecast = getForecast(day);

        for (WeatherResponse weatherResponse : forecast) {
            System.out.println(weatherResponse);
        }
    }

    public static void getForecastFirstDay() throws IOException {
        final String day = "1day/";
        getForecast(day);

        List<WeatherResponse> forecast = getForecast(day);

        for (WeatherResponse weatherResponse : forecast) {
            System.out.println(weatherResponse);
        }
    }

    private static List<WeatherResponse> getForecast(String day) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/")
                .addPathSegments(day)
                .addPathSegment("2497181")
                .addQueryParameter("apikey", "zNg7A9gvOLDVq1paBvciXqYqVPKXj4WJ")
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            System.out.println("Нет ответа");
            return null;
        }

        String response = responseBody.string();
        return parseResponse(response);
    }

    private static List<WeatherResponse> parseResponse(String response) throws IOException {
        int indexTop = response.indexOf("[{\"Date\"");
        int indexDown = response.lastIndexOf("}");
        response = response.substring(indexTop, indexDown);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, new TypeReference<List<WeatherResponse>>() {
        });
    }
}
