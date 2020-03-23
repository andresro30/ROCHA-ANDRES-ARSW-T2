package edu.eci.arsw.preparcial.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.preparcial.model.Airport;
import java.util.List;

public interface AirportFinderServices {

    List<Airport> getAirportsByName(String name) throws UnirestException;
}
