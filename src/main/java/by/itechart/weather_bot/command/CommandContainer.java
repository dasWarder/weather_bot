package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.ForecastService;
import by.itechart.weather_bot.service.weather.WeatherService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import static by.itechart.weather_bot.command.CommandName.*;

@Slf4j
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService messageService, WeatherService weatherService,
                            BotConfig bot, ForecastService forecastService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService))
                .put(RU.getCommandName(), new RuCommand(bot, messageService))
                .put(ENG.getCommandName(), new EngCommand(bot, messageService))
                .put(HELP.getCommandName(), new HelpCommand(bot, messageService))
                .put(GET_WEATHER.getCommandName(), new WeatherCommand(bot, messageService, weatherService))
                .put(GET_DRIVER_WEATHER.getCommandName(), new DriverWeatherCommand(bot, messageService, weatherService))
                .put(FORECAST.getCommandName(), new ForecastCommand(bot, messageService, forecastService))
                .put(NO.getCommandName(), new NoCommand(bot, messageService))
                .build();
        unknownCommand = new UnknownCommand(bot, messageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        log.info("Retrieve a command with identifier = {}", commandIdentifier);

        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
