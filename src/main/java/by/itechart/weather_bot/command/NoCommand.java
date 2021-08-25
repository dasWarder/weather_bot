package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.util.botUtil.BotUtil.getChatIdFromUpdate;
import static by.itechart.weather_bot.util.botUtil.BotUtil.selectLocationLanguage;

@RequiredArgsConstructor
public class NoCommand implements Command {

    private final BotConfig botConfig;

    private final SendBotMessageService messageService;

    public static final String NO_MESSAGE_ENG = "I can run commands start with /.\n" +
            "Send /help for more information about commands";

    public static final String NO_MESSAGE_RU = "Я могу выполнять команды начинающиеся с /.\n" +
            "Отправь /help для того, чтобы увидеть полный список команд, которые я знаю.";

    @Override
    public void execute(Update update) {
        String localeResponse = selectLocationLanguage(botConfig.getLocale(),
                                                                            NO_MESSAGE_RU, NO_MESSAGE_ENG);
        messageService.sendMessage(getChatIdFromUpdate(update), localeResponse);
    }
}
