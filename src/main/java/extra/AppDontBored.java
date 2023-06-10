package extra;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.IdeaResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class AppDontBored {
    public static String getIdea() throws IOException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("www.boredapi.com")
                .addPathSegments("api")
                .addPathSegments("activity")
                .build();

        Request request = new Request.Builder().url(url).build();

        ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            System.out.println("Нет ответа");
        }
        String json = responseBody.string();
        ObjectMapper objectMapper = new ObjectMapper();

        IdeaResponse responseIdea = objectMapper.readValue(json, IdeaResponse.class);
        return responseIdea + "\n"+"---------------------------";
    }
}
