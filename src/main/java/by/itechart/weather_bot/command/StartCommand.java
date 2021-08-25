package by.itechart.weather_bot.command;

import by.itechart.weather_bot.service.bot.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.util.botUtil.BotUtil.getChatIdFromUpdate;

@RequiredArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService messageService;

    private final static String DIVIDER = "\n\n_________________________\n\n";

    public final static String START_MESSAGE = "Hi! I'm a weather bot. My goal is to help you to get actual weather for your city. \n" +
            "Send /help to see a list of all commands, that I know. \n\n" +
            "Before we start, please select your language.\n" +
            "<strong>Send <i>/ru</i> or <i>/eng</i></strong>";

    public final static String START_MESSAGE_RU = "Привет! Я бот погоды. Моя задача помочь тебе узнать актуальную погоду в твоем городе. \n" +
            "Отправь /help для того, чтобы увидеть полный список команд, которые я знаю. \n\n" +
            "Перед началом, пожалуйста выбери свой язык.\n" +
            "<strong>Отправь <i>/ru</i> или <i>/eng</i></strong>";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(getChatIdFromUpdate(update), START_MESSAGE + DIVIDER + START_MESSAGE_RU);
    }
}
