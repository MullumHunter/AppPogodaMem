package weather.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import weather.dto.WeatherResponse;

import java.io.IOException;
import java.util.List;

public class AccuWeatherClient {
    public static void getForecastForFiveDays() throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("forecasts/v1/daily/5day/")
                .addPathSegment("2515330")
                .addQueryParameter("apikey", "oNKdoT8URIEAwVWmmVTjmliuZe33GVif")
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

        int firstIndexBody = responseList.indexOf("[{\"Date\"");
        int lastIndexBody = responseList.lastIndexOf("}");
        responseList = responseList.substring(firstIndexBody, lastIndexBody);

        //TODO: Та самая волшебная строка которая тебе делает из Json СПИСОК !!!! СВЯТАЯ КОПИБАРА! СПИСОК !!!! ОБЪЕКТОВ))))
        List<WeatherResponse> weatherResponses = objectMapper.readValue(responseList, new TypeReference<List<WeatherResponse>>() {
        });

        for (WeatherResponse weatherResponse : weatherResponses) {
            System.out.println(weatherResponse);
        }

    }
}
