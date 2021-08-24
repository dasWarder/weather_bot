package by.itechart.weather_bot.command;

import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class NoCommand implements Command {

    private final SendBotMessageService messageService;

    public static final String NO_MESSAGE = "I can run commands start with /.\n" +
            "Send /help for more information about commands";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
