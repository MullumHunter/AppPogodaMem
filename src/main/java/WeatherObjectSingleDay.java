import com.fasterxml.jackson.databind.JsonNode;

public class WeatherObjectSingleDay {
    private String date;
    private String dayIconPhrase;
    private String nightIconPhrase;
    private boolean hasDayPrecipitation;
    private boolean hasNightPrecipitation;
    private int minTemperature;
    private int maxTemperature;

    public WeatherObjectSingleDay(JsonNode forecast) {
        this.date = forecast.get("Date").asText();
        this.dayIconPhrase = forecast.get("Day").get("IconPhrase").asText();
        this.nightIconPhrase = forecast.get("Night").get("IconPhrase").asText();
        this.hasDayPrecipitation = forecast.get("Day").get("HasPrecipitation").asBoolean();
        this.hasNightPrecipitation = forecast.get("Night").get("HasPrecipitation").asBoolean();
        this.minTemperature = forecast.get("Temperature").get("Minimum").get("Value").asInt();
        this.maxTemperature = forecast.get("Temperature").get("Maximum").get("Value").asInt();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Дата: ").append(date).append("\n");
        sb.append("Погода днем: ").append(dayIconPhrase).append("\n");
        sb.append("Погода ночью: ").append(nightIconPhrase).append("\n");
        sb.append("Дождь днем: ").append(hasDayPrecipitation).append("\n");
        sb.append("Дождь ночью: ").append(hasNightPrecipitation).append("\n");
        sb.append("Min температура в сутках: ").append(minTemperature).append("\n");
        sb.append("Max температура в сутках: ").append(maxTemperature).append("\n");
        sb.append("---------------------------\n");
        return sb.toString();
    }
}
