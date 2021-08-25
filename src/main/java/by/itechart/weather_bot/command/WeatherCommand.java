package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static by.itechart.weather_bot.command.CommandName.GET_WEATHER;
import static by.itechart.weather_bot.util.botUtil.BotUtil.*;

@Slf4j
public class WeatherCommand extends AbstractWeatherCommand{

    public static final String WEATHER_MESSAGE_ENG = "<b> Current weather for city %s: </b> \n" +
                                                                        "Temperature: %d&#176;С, " +
                                                                        "but feels like: %d&#176;С \n" +
                                                                        "Amount of precipitations: %d \n" +
                                                                        "Wind: %d \n" +
                                                                        "Amount of sun: %d \n";

    public static final String WEATHER_MESSAGE_RU = "<b> Текущая погода для города %s: </b> \n" +
                                                                    "Температура: %d&#176;С, " +
                                                                    ", но ощущается как: %d&#176;С \n" +
                                                                    "Количество осадков: %d \n" +
                                                                    "Ветер: %d \n" +
                                                                    "Количество солнечного света: %d \n";

    public WeatherCommand(BotConfig botConfig, SendBotMessageService messageService, WeatherService weatherService) {
        super(botConfig, messageService, weatherService);
    }

    @Override
    public void execute(Update update) {

        String chatId = getChatIdFromUpdate(update);
        String message = getMessageFromUpdate(update);
        String command = message.split(" ")[0];
        String city = message.split(" ")[1];

        if(command.equalsIgnoreCase(GET_WEATHER.getCommandName())) {

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
            Weather currentWeather = weatherService.getCurrentWeather(city);

            return String.format(localeResponse, city, currentWeather.getTemperature(),
                                                       currentWeather.getFeelsLike(), currentWeather.getPrecip(),
                                                       currentWeather.getWind(), currentWeather.getSunIndex());
        } catch (NotValidException e) {
            String localeResponse = selectLocationLanguage(locale,
                                                                CITY_NOT_FOUND_RU, CITY_NOT_FOUND_ENG);
            return String.format(localeResponse, city);
        }
    }
}
