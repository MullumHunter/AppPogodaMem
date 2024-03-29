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

public class AccuWeatherClient implements WeatherProvider {
    private static final String API_KEY = GlobalStateApp.getInstance().getAPI_KEY();

    public void getForecastForFiveDays() {
        final String day = "5day/";
        getForecast(day);
    }

    public void getForecastFirstDay() {
        final String day = "1day/";
        getForecast(day);
    }

    private static void getForecast(String day) {
        String sity = GlobalStateApp.getInstance().getCity();
        String cityKey = AppCityService.getCityKey(sity);
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/")
                .addPathSegments(day)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            ResponseBody responseBody = client.newCall(request).execute().body();

            if (responseBody == null) {
                System.out.println("Нет ответа");
                return;
            }

            String response = responseBody.string();
            List<WeatherResponse> weatherResponses = parseResponse(response);

            for (WeatherResponse weatherResponse : weatherResponses) {
                System.out.println(weatherResponse);
            }
        } catch (Exception e) {
            System.out.println("Не удалось получить прогноз.");
            e.printStackTrace();
        }
    }

    public static List<WeatherResponse> parseResponse(String response) throws IOException {
        int indexTop = response.indexOf("[{\"Date\"");
        int indexDown = response.lastIndexOf("}");
        try {
            response = response.substring(indexTop, indexDown);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Лимит запросов исчерпан");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, new TypeReference<>() {
        });
    }
}
