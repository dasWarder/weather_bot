package by.itechart.weather_bot.service.weather;

import by.itechart.weather_bot.dto.ForecastWeather;
import by.itechart.weather_bot.dto.weatherapi.WeatherApiWeather;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.mapping.WeatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static by.itechart.weather_bot.config.AppConfig.FORECAST_CACHE;
import static by.itechart.weather_bot.config.AppConfig.WEATHER_CACHE;
import static by.itechart.weather_bot.util.ValidateUtil.validateObject;

@Slf4j
@Service
public class WeatherApiForecastService implements ForecastService {

    @Value("${api.weatherapi.key}")
    private String API_KEY;

    private final WeatherMapper mapper;

    private final RestTemplate restTemplate;

    private static final String BASE_URI = "http://api.weatherapi.com/v1/forecast.json?key=%s&q=%s&days=%s&aqi=no&alerts=no";

    @Autowired
    public WeatherApiForecastService(RestTemplate restTemplate, WeatherMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(WEATHER_CACHE)
    public ForecastWeather getWeatherForecast(String city, String days) throws NotValidException {
        validateObject(city, days);
        log.info("Receive 3 days forecast for city = {}", city);

        String uri = String.format(BASE_URI, API_KEY, city, days);
        WeatherApiWeather forecastApiResponse = restTemplate.getForObject(uri, WeatherApiWeather.class);
        ForecastWeather responseDto = mapper.fromWeatherApiWeatherToForecastWeather(forecastApiResponse);

        return responseDto;
    }
}
