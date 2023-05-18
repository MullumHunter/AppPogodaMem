package main.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class TimeJSON {
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "https://www.timeapi.io/api/Time/current/coordinate?latitude=55.7&longitude=37.6";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);

                String timeZone = root.get("timeZone").asText();
                String dayOfWeek = root.get("dayOfWeek").asText();

                String year = root.get("year").asText();
                String monthdate = root.get("month").asText();
                String day = root.get("day").asText();

                String hour = root.get("hour").asText();
                String minute = root.get("minute").asText();
                String seconds = root.get("seconds").asText();

                System.out.println("timeZone :"+timeZone);
                System.out.println("dayOfWeek :"+dayOfWeek);

                System.out.println("---------------------------");

                System.out.println(year + " " + day + " " + monthdate);
                System.out.println(hour + ":" + minute + ":" + seconds);



            }
        }
    }
}



