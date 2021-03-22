package com.example.tp3;

import java.util.ArrayList;

public class Data {

    public static final String[] PLANETS_NAME = {"Mercure", "Venus", "Terre", "Mars", "Jupiter", "Saturne", "Uranus", "Neptune", "Pluton"};
    public static final String[] PLANETS_SIZE = {"4900"   , "12000", "12800", "6800", "144000" , "120000" , "52000" , "50000"  , "2300"  };

    private final ArrayList<Planet> planets;

    public Data(){
        planets = new ArrayList<Planet>();
        for(int i = 0; i< PLANETS_NAME.length; i++){
            planets.add(
                    new Planet(
                            PLANETS_NAME[i],
                            Integer.parseInt(PLANETS_SIZE[i]
                            )
                    )
            );
        }
    }

    public int getSize(){
        return planets.size();
    }

    public Planet get(int i){
        return planets.get(i);
    }
}
