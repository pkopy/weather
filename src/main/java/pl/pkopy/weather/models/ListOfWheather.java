package pl.pkopy.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListOfWheather {
    @JsonProperty("list")
    private MainWeather mainWeather;
    private String dt;

}
