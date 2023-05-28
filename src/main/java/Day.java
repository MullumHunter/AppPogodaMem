import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Day {
    @Override
    public String toString() {
        return " " + IconPhrase + "\n" +
                "Осадки днем - " + HasPrecipitation;
    }

    @JsonProperty(value = "HasPrecipitation")
    private boolean HasPrecipitation;

    @JsonProperty(value = "IconPhrase")
    private String IconPhrase;
}
