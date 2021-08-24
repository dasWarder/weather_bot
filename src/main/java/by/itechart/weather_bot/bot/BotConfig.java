package by.itechart.weather_bot.bot;


import by.itechart.weather_bot.command.CommandContainer;
import by.itechart.weather_bot.mapping.WeatherMapper;
import by.itechart.weather_bot.service.bot.SendBotMessageServiceImpl;
import by.itechart.weather_bot.service.weather.WeatherService;
import by.itechart.weather_bot.service.weather.WeatherStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.command.CommandName.NO;

@Component
public class BotConfig extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.user}")
    private String username;

    public static String COMMAND_PREFIX = "/";

    private WeatherService weatherService;

    private CommandContainer commandContainer;

    @Autowired
    public BotConfig(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.commandContainer = new CommandContainer(
                new SendBotMessageServiceImpl(this), weatherService);
    }

    public BotConfig() {
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if(message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
