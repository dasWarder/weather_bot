package by.itechart.weather_bot.util;

import by.itechart.weather_bot.exception.NotValidException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



public class ValidateUtil {

    public static <T> void validateObject(T ... objs) throws NotValidException {
        List<Boolean> nonValid = Arrays.stream(objs)
                                                    .map(obj -> Objects.nonNull(obj))
                                                    .filter(obj -> obj == false)
                                                    .collect(Collectors.toList());

        if(!nonValid.isEmpty()) {

            throw new NotValidException("One or several objects no valid [equals to NULL]");
        }
    }
}
