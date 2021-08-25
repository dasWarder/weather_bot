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

    public static final String WEATHER_MESSAGE_ENG = "<b><u>Current weather for city %s:</u></b> \n\n" +
                                                    "Temperature: <strong>%s&#8451;</strong> \n" +
                                                    "Amount of precipitations: <strong>%d &#13212; &#9729;</strong> \n" +
                                                    "Wind: <strong>%d mS</strong> \n" +
                                                    "Humidity: <strong>%d%c</strong> \n" +
                                                    "Visibility: <strong>%d m</strong> \n" +
                                                    "Amount of sun: <strong>%d &#9728;</strong> \n";

    public static final String WEATHER_MESSAGE_RU = "<b><u>Текущая погода для города %s:</u></b> \n\n" +
                                                    "Температура: <strong>%s &#8451;</strong> \n" +
                                                    "Количество осадков: <strong>%d &#13212; &#9729;</strong> \n" +
                                                    "Ветер: <strong>%d мС</strong> \n" +
                                                    "Влажность: <strong>%d%c</strong> \n" +
                                                    "Видимость: <strong>%d м</strong> \n" +
                                                    "Количество солнечного света: <strong>%d &#9728;</strong> \n";

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
        char per = 045;

        try {

            String localeResponse = selectLocationLanguage(locale,
                                                                 WEATHER_MESSAGE_RU, WEATHER_MESSAGE_ENG);
            DriverWeather currentDriverWeather = weatherService.getCurrentDriverWeatherInfo(city);

            return String.format(localeResponse, city, currentDriverWeather.getTemperature(),
                                                        currentDriverWeather.getPrecip(), currentDriverWeather.getWind(),
                                                        currentDriverWeather.getHumidity(), per, currentDriverWeather.getVisibility(),
                                                        currentDriverWeather.getSunIndex());

        } catch (NotValidException e) {

            String localeResponse = selectLocationLanguage(locale,
                                                                 CITY_NOT_FOUND_RU, CITY_NOT_FOUND_ENG);
            return String.format(localeResponse, city);

        }
    }
}
