package by.itechart.weather_bot.mapping;

import by.itechart.weather_bot.dto.DriverWeather;
import by.itechart.weather_bot.dto.ForecastDayDto;
import by.itechart.weather_bot.dto.ForecastWeather;
import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.dto.weatherapi.Forecast;
import by.itechart.weather_bot.dto.weatherapi.ForecastDay;
import by.itechart.weather_bot.dto.weatherapi.WeatherApiWeather;
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


    @Mapping(target = "localDate", source = "localDate")
    @Mapping(target = "maxTemp", source = "day.maxTemp")
    @Mapping(target = "minTemp", source = "day.minTemp")
    @Mapping(target = "avgTemp", source = "day.avgTemp")
    @Mapping(target = "maxWind", source = "day.maxWind")
    @Mapping(target = "sunIndex", source = "day.sunIndex")
    @Mapping(target = "totalPrecip", source = "day.totalPrecip")
    @Mapping(target = "avgHumidity", source = "day.avgHumidity")
    @Mapping(target = "chanceOfRain", source = "day.chanceOfRain")
    ForecastDayDto fromForecastDayToForecastDayDto(ForecastDay forecastDay);

    @Mapping(target = "forecastDayDtos", source = "forecast.forecastDays")
    ForecastWeather fromWeatherApiWeatherToForecastWeather(WeatherApiWeather weather);
}
