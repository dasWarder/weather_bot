package by.itechart.weather_bot.command;

public enum CommandName {
    RU("/ru"),
    ENG("/eng"),
    START("/start"),
    HELP("/help"),
    NO("/"),
    GET_WEATHER("/weather"),
    GET_DRIVER_WEATHER("/drive");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName(){
        return commandName;
    }
}
