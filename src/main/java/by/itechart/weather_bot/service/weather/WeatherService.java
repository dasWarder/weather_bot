package by.itechart.weather_bot.service.weather;

import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.exception.NotValidException;

public interface WeatherService {

    Weather getCurrentWeather(String city) throws NotValidException;
}
