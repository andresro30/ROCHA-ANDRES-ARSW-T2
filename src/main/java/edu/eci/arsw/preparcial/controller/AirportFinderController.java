package edu.eci.arsw.preparcial.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.preparcial.model.Airport;

import edu.eci.arsw.preparcial.services.AirportFinderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public class AirportFinderController {

    @Autowired
    private AirportFinderServices services;


    @GetMapping("/airports/{name}")
    public ResponseEntity<?> getAirportsByName(@PathVariable(name = "name") String name){
        List<Airport> airportList = null;
        try {
            services.getAirportsByName(name);
            return new ResponseEntity<>(airportList, HttpStatus.ACCEPTED);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
