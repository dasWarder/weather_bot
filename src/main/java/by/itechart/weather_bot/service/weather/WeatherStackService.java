package by.itechart.weather_bot.service.weather;

import by.itechart.weather_bot.dto.DriverWeather;
import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.dto.weatherstack.WeatherStackWeather;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.mapping.WeatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static by.itechart.weather_bot.config.AppConfig.DRIVE_CACHE;
import static by.itechart.weather_bot.config.AppConfig.WEATHER_CACHE;
import static by.itechart.weather_bot.util.ValidateUtil.validateObject;
import static org.apache.http.util.Asserts.notNull;

@Slf4j
@Service
public class WeatherStackService implements WeatherService {

    private final RestTemplate restTemplate;

    private final WeatherMapper mapper;

    @Value("${api.weatherstack.key}")
    private String API_KEY;

    private static final String BASE_URI = "http://api.weatherstack.com/current?access_key=%s&query=%s";

    @Autowired
    public WeatherStackService(RestTemplate restTemplate, WeatherMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(WEATHER_CACHE)
    public Weather getCurrentWeather(String city) throws NotValidException {
        notNull(city, "The city param must be not null");
        log.info("Receiving current weather for city = {}", city);

        WeatherStackWeather responseDto = getApiResponse(city);
        Weather weatherResponse = mapper.fromWeatherStackWeatherToWeather(responseDto);

        return weatherResponse;
    }

    @Override
    @Cacheable(DRIVE_CACHE)
    public DriverWeather getCurrentDriverWeatherInfo(String city) throws NotValidException {
        notNull(city, "The city param must be not null");
        log.info("Receiving current driver weather for city = {}", city);

        WeatherStackWeather responseDto = getApiResponse(city);
        DriverWeather driverWeatherResponse = mapper.fromWeatherStackWeatherToDriverWeather(responseDto);

        return driverWeatherResponse;
    }

    private WeatherStackWeather getApiResponse(String city) throws NotValidException {

        String requestUri = String.format(BASE_URI, API_KEY, city);

        WeatherStackWeather responseDto = restTemplate.getForObject(requestUri, WeatherStackWeather.class);
        validateObject(responseDto, responseDto.getCurrent());

        return responseDto;
    }
}
