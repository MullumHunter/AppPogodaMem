package servise;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CityResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class AppCityService {
    private static final String API_KEY = GlobalStateApp.getInstance().getAPI_KEY();

    public static String getCityKey(String city_name) {

        try {
            OkHttpClient client = new OkHttpClient();
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host("dataservice.accuweather.com")
                    .addPathSegments("locations/v1/cities/search")
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("q", city_name)
                    .build();

            Request request = new Request.Builder().url(url).build();

            ResponseBody responseBody = client.newCall(request).execute().body();

            if (responseBody == null) {
                throw new IOException("Нет ответа");
            }

            String json = responseBody.string();
            ObjectMapper objectMapper = new ObjectMapper();

            CityResponse[] cityResponses = objectMapper.readValue(json, CityResponse[].class);

            if (cityResponses == null || cityResponses.length == 0) {
                throw new IOException("Не удалось получить ключ города");
            }
            CityResponse firstCityResponse = cityResponses[0];
            return firstCityResponse.getKey();

        } catch (IOException e) {
            System.out.println("\u001B[37mОшибка: исчерпан лимит запросов");
            System.exit(0);
        }
        // TODO: придумать другое решение, null возвращать плохая практика
        return null;
    }
}
