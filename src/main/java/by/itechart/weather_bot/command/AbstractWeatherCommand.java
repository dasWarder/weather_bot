package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.WeatherService;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractWeatherCommand implements Command {

    protected final BotConfig botConfig;

    protected final SendBotMessageService messageService;

    protected final WeatherService weatherService;

    public static final String CITY_NOT_FOUND_ENG = "<i> The city with name <b>%s</b> not found! Try again! </i>";

    public static final String CITY_NOT_FOUND_RU = "<i> Город с именем <b>%s</b> не найден! Попробуйте еще раз! </i>";

    public AbstractWeatherCommand(BotConfig botConfig, SendBotMessageService messageService, WeatherService weatherService) {
        this.botConfig = botConfig;
        this.messageService = messageService;
        this.weatherService = weatherService;
    }

    @Override
    public abstract void execute(Update update);
}
