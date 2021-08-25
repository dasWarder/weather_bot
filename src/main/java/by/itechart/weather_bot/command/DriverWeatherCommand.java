package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.dto.DriverWeather;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static by.itechart.weather_bot.command.CommandName.GET_DRIVER_WEATHER;
import static by.itechart.weather_bot.util.botUtil.BotUtil.*;

@Slf4j
public class DriverWeatherCommand extends AbstractWeatherCommand {

    public static final String WEATHER_MESSAGE_ENG = "<b> Current weather for city %s: </b> \n" +
            "Temperature: %d&#176;С \n" +
            "Amount of precipitations: %d \n" +
            "Wind: %d \n" +
            "Humidity: %d \n" +
            "Visibility: %d \n" +
            "Amount of sun: %d \n";

    public static final String WEATHER_MESSAGE_RU = "<b> Текущая погода для города %s: </b> \n" +
            "Температура: %d&#176;С \n" +
            "Количество осадков: %d \n" +
            "Ветер: %d \n" +
            "Влажность: %d \n" +
            "Видимость: %d \n" +
            "Количество солнечного света: %d \n";

    public DriverWeatherCommand(BotConfig botConfig, SendBotMessageService messageService, WeatherService weatherService) {
        super(botConfig, messageService, weatherService);
    }

    @Override
    public void execute(Update update) {

        String chatId = getChatIdFromUpdate(update);
        String message = getMessageFromUpdate(update);
        String command = message.split(" ")[0];
        String city = message.split(" ")[1];

        if(command.equalsIgnoreCase(GET_DRIVER_WEATHER.getCommandName())) {

            String responseMessage = formulateResponseMessage(city);
            messageService.sendMessage(chatId, responseMessage);

        } else {
            messageService.sendMessage(chatId, UnknownCommand.UNKNOWN_MESSAGE_RU);
        }
    }

    private String formulateResponseMessage(String city) {

        Locale locale = botConfig.getLocale();

        try {

            String localeResponse = selectLocationLanguage(locale,
                                                                 WEATHER_MESSAGE_RU, WEATHER_MESSAGE_ENG);
            DriverWeather currentDriverWeather = weatherService.getCurrentDriverWeatherInfo(city);

            return String.format(localeResponse, city, currentDriverWeather.getTemperature(),
                                                        currentDriverWeather.getPrecip(), currentDriverWeather.getWind(),
                                                        currentDriverWeather.getHumidity(), currentDriverWeather.getVisibility(),
                                                        currentDriverWeather.getSunIndex());

        } catch (NotValidException e) {

            String localeResponse = selectLocationLanguage(locale,
                                                                 CITY_NOT_FOUND_RU, CITY_NOT_FOUND_ENG);
            return String.format(localeResponse, city);

        }
    }
}
