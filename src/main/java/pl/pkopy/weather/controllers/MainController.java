package pl.pkopy.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pkopy.weather.models.CityEntity;
import pl.pkopy.weather.models.ListOfWheather;
import pl.pkopy.weather.models.Repositories.CityRepository;
import pl.pkopy.weather.models.services.CityService;
import pl.pkopy.weather.models.services.Page;
import pl.pkopy.weather.models.services.WeatherService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private CityRepository cityRepository;
    private CityService cityService;
    private List<CityEntity> cities;
    private ListOfWheather listOfWheather;
    private WeatherService weatherService;
    @Autowired
    public MainController(CityRepository cityRepository, CityService cityService, WeatherService weatherService){
        this.cityService = cityService;
        this.cityRepository = cityRepository;
        this.weatherService = weatherService;
        cities  = new ArrayList<>();

    }

//    @GetMapping("/")
//    @ResponseBody
//    public String index(){
//            cities   = cityService.cos();
//        CityEntity cityEntity = new CityEntity();
//        cityEntity.setCountry("PL");
//        cityEntity.setName("POLSKA");
//
//
//        for(CityEntity city : cities){
//            if (city.getCountry().equals("PL")){
//                System.out.println("+");
//                cityRepository.save(city);
//            }
//        }

//        cityRepository.saveAll();

//        StringBuilder tekst = new StringBuilder();
////        for(CityEntity city : cities){
////            tekst.append("id: " + city.getIdCity() + " name: " + city.getName() + " country: " + city.getCountry() +"\n");
////        }
//
//        return"ok";
//    }

    @GetMapping
    public String indexGet(){
        return "index";
    }

    @PostMapping("/")

    public String index(@RequestParam("city") String city, Model model){

        List<ListOfWheather> wheathers = weatherService.makeCall(city);
        List<List<ListOfWheather>> name = new ArrayList<>();
//        List<ListOfWheather> adam = new ArrayList<ListOfWheather>();

        int c = 0 ;
        for (int i = 0; i < 5; i++) {
            List<ListOfWheather> array = new ArrayList<>();
            while(array.size()<5){

//                System.out.println(wheathers.get(c));
                array.add(wheathers.get(c));
                c++;
            }
            name.add(array);
        }
//        System.out.println(name.get(0).get(2).getDt());



//        System.out.println(adam.get(1).getDt());

        model.addAttribute("weathers",weatherService.makeCall(city));

//            cities   = cityService.insertCitiesToDB();
//        CityEntity cityEntity = new CityEntity();
//        cityEntity.setCountry("PL");
//        cityEntity.setName("POLSKA");
//
//
//        cityRepository.saveAll(cities);

//        cityRepository.saveAll();
//        List<ListOfWheather> listOfWheathers = weatherService.makeCall("Radom");
//        StringBuilder tekst = new StringBuilder();
//        for(ListOfWheather weater : listOfWheathers){
//            tekst.append("time: " +weater.getDt() + " temp: " + weater.getMainWeather().getTemp() + " hum: " + weater.getMainWeather().getHumidity() +"===\n");
//        }
//
//
        return "redirect:/0";
    }

    @GetMapping("/{idWeather}")

    public String getPartOfWeather(@PathVariable("idWeather") int idWeather,
                                   Model model){
        List<ListOfWheather> weathers = weatherService.getListOfWheathers();
        List<Page> pagination = new ArrayList<>();
        int amountOfPages = weathers.size()/6;
        for (int i = 0; i < amountOfPages; i++) {
            pagination.add(new Page(i ));
        }

        List<ListOfWheather> partOfWeather = new ArrayList<>();
        for(int i = idWeather * 6; i < 6 + (idWeather * 6); i++){
            if(i<weathers.size()){
                partOfWeather.add(weathers.get(i));

            }
        }
        System.out.println(idWeather);
        model.addAttribute("weathers",partOfWeather);
        model.addAttribute("pages", pagination);
        return "index";
    }
}
