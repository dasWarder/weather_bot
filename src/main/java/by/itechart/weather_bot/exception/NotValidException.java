package by.itechart.weather_bot.exception;

public class NotValidException extends Throwable {

    public NotValidException() {
    }

    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
