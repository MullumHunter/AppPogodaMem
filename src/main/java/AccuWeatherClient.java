import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.File;
import java.io.IOException;

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
        Request request = new Request.Builder().url(url).build();
        okhttp3.ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            System.out.println("Нет ответа");
            return;
        }

        String json = responseBody.string();
        ObjectMapper objectMapper = new ObjectMapper();

        //TODO:
        // убрать работу с сохранением в файл
        // сделать ДВА метода, один на 1 день, второй на 5 дней
        // ниже пример как должно пройти преобразование ответа в Java Объект. НИКАКОГО РУЧНОГО ПАРСИНГА!
//        WeatherObject weatherObject = objectMapper.readValue(json, WeatherObject.class);
//        System.out.println(weatherObject);

        objectMapper.writeValue(new File("pogoda.json"), json);
        System.out.println(json);
    }



    public static JsonNode getWeatherData(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        return rootNode.get("DailyForecasts");
    }
}
