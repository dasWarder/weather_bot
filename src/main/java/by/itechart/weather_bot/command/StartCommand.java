package by.itechart.weather_bot.command;

import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService messageService;

    public final static String START_MESSAGE = "Hi! I'm a weather bot. My goal is to help you to get actual weather for your city. \n" +
            "Send /help to see a list of all commands, that I know.";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
