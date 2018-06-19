package pl.pkopy.weather.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainWeather {
    private double temp;
    private double pressure;
    private Long humidity;
    private String icon;
}
