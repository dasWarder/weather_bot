package by.itechart.weather_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastWeather {

    private Double maxTemp;

    private Double minTemp;

    private Double avgTemp;

    private Double maxWind;

    private Double totalPrecip;

    private Double avgHumidity;

    private Integer chanceOfRain;

    private Double sunIndex;
}
