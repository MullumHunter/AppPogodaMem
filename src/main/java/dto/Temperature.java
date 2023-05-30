package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)

public class Temperature {
    @Override
    public String toString() {
        return "+" + minimum + "°" + " " +
                "+" + maximum + "°";
    }
    @JsonProperty(value = "Minimum")
    private Minimum minimum;

    @JsonProperty(value = "Maximum")
    private Maximum maximum;
}


