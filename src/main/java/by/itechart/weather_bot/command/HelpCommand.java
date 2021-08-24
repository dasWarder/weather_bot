package by.itechart.weather_bot.command;

import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.command.CommandName.*;


@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final SendBotMessageService messageService;

    public static final String HELP_MESSAGE = String.format("<b>Possible commands:</b>\n\n" +
            "%s - get help \n" +
            "%s - start work with me\n" +
            "%s city_name - get current weather for city",
            HELP.getCommandName(), START.getCommandName(), GET_WEATHER.getCommandName());

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
