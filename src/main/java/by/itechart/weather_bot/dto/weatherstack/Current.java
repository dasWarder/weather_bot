package by.itechart.weather_bot.dto.weatherstack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Current implements Serializable {

    @JsonProperty("temperature")
    private Integer temperature;

    @JsonProperty("humidity")
    private Long humidity;
}
