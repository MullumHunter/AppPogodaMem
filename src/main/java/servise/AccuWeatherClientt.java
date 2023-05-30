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

public class AccuWeatherClientt {
    public static void getForecastForFiveDays() throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/5day/")
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
            return;
        }

        String responseList = responseBody.string();
        ObjectMapper objectMapper = new ObjectMapper();


        int indexTop = responseList.indexOf("[{\"Date\"");
        int indexDown = responseList.lastIndexOf("}");
        responseList = responseList.substring(indexTop, indexDown);

        List<WeatherResponse> weatherResponses = objectMapper.readValue(responseList, new TypeReference<List<WeatherResponse>>() {
        });

        for (WeatherResponse weatherResponse : weatherResponses) {
            System.out.println(weatherResponse);
        }
    }

    public static void getForecastFirstDay() throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/1day/")
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
            return;
        }

        String response = responseBody.string();
        ObjectMapper objectMapper = new ObjectMapper();

        int indexTop = response.indexOf("[{\"Date\"");
        int indexDown = response.lastIndexOf("}");
        response = response.substring(indexTop, indexDown);


        List<WeatherResponse> weatherResponses = objectMapper.readValue(response, new TypeReference<List<WeatherResponse>>() {
        });

        for (WeatherResponse weatherResponse : weatherResponses) {
            System.out.println(weatherResponse);
        }
    }
}
