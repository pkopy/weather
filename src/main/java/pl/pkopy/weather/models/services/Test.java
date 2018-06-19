package pl.pkopy.weather.models.services;

import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;

import java.util.List;
import java.util.Map;

public class Test {

    JsonParser parser = new JsonParser() {
        @Override
        public Map<String, Object> parseMap(String json) throws JsonParseException {
            return null;
        }

        @Override
        public List<Object> parseList(String json) throws JsonParseException {
            return null;
        }
    };
}
