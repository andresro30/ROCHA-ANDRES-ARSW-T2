package edu.eci.arsw.preparcial.services.imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.preparcial.cache.AirportFinderCache;
import edu.eci.arsw.preparcial.model.Airport;
import edu.eci.arsw.preparcial.services.AirportFinderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirportFinderServicesImp implements AirportFinderServices {

    @Autowired
    private AirportFinderCache cache;
    private String url;
    private String apiHost;
    private String apiKey;
    private Gson gson;


    public AirportFinderServicesImp(){
        url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-text?text=";
        apiHost  = "cometari-airportsfinder-v1.p.rapidapi.com";
        apiKey = "80100ea453mshcd0d35d8235246bp102f71jsnf315f9368589";
    }

    @Override
    public List<Airport> getAirportsByName(String name){
        String code = null;
        //Preguntar por qué usaron ese formato !!
        try {
            code = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //StringBuilder es una calse que proporciona una Api compatible con StringGuffer pero sin garantizar
        //sincronización. Construye una cadena de caracteres vacía y con capacidad de 16.
        StringBuilder urlApi = new StringBuilder();
        urlApi.append(url+code);


        //Creamos una petición de tipo encabezado usando Unirest
        HttpResponse<String> responseApi = null;
        try {
            responseApi = Unirest.get(urlApi.toString())
                    .header("x-rapidapi-host",apiHost)
                    .header("x-rapidapi-key",apiKey)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return getAirportsFromCache(responseApi,name);
    }

    public List<Airport> getAirportsFromCache(HttpResponse<String> responseApi,String name) {
        List<Airport> response = null;
        if(cache.getAirportsCache().contains(name)){
            LocalDateTime tiempoActual = LocalDateTime.now();
            LocalDateTime tiempoConsulta = cache.getAirportsTime().get("name");
            if(tiempoActual.isAfter(tiempoConsulta.plusMinutes(5))){
                System.out.println("La petición excedió los 5 minutos");
                cache.deleteAirportByName(name);
                response = gson.fromJson(responseApi.getBody(),new TypeToken<List<Airport>>(){}.getType());
            }
            else{
                System.out.println("La petición está en cache");
                response = cache.getAirportsCache().get(name);
            }
        }
        else{
            System.out.println("La petición no está en cache");
            response = gson.fromJson(responseApi.getBody(),new TypeToken<List<Airport>>(){}.getType());
            cache.saveAirportByName(name,response);
        }
        return response;
    }

}

