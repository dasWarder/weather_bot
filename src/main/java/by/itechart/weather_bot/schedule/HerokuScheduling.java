package by.itechart.weather_bot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class HerokuScheduling {

    private static final String REQUEST_URL = "https://google.com";

    @Scheduled(cron = "* 15 * * * *")
    public void ping() {
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            log.info("Ping connection to the {}", REQUEST_URL);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
