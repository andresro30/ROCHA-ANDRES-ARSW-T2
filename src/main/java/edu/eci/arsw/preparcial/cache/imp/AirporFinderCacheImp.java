package edu.eci.arsw.preparcial.cache.imp;

import edu.eci.arsw.preparcial.cache.AirportFinderCache;
import edu.eci.arsw.preparcial.model.Airport;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AirporFinderCacheImp implements AirportFinderCache {

    ConcurrentHashMap<String,List<Airport>> airportsCache;
    ConcurrentHashMap<String, LocalDateTime> airportsTime;

    public AirporFinderCacheImp(){
        airportsCache = new ConcurrentHashMap<>();
        airportsTime = new ConcurrentHashMap<>();
    }

    @Override
    public void saveAirportByName(String name, List<Airport> airports) {
        airportsCache.put(name,airports);
        airportsTime.put(name,LocalDateTime.now());
    }

    @Override
    public ConcurrentHashMap<String, List<Airport>> getAirportsCache() {
        return airportsCache;
    }

    @Override
    public ConcurrentHashMap<String, LocalDateTime> getAirportsTime() {
        return airportsTime;
    }

    @Override
    public void deleteAirportByName(String name) {
        airportsCache.remove(name);
        airportsTime.remove(name);
    }
}
