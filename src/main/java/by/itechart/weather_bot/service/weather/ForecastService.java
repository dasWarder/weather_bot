package by.itechart.weather_bot.service.weather;

import by.itechart.weather_bot.dto.ForecastWeather;
import by.itechart.weather_bot.exception.CityNotFoundException;
import by.itechart.weather_bot.exception.NotValidException;

public interface ForecastService {

    ForecastWeather getWeatherForecast(String city, String days) throws NotValidException, CityNotFoundException;
}
