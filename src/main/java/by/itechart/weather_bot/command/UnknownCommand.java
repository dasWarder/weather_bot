package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.util.botUtil.BotUtil;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.util.botUtil.BotUtil.getChatIdFromUpdate;
import static by.itechart.weather_bot.util.botUtil.BotUtil.selectLocationLanguage;

@RequiredArgsConstructor
public class UnknownCommand implements Command {

    private final BotConfig botConfig;

    private final SendBotMessageService messageService;

    public static final String UNKNOWN_MESSAGE_ENG = "I don't figure you out! \n" +
            "Send /help for more information about commands";

    public static final String UNKNOWN_MESSAGE_RU = "Я не понимаю! \n" +
            "Отправьте /help для получения большей информации о командах";

    @Override
    public void execute(Update update) {
        String localeResponse = selectLocationLanguage(botConfig.getLocale(),
                                                                            UNKNOWN_MESSAGE_RU, UNKNOWN_MESSAGE_ENG);

        messageService.sendMessage(getChatIdFromUpdate(update), localeResponse);
    }
}
