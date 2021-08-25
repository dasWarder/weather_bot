package by.itechart.weather_bot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverWeather implements Serializable {

    private Integer temperature;

    private Integer precip;

    private Integer wind;

    private Integer humidity;

    private Integer visibility;

    private Integer sunIndex;
}
