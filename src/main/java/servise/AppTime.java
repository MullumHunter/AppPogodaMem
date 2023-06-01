package servise;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TimeResponse;
import okhttp3.*;

import java.io.IOException;

public class AppTime {
    public static void getTime() throws IOException {
        String latitude = "55.1";
        String longitude = "61.6";


        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("www.timeapi.io")
                .addPathSegment("api")
                .addPathSegment("Time")
                .addPathSegment("current")
                .addPathSegment("coordinate")
                .addQueryParameter("latitude", latitude)
                .addQueryParameter("longitude", longitude)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        ResponseBody response = client.newCall(request).execute().body();

        if (response == null) {
            System.out.println("Нет ответа");
            return;
        }

        String json = response.string();
        ObjectMapper mapper = new ObjectMapper();

        TimeResponse timeResponse = mapper.readValue(json, TimeResponse.class);

        System.out.println(timeResponse + "\n");
    }
}




