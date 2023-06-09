package servise;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CityResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class AppCityGet {
    public static String getCityResult(String sity) throws IOException {
        if (sity == null) {
            throw new IllegalArgumentException("Город не может быть null");
        }

        if (sity == null) {
            throw new IllegalArgumentException("Город не может быть null");
        }
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("locations/v1/cities/search")
                .addQueryParameter("apikey", "9GixShszNLSlKHGqgNtm558TVJ8Xb8hR")
                .addQueryParameter("q", sity)
                .build();

        Request request = new Request.Builder().url(url).build();

        ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            throw new IOException("Нет ответа");
        }

        String json = responseBody.string();
        ObjectMapper objectMapper = new ObjectMapper();


        CityResponse[] cityResponses = objectMapper.readValue(json, CityResponse[].class);
        if (cityResponses.length == 0) {
            throw new IOException("Не удалось получить ключ города");
        }
        CityResponse firstCityResponse = cityResponses[0];


        AccuWeatherClient.setCityKey(firstCityResponse.getKey());

        return firstCityResponse.getKey();

    }
}
