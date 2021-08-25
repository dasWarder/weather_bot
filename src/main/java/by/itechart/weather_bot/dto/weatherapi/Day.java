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
    private Double maxTemp;

    @JsonProperty("mintemp_c")
    private Double minTemp;

    @JsonProperty("avgtemp_c")
    private Double avgTemp;

    @JsonProperty("maxwind_kph")
    private Double maxWind;

    @JsonProperty("totalprecip_in")
    private Double totalPrecip;

    @JsonProperty("avghumidity")
    private Double avgHumidity;

    @JsonProperty("daily_chance_of_rain")
    private Integer chanceOfRain;

    @JsonProperty("uv")
    private Double sunIndex;
}
