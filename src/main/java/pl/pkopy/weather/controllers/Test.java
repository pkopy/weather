package pl.pkopy.weather.controllers;

import pl.pkopy.weather.models.ListOfWheather;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        long a = 1;
        double b = 2.00;
        double c = a*b;
        System.out.println(c);


            List<List<ListOfWheather>> name = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            name.add(new ArrayList<ListOfWheather>());
            System.out.println(name.get(i));

        }
    }
}
