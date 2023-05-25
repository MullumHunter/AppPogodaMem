import com.fasterxml.jackson.databind.JsonNode;

public class WeatherObject {
    private String[] dates;
    private String[] dayIconPhrases;
    private String[] nightIconPhrases;
    private boolean[] hasDayPrecipitations;
    private boolean[] hasNightPrecipitations;
    private int[] minTemperatures;
    private int[] maxTemperatures;

    public WeatherObject(JsonNode forecasts) {
        int numDays = forecasts.size();
        this.dates = new String[numDays];
        this.dayIconPhrases = new String[numDays];
        this.nightIconPhrases = new String[numDays];
        this.hasDayPrecipitations = new boolean[numDays];
        this.hasNightPrecipitations = new boolean[numDays];
        this.minTemperatures = new int[numDays];
        this.maxTemperatures = new int[numDays];

        for (int i = 0; i < numDays; i++) {
            JsonNode forecast = forecasts.get(i);
            this.dates[i] = forecast.get("Date").asText();
            this.dayIconPhrases[i] = forecast.get("Day").get("IconPhrase").asText();
            this.nightIconPhrases[i] = forecast.get("Night").get("IconPhrase").asText();
            this.hasDayPrecipitations[i] = forecast.get("Day").get("HasPrecipitation").asBoolean();
            this.hasNightPrecipitations[i] = forecast.get("Night").get("HasPrecipitation").asBoolean();
            this.minTemperatures[i] = forecast.get("Temperature").get("Minimum").get("Value").asInt();
            this.maxTemperatures[i] = forecast.get("Temperature").get("Maximum").get("Value").asInt();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dates.length; i++) {
            sb.append("Дата: ").append(dates[i]).append("\n");
            sb.append("Погода днем: ").append(dayIconPhrases[i]).append("\n");
            sb.append("Погода ночью: ").append(nightIconPhrases[i]).append("\n");
            sb.append("Дождь днем: ").append(hasDayPrecipitations[i]).append("\n");
            sb.append("Дождь ночью: ").append(hasNightPrecipitations[i]).append("\n");
            sb.append("Min температура в сутках: ").append(minTemperatures[i]).append("\n");
            sb.append("Max температура в сутках: ").append(maxTemperatures[i]).append("\n");
            sb.append("---------------------------\n");
        }
        return sb.toString();
    }
}

