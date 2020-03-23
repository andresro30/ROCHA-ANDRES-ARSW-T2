package edu.eci.arsw.preparcial.cache;

import edu.eci.arsw.preparcial.model.Airport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface AirportFinderCache{

    void saveAirportByName(String name, List<Airport> airports);

    ConcurrentHashMap<String,List<Airport>> getAirportsCache();

    ConcurrentHashMap<String, LocalDateTime> getAirportsTime();

    void deleteAirportByName(String name);
}
