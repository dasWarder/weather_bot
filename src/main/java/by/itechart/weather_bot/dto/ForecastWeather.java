package by.itechart.weather_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastWeather implements Serializable {

    List<ForecastDayDto> forecastDayDtos;
}
