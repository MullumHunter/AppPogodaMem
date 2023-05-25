import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.File;
import java.io.IOException;

public class ResponseBody {
    public static void main(String[] args) throws IOException {

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
        objectMapper.writeValue(new File("pogoda.json"), json);
        System.out.println(json);
    }

    static JsonNode getWeatherData(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        return rootNode.get("DailyForecasts");
    }
}
