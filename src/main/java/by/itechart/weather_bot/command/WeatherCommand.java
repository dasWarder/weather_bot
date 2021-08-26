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
import static by.itechart.weather_bot.command.UnknownCommand.UNKNOWN_MESSAGE_RU;
import static by.itechart.weather_bot.util.botUtil.BotUtil.*;

@Slf4j
public class WeatherCommand extends AbstractWeatherCommand{

    public static final String WEATHER_MESSAGE_ENG = "<b><u>Current weather for city %s:</u></b> \n\n" +
                                                                        "Temperature: <strong>%s &#8451;</strong>, " +
                                                                        "but feels like: <strong>%s &#8451;</strong> \n" +
                                                                        "Amount of precipitations: <strong>%d &#13212; &#9729;</strong> \n" +
                                                                        "Wind: <strong>%d mS</strong> \n" +
                                                                        "Amount of sun: <strong>%d &#9728;</strong> \n";

    public static final String WEATHER_MESSAGE_RU = "<b><u>Текущая погода для города %s:</u></b> \n\n" +
                                                                    "Температура: <strong>%s &#8451;</strong>, " +
                                                                    "но ощущается как: <strong>%s &#8451;</strong> \n" +
                                                                    "Количество осадков: <strong>%d &#13212; &#9729;</strong> \n" +
                                                                    "Ветер: <strong>%d мС</strong> \n" +
                                                                    "Количество солнечного света: <strong>%d &#9728;</strong> \n";

    public WeatherCommand(BotConfig botConfig, SendBotMessageService messageService, WeatherService weatherService) {
        super(botConfig, messageService, weatherService);
    }

    @Override
    public void execute(Update update) {

        String chatId = getChatIdFromUpdate(update);
        String message = getMessageFromUpdate(update);
        String[] slicedMessage = message.split(" ");

        if(!isCommandValid(slicedMessage, chatId, botConfig, messageService)) {
            return;
        }
        String command = slicedMessage[0];
        String city = slicedMessage[1];

        if(command.equalsIgnoreCase(GET_WEATHER.getCommandName())) {

            String responseMessage = formulateResponseMessage(city);
            messageService.sendMessage(chatId, responseMessage);

        } else {
            messageService.sendMessage(chatId, UNKNOWN_MESSAGE_RU);
        }
    }

    private String formulateResponseMessage(String city) {

        Locale locale = botConfig.getLocale();

        try {

            String localeResponse = selectLocationLanguageMessage(locale,
                                                                WEATHER_MESSAGE_RU, WEATHER_MESSAGE_ENG);
            Weather currentWeather = weatherService.getCurrentWeather(city);

            return String.format(localeResponse, city, currentWeather.getTemperature(),
                                                       currentWeather.getFeelsLike(), currentWeather.getPrecip(),
                                                       currentWeather.getWind(), currentWeather.getSunIndex());
        } catch (NotValidException e) {

            String localeResponse = selectLocationLanguageMessage(locale,
                                                                CITY_NOT_FOUND_RU, CITY_NOT_FOUND_ENG);
            return String.format(localeResponse, city);
        }
    }
}
