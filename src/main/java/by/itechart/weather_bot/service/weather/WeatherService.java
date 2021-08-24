package by.itechart.weather_bot.service.weather;

import by.itechart.weather_bot.dto.Weather;

public interface WeatherService {

    Weather getCurrentWeather(String city);
}
