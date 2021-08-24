package by.itechart.weather_bot.command;

public enum CommandName {

    START("/start"),
    HELP("/help"),
    NO("/"),
    GET_WEATHER("/weather");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName(){
        return commandName;
    }
}
