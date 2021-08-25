package by.itechart.weather_bot.dto.weatherstack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Current implements Serializable {

    @JsonProperty("temperature")
    private Integer temperature;

    @JsonProperty("wind_speed")
    private Integer wind;

    @JsonProperty("precip")
    private Integer precip;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonProperty("feelslike")
    private Integer feelsLike;

    @JsonProperty("uv_index")
    private Integer sunIndex;

    @JsonProperty("visibility")
    private Integer visibility;
}
