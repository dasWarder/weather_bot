package by.itechart.weather_bot.bot;


import by.itechart.weather_bot.command.CommandContainer;
import by.itechart.weather_bot.mapping.WeatherMapper;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.bot.SendBotMessageServiceImpl;
import by.itechart.weather_bot.service.weather.WeatherService;
import by.itechart.weather_bot.service.weather.WeatherStackService;
import by.itechart.weather_bot.util.botUtil.BotUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static by.itechart.weather_bot.command.CommandName.NO;
import static by.itechart.weather_bot.util.botUtil.BotUtil.getMessageFromUpdate;

@Component
public class BotConfig extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.user}")
    private String username;

    public static String COMMAND_PREFIX = "/";

    private WeatherService weatherService;

    private CommandContainer commandContainer;

    private SendBotMessageService messageService;

    @Getter
    @Setter
    private Locale locale = new Locale("us");

    @Autowired
    public BotConfig(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.messageService = new SendBotMessageServiceImpl(this);
        this.commandContainer = new CommandContainer(this.messageService, this.weatherService, this);
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
            String message = getMessageFromUpdate(update);
            if(message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
