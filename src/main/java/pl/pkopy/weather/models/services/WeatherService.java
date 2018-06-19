package pl.pkopy.weather.models.services;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pkopy.weather.models.ListOfWheather;
import pl.pkopy.weather.models.MainWeather;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
@Setter
public class WeatherService {

    @Value("${opnenweathermap.api_key}")
    private String apiKey;
    private List<ListOfWheather> listOfWheathers;


    public List<ListOfWheather> makeCall(String city){
        listOfWheathers = new ArrayList<>();
    JSONParser parser = new JSONParser();


        try {
            RestTemplate restTemplate = new RestTemplate();
            String cos = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&mode=json&appid=" + apiKey, String.class);
//            System.out.println(cos);
            Object obj = parser.parse(cos);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray weaters = (JSONArray) jsonObject.get("list");


            for (int i = 0; i < weaters.size(); i++) {
                String weater = weaters.get(i).toString();
                Object obj1 = parser.parse(weater);
                JSONObject jsonObject1 = (JSONObject) obj1;

                ListOfWheather listOfWheather = new ListOfWheather();
                String dt = (String) jsonObject1.get("dt_txt");
                listOfWheather.setDt(dt);

                Object obj2 =  parser.parse(jsonObject1.get("main").toString());
                JSONObject jsonObject2 = (JSONObject) obj2;

                Object obj3 =  parser.parse(jsonObject1.get("weather").toString());
                JSONArray jsonObject3 = (JSONArray) obj3;


                Object obj4 = parser.parse(jsonObject3.get(0).toString());
                JSONObject jsonObject4 = (JSONObject) obj4;

                MainWeather mainWeather = new MainWeather();
                Double temp;
//                System.out.println(jsonObject2.get("temp").getClass().getName());
                if(jsonObject2.get("temp").getClass().getName().equals("java.lang.Long")){
                    Long temp1 = (Long) jsonObject2.get("temp");
                    temp = temp1 * 1.0;
                }else{

                    temp = (Double) jsonObject2.get("temp");
                }
                Double pressure;
                if(jsonObject2.get("pressure").getClass().getName().equals("java.lang.Long")){
                    Long pressure1 = (Long) jsonObject2.get("pressure");
                    pressure = pressure1 * 1.0;
                }else{

                    pressure = (Double) jsonObject2.get("pressure");
                }


                Long humidity = (Long) jsonObject2.get("humidity") ;
                String icon = (String) jsonObject4.get("icon");


                mainWeather.setHumidity(humidity);
                mainWeather.setPressure(pressure);
                mainWeather.setIcon(icon);
                mainWeather.setTemp(Math.round(temp - 273.15));

                listOfWheather.setMainWeather(mainWeather);

                listOfWheathers.add(listOfWheather);



            }


        }catch (Exception e) {
            e.printStackTrace();
        }

        return listOfWheathers;
    }
}
