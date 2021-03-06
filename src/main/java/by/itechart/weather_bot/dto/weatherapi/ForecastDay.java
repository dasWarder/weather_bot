package by.itechart.weather_bot.dto.weatherapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ForecastDay implements Serializable {

    @JsonProperty("date")
    private LocalDate localDate;

    @JsonProperty("day")
    private Day day;
}
