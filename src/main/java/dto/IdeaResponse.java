package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdeaResponse {
    @Override
    public String toString(){
        return "Idea for you - " + activity + "\n" +
                "TYPE - " + type;
    }
   @JsonProperty(value = "activity")
    private  String activity;

    @JsonProperty(value = "type")
    private  String type;
}
