package airport.models;

import airport.models.storages.FlightStorage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Location implements Cloneable {
    
    private final String airportId;
    private String airportName;
    private String airportCity;
    private String airportCountry;
    private double airportLatitude;
    private double airportLongitude;

    public Location(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    public String getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public double getAirportLatitude() {
        return airportLatitude;
    }

    public double getAirportLongitude() {
        return airportLongitude;
    }
    
    // Métodos para obtener vuelos
    public List<Flight> getDepartureFlights() throws Exception {
        List<Flight> departureFlights = new ArrayList<>();
        for (Flight flight : FlightStorage.getInstance().getAllFlights()) {
            if (flight.getDepartureLocation().getAirportId().equals(this.airportId)) {
                departureFlights.add(flight);
            }
        }
        return departureFlights;
    }
    
    public List<Flight> getArrivalFlights() throws Exception {
        List<Flight> arrivalFlights = new ArrayList<>();
        for (Flight flight : FlightStorage.getInstance().getAllFlights()) {
            if (flight.getArrivalLocation().getAirportId().equals(this.airportId)) {
                arrivalFlights.add(flight);
            }
        }
        return arrivalFlights;
    }
    
    // Método para obtener todos los vuelos relacionados
    public List<Flight> getAllRelatedFlights() throws Exception {
        List<Flight> allFlights = new ArrayList<>();
        allFlights.addAll(getDepartureFlights());
        allFlights.addAll(getArrivalFlights());
        return allFlights;
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}