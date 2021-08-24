package by.itechart.weather_bot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {

    private Integer temperature;

    private Long humidity;
}
