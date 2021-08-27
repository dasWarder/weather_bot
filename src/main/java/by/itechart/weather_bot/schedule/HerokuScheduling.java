package by.itechart.weather_bot.schedule;

import by.itechart.weather_bot.bot.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class HerokuScheduling {

    @Autowired
    private BotConfig botConfig;

    @Scheduled(cron = "* 10 * * * *")
    public void ping() {
        try {
            botConfig.execute(new SendMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
