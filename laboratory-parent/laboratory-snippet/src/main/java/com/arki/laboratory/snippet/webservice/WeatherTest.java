package com.arki.laboratory.snippet.webservice;

import com.arki.laboratory.snippet.webservice.weather.ArrayOfString;
import com.arki.laboratory.snippet.webservice.weather.WeatherWS;
import com.arki.laboratory.snippet.webservice.weather.WeatherWSSoap;

import java.util.List;

public class WeatherTest {
    public static void main(String[] args) {
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
        ArrayOfString regionProvince = weatherWSSoap.getRegionProvince();
        System.out.println(regionProvince.getString());
        ArrayOfString supportCityString = weatherWSSoap.getSupportCityString("3118");
        System.out.println(supportCityString.getString());
        ArrayOfString weather = weatherWSSoap.getWeather("1431", null);
        List<String> weatherList = weather.getString();
        for (String s:weatherList) {
            System.out.println("===============");
            System.out.println(s);
        }
    }
}
