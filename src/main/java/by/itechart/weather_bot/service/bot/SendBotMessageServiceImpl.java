package by.itechart.weather_bot.service.bot;


import by.itechart.weather_bot.bot.BotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final BotConfig bot;

    @Override
    public void sendMessage(String chatId, String message) {
        log.info("Try to send a message for a chat with ID={}", chatId);

        SendMessage sendMessage = new SendMessage();

        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
