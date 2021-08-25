package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.util.botUtil.BotUtil;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static by.itechart.weather_bot.util.botUtil.BotUtil.getChatIdFromUpdate;

@RequiredArgsConstructor
public class EngCommand implements Command {

    private final BotConfig botConfig;

    private final SendBotMessageService messageService;

    public static final String ENG_LANGUAGE_MESSAGE = "Language was changed to <i><strong>ENGLISH</strong></i>";

    @Override
    public void execute(Update update) {
        botConfig.setLocale(new Locale("us"));
        messageService.sendMessage(getChatIdFromUpdate(update), ENG_LANGUAGE_MESSAGE);
    }
}
