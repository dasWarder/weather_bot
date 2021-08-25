package by.itechart.weather_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastWeather {

    List<ForecastDayDto> forecastDayDtos;
}
