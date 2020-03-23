package edu.eci.arsw.preparcial.model;

public class Airport {

    private String name;
    private String code;
    private String countryCode;
    private String city;
    private String cityId;
    private String airportId;
    private Location location;


    public Airport(String name, String city, String code, String countryCode, String cityId, String airportId, Location location) {
        this.name = name;
        this.code = code;
        this.countryCode = countryCode;
        this.cityId = cityId;
        this.airportId = airportId;
        this.location = location;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
