package by.itechart.weather_bot.util.botUtil;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static by.itechart.weather_bot.command.UnknownCommand.UNKNOWN_MESSAGE_ENG;
import static by.itechart.weather_bot.command.UnknownCommand.UNKNOWN_MESSAGE_RU;

public class BotUtil {

    public static String getMessageFromUpdate(Update update) {
        return update.getMessage()
                                .getText()
                                .trim();
    }

    public static String getChatIdFromUpdate(Update update) {
        return update.getMessage()
                                .getChatId()
                                .toString();
    }

    public static String getLocale(Locale locale) {
        return locale.getLanguage();
    }

    public static String selectLocationLanguageMessage(Locale locale, String ruResponse, String engResponse) {
        String language = getLocale(locale);

        return language.equals("ru")?
                                    ruResponse : engResponse;
    }

    public static boolean isCommandValid(String[] sliced, String chatId, BotConfig botConfig, SendBotMessageService messageService) {

        if(sliced.length < 2) {
            sendUnknownCommandMessage(botConfig, messageService, chatId);
            return false;
        }

        return true;
    }

    private static void sendUnknownCommandMessage(BotConfig bot, SendBotMessageService messageService, String chatId) {
        String unknownResponse = selectLocationLanguageMessage(bot.getLocale(),
                UNKNOWN_MESSAGE_RU, UNKNOWN_MESSAGE_ENG);
        messageService.sendMessage(chatId, unknownResponse);
    }
}
