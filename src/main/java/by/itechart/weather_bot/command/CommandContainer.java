package by.itechart.weather_bot.command;

import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.WeatherService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import static by.itechart.weather_bot.command.CommandName.*;

@Slf4j
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService messageService, WeatherService weatherService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService))
                .put(HELP.getCommandName(), new HelpCommand(messageService))
                .put(GET_WEATHER.getCommandName(), new WeatherCommand(messageService, weatherService))
                .put(NO.getCommandName(), new NoCommand(messageService))
                .build();
        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        log.info("Retrieve a command with identifier = {}", commandIdentifier);

        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
