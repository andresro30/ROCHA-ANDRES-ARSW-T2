package edu.eci.arsw.preparcial.model;

public class Location {

    private Double altitud;
    private Double longitud;

    public Location(Double altitud, Double longitud) {
        this.altitud = altitud;
        this.longitud = longitud;
    }


    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
