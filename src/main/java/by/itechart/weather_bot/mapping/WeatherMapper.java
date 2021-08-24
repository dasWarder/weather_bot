package by.itechart.weather_bot.mapping;

import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.dto.weatherstack.WeatherStackWeather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WeatherMapper {

    @Mapping(target = "temperature", source = "current.temperature")
    @Mapping(target = "humidity", source = "current.humidity")
    Weather fromWeatherStackWeatherToWeather(WeatherStackWeather weather);
}
