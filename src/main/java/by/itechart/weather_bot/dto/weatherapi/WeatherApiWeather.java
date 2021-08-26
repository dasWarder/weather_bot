package by.itechart.weather_bot.dto.weatherapi;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class WeatherApiWeather implements Serializable {

    @JsonProperty("forecast")
    private Forecast forecast;
}
