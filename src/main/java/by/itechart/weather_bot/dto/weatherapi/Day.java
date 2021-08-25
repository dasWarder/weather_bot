package by.itechart.weather_bot.dto.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Day {

    @JsonProperty("maxtemp_c")
    private Integer maxTemp;

    @JsonProperty("mintemp_c")
    private Integer minTemp;

    @JsonProperty("avgtemp_c")
    private Integer avgTemp;

    @JsonProperty("maxwind_kph")
    private Integer maxWind;

    @JsonProperty("totalprecip_mm")
    private Integer totalPrecip;

    @JsonProperty("avghumidity")
    private Integer avgHumidity;

    @JsonProperty("daily_chance_of_rain")
    private Integer chanceOfRain;

    @JsonProperty("uv")
    private Integer sunIndex;
}
