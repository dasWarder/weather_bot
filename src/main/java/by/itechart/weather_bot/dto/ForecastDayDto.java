package by.itechart.weather_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDayDto {

    private LocalDate localDate;

    private Integer maxTemp;

    private Integer minTemp;

    private Integer avgTemp;

    private Integer maxWind;

    private Integer totalPrecip;

    private Integer avgHumidity;

    private Integer chanceOfRain;

    private Integer sunIndex;
}
