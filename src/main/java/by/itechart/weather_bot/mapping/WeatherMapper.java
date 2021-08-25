package by.itechart.weather_bot.mapping;

import by.itechart.weather_bot.dto.DriverWeather;
import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.dto.weatherstack.WeatherStackWeather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WeatherMapper {

    @Mapping(target = "wind", source = "current.wind")
    @Mapping(target = "precip", source = "current.precip")
    @Mapping(target = "sunIndex", source = "current.sunIndex")
    @Mapping(target = "feelsLike", source = "current.feelsLike")
    @Mapping(target = "temperature", source = "current.temperature")
    Weather fromWeatherStackWeatherToWeather(WeatherStackWeather weather);


    @Mapping(target = "wind", source = "current.wind")
    @Mapping(target = "precip", source = "current.precip")
    @Mapping(target = "humidity", source = "current.humidity")
    @Mapping(target = "sunIndex", source = "current.sunIndex")
    @Mapping(target = "visibility", source = "current.visibility")
    @Mapping(target = "temperature", source = "current.temperature")
    DriverWeather fromWeatherStackWeatherToDriverWeather(WeatherStackWeather weather);
}
