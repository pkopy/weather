package pl.pkopy.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pkopy.weather.models.ListOfWeather;
import pl.pkopy.weather.models.services.Page;
import pl.pkopy.weather.models.services.WeatherService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private String city;
    private WeatherService weatherService;

    @Autowired
    public MainController(WeatherService weatherService) {

        this.weatherService = weatherService;
    }


    @GetMapping("/")
    public String indexGet() {
        return "index";
    }

    @PostMapping("/")

    public String index(@RequestParam("city") String city, Model model) {
        this.city = city;

        if(city == ""){
            return "index";
        }

        List<ListOfWeather> weathers = weatherService.makeCall(city);

//        model.addAttribute("weathers", weathers);

        return "redirect:/0";
    }

    @GetMapping("/{idWeather}")

    public String getPartOfWeather(@PathVariable("idWeather") int idWeather,
                                   Model model) {
        List<ListOfWeather> weathers;

        weathers = weatherService.getListOfWeathers();
        if (weathers == null) {
            return "index";
        }
        List<Page> pagination = new ArrayList<>();

        int amountOfPages = (weathers.size() / 6) + 1;
        for (int i = 0; i < amountOfPages; i++) {
            pagination.add(new Page(i));
        }

        List<ListOfWeather> partOfWeather = new ArrayList<>();
        for (int i = idWeather * 6; i < 6 + (idWeather * 6); i++) {
            if (i < weathers.size()) {
                partOfWeather.add(weathers.get(i));
            }
        }

        model.addAttribute("weathers", partOfWeather);
        model.addAttribute("pages", pagination);
        model.addAttribute("city", city.toUpperCase());
        model.addAttribute("idWeather", idWeather);

        return "index";
    }
}
