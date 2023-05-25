import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private final String filePath;

    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    public String readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), String.class);
    }
}
