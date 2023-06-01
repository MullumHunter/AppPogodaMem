package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeResponse {

    @Override
    public String toString(){
        return timeZone + "\n"+
                date + "(" + dayOfWeek + ")" + "\n" +
                time + ":" + milliSeconds + "mс";
    }

    @JsonProperty(value = "timeZone")
    private String timeZone;

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "dayOfWeek")
    private String dayOfWeek;

    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "milliSeconds")
    private String milliSeconds;
}
