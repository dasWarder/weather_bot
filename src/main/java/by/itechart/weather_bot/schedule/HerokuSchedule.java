package by.itechart.weather_bot.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HerokuSchedule {

    @Scheduled(cron = "* * 1 * * *")
    public void scheduleToBot() {

        try {
            URL link = new URL("http://google.com");
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.connect();
            log.info("Fake successful connection with google.com");
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
