package pl.pkopy.weather.models.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private int id;

    public Page(int id){
        this.id = id;
    }
}
