package by.itechart.weather_bot.util.botUtil;

import by.itechart.weather_bot.bot.BotConfig;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

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

    public static String selectLocationLanguage(Locale locale, String ruResponse, String engResponse) {
        String language = getLocale(locale);

        return language.equals("ru")?
                                    ruResponse : engResponse;
    }
}
