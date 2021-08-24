package by.itechart.weather_bot.command;

import by.itechart.weather_bot.dto.Weather;
import by.itechart.weather_bot.exception.NotValidException;
import by.itechart.weather_bot.service.bot.SendBotMessageService;
import by.itechart.weather_bot.service.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import static by.itechart.weather_bot.command.CommandName.GET_WEATHER;

@Slf4j
public class WeatherCommand implements Command {

    private final SendBotMessageService messageService;

    private final WeatherService weatherService;

    public WeatherCommand(SendBotMessageService messageService, WeatherService weatherService) {
        this.messageService = messageService;
        this.weatherService = weatherService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        String command = message.split(" ")[0];
        String city = message.split(" ")[1];

        if(command.equalsIgnoreCase(GET_WEATHER.getCommandName())) {

            String responseMessage = formulateResponseMessage(city);

            messageService.sendMessage(chatId, responseMessage);
        } else {
            messageService.sendMessage(chatId, UnknownCommand.UNKNOWN_MESSAGE);
        }
    }

    private String formulateResponseMessage(String city) {
        try {

            Weather currentWeather = weatherService.getCurrentWeather(city);
            return String.format("<b> Current weather for city %s: </b> \n" +
                    "temperature: %d \n" +
                    "humidity: %d", city, currentWeather.getTemperature(), currentWeather.getHumidity());

        } catch (NotValidException e) {

            return String.format("<i> The city with name <b>%s</b> not found! Try again! </i>", city);
        }
    }
}
