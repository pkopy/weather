package pl.pkopy.weather.models.services;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.pkopy.weather.models.CityEntity;
import pl.pkopy.weather.models.Repositories.CityRepository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@SuppressWarnings("unchecked")
public class CityService {

    private CityRepository cityRepository;
    private List<CityEntity> cityEntities = new ArrayList<>();
    @Autowired
    public CityService(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    public List<CityEntity> cos(){


        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("C:/city.txt"));

            JSONArray cities = (JSONArray) obj;
            for(Object citi : cities){

                JSONObject jsonObject = (JSONObject) citi;

                Long idCity = (Long) jsonObject.get("id");
                String name = (String) jsonObject.get("name");
                String country = (String) jsonObject.get("country") ;

                CityEntity cityEntity = new CityEntity();
                cityEntity.setIdCity(idCity);
                cityEntity.setCountry(country);
                cityEntity.setName(name);

                cityEntities.add(cityEntity);
                if (cityEntity.getCountry().equals("PL")){
                    System.out.println("+");
                    cityRepository.save(cityEntity);
                }




//                System.out.println("Name: " + cityEntity.getName() + " id: " + cityEntity.getIdCity()+ " country: " + cityEntity.getCountry());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
//        cityRepository.saveAll(cityEntities);
        return cityEntities;

    }

    public void test(){
        CityEntity cityEntity = new CityEntity();

        cityEntity.setCountry("PK");
        cityEntity.setName("KKKK");

        cityRepository.save(cityEntity);
    }


    public String xxx() {
        StringBuilder tekst = new StringBuilder();
        for(CityEntity city : cityEntities){
            tekst.append("id: " + city.getIdCity() + " name: " + city.getName() + " country: " + city.getCountry() +"\n");
        }

        return tekst.toString();
    }
}
