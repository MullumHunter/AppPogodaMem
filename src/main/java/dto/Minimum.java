package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Minimum {

    @Override
    public String toString() {
        return ""+ value;
    }

    @JsonProperty(value = "Value")
    private int value;
}
