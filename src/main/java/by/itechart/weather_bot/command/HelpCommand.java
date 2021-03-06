package by.itechart.weather_bot.command;

import by.itechart.weather_bot.bot.BotConfig;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.command.CommandName.*;
import static by.itechart.weather_bot.util.botUtil.BotUtil.*;


@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final BotConfig botConfig;

    private final SendBotMessageService messageService;

    public static final String HELP_MESSAGE_ENG = "<b><u>Possible commands:</u></b>\n\n" +
                                                        "%s - get help \n" +
                                                        "%s - select Russian language \n" +
                                                        "%s - select English language \n" +
                                                        "%s - receive start information \n" +
                                                        "%s <i>city_name</i> - get current weather for a city \n" +
                                                        "%s <i>city_name</i> - get drive weather for a city \n" +
                                                        "%s <i>city_name</i> - get forecast for 3 days for a city \n" +
                                                        "%s [<i>amount of days</i>] <i>city_name</i> - get forecast for N days(max 3) for a city \n";

    public static final String HELP_MESSAGE_RU = "<b>Возможные команды:</b>\n\n" +
                                        "%s - получить помощь \n" +
                                        "%s - выбрать русский язык \n" +
                                        "%s - выбрать английский язык \n" +
                                        "%s - получить стартовую информацию\n" +
                                        "%s <i>название_города</i> - получить актуальную погоду для выбранного города \n" +
                                        "%s <i>название_города</i> - получить водительский прогноз погоды для выбранного города \n" +
                                        "%s <i>название_города</i> - получить прогноз на 3 дня для выбранного города \n" +
                                        "%s [<i>количество дней</i>] <i>название_города</i> - получить прогноз на N дней(макс. 3) для выбранного города \n";

    @Override
    public void execute(Update update) {

        String localeResponse = selectLocationLanguageMessage(botConfig.getLocale(),
                                                                            HELP_MESSAGE_RU, HELP_MESSAGE_ENG);
        String response = String.format(localeResponse,
                                                   HELP.getCommandName(), RU.getCommandName(),
                                                   ENG.getCommandName(), START.getCommandName(),
                                                   GET_WEATHER.getCommandName(), GET_DRIVER_WEATHER.getCommandName(),
                                                   FORECAST.getCommandName(), FORECAST.getCommandName());

        messageService.sendMessage(getChatIdFromUpdate(update), response);
    }
}
