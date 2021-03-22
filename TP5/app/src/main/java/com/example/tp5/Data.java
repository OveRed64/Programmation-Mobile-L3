package com.example.tp5;

public class Data {
    public static final String[] PlanetsName = {"Mercure","Venus","Terre","Mars","Jupiter","Saturne","Uranus","Neptune"};
    public static final String[] PlanetsDesc = {"2439","6051","6371","3389","69911","58232","25362","24622"};

    private final String planetName;
    private final String planetDesc;

    Data(String planetName, String planetDesc){
        this.planetName = planetName;
        this.planetDesc = planetDesc;
    }

    public String getPlanetName() {
        return planetName;
    }

    public String getPlanetDesc() {
        return planetDesc;
    }
}