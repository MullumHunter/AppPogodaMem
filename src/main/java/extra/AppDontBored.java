package extra;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.IdeaResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class AppDontBored {
    private static final String ERROR_GENERATE_IDEA_OF_DAY = "Не удалось получить идею дня, справляйтесь сами =)";

    public static String getIdea() {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("www.boredapi.com")
                .addPathSegments("api")
                .addPathSegments("activity")
                .build();

        Request request = new Request.Builder().url(url).build();
        ObjectMapper objectMapper = new ObjectMapper();

        try (ResponseBody responseBody = client.newCall(request).execute().body()) {
            if (responseBody == null) {
                System.out.println("Нет ответа");
                return ERROR_GENERATE_IDEA_OF_DAY;
            }

            String json = responseBody.string();
            IdeaResponse responseIdea = objectMapper.readValue(json, IdeaResponse.class);
            return responseIdea + "\n" + "---------------------------";
        } catch (Exception e) {
            return ERROR_GENERATE_IDEA_OF_DAY;
        }
    }
}