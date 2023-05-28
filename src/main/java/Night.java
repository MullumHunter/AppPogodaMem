import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Night {
    @Override
    public String toString() {
        return " " + IconPhrase + "\n" +
                "Осадки ночью - " + HasPrecipitation;
    }

    @JsonProperty(value = "HasPrecipitation")
    private boolean HasPrecipitation;

    @JsonProperty(value = "IconPhrase")
    private String IconPhrase;
}
