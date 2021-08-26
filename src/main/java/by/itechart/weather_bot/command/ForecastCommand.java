package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.dto.ForecastWeather;
import by.itechart.weather_bot.exception.CityNotFoundException;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.ForecastService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.stream.Collectors;

import static by.itechart.weather_bot.command.AbstractWeatherCommand.CITY_NOT_FOUND_ENG;
import static by.itechart.weather_bot.command.AbstractWeatherCommand.CITY_NOT_FOUND_RU;
import static by.itechart.weather_bot.command.CommandName.FORECAST;
import static by.itechart.weather_bot.util.ValidateUtil.validateObject;
import static by.itechart.weather_bot.util.botUtil.BotUtil.*;

@Slf4j
public class ForecastCommand implements Command {

    private final BotConfig botConfig;

    private final SendBotMessageService messageService;

    private final ForecastService forecastService;

    public static final String FORECAST_MESSAGE_ENG = "<b><u>Forecast for <i>%s</i>: </u></b> \n\n" +
                                                        "Max. temperature: <strong>%d &#8451;</strong> \n" +
                                                        "Min. temperature: <strong>%d &#8451;</strong> \n" +
                                                        "Avg. temperature: <strong>%d &#8451;</strong> \n" +
                                                        "Max. wind: <strong>%d mS</strong> \n" +
                                                        "Amount of precipitations: <strong>%d &#13212; &#9729;</strong> \n" +
                                                        "Avg. humidity: <strong>%d%c</strong> \n" +
                                                        "Chance of rain: <strong>%d &#9730;</strong> \n" +
                                                        "Amount of sun: <strong>%d &#9728;</strong> \n";

    public static final String FORECAST_MESSAGE_RU = "<b><u>Прогноз погоды на <i>%s</i>:</u></b> \n\n" +
                                                        "Максимальная температура: <strong>%d &#8451;</strong> \n" +
                                                        "Минимальная температура: <strong>%d &#8451;</strong> \n" +
                                                        "Средняя температура: <strong>%d &#8451;</strong> \n" +
                                                        "Максимальный ветер: <strong>%d мС</strong> \n" +
                                                        "Количество осадков: <strong>%d &#13212; &#9729;</strong> \n" +
                                                        "Средняя влажность: <strong>%d%c</strong> \n" +
                                                        "Вероятность дождя: <strong>%d &#9730;</strong> \n" +
                                                        "Количество солнечного света: <strong>%d &#9728;</strong> \n";

    public ForecastCommand(BotConfig botConfig, SendBotMessageService messageService, ForecastService forecastService) {
        this.botConfig = botConfig;
        this.messageService = messageService;
        this.forecastService = forecastService;
    }

    @Override
    public void execute(Update update) {

        log.info("Try to create forecast request to forecastService and return a response");

        String chatId = getChatIdFromUpdate(update);
        String message = getMessageFromUpdate(update);
        String[] slicedMessage = message.split(" ");
        String days = String.valueOf(3);
        String city = null;

        if(!isCommandValid(slicedMessage, chatId, botConfig, messageService)) {
            return;
        }

        if(slicedMessage.length == 3) {
            days = slicedMessage[1];
            city = slicedMessage[2];
        } else if (slicedMessage.length == 2) {
            city = slicedMessage[1];
        }

        String command = slicedMessage[0];


        if(command.equalsIgnoreCase(FORECAST.getCommandName())) {

            String responseMessage = formulateResponseMessage(city, days);
            messageService.sendMessage(chatId, responseMessage);

        } else {
            messageService.sendMessage(chatId, UnknownCommand.UNKNOWN_MESSAGE_RU);
        }
    }

    private String formulateResponseMessage(String city, String days) {

        Locale locale = botConfig.getLocale();

        char percent = 045;

        try {

            String localeResponse = selectLocationLanguageMessage(locale,
                                                                FORECAST_MESSAGE_RU, FORECAST_MESSAGE_ENG);
            ForecastWeather weatherForecast = forecastService.getWeatherForecast(city, days);
            validateObject(weatherForecast, weatherForecast.getForecastDayDtos());

            String daysForecast = weatherForecast.getForecastDayDtos().stream()
                    .map(dto -> String.format(localeResponse, dto.getLocalDate(), dto.getMaxTemp(),
                            dto.getMinTemp(), dto.getAvgTemp(), (int) (dto.getMaxWind() * 0.27778),
                            dto.getTotalPrecip(), dto.getAvgHumidity(), percent,
                            dto.getChanceOfRain(), dto.getSunIndex()))
                    .collect(Collectors.joining("\n\n"));


            return daysForecast;

        } catch (NotValidException | CityNotFoundException e) {
            String localeResponse = selectLocationLanguageMessage(locale,
                                                                CITY_NOT_FOUND_RU, CITY_NOT_FOUND_ENG);
            return String.format(localeResponse, city);
        }
    }
}
