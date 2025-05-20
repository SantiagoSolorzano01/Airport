/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storages;

import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightStorage {
    private static FlightStorage instance;
    private List<Flight> flights;
    private static final String FILENAME = "flights.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private FlightStorage() throws Exception {
        this.flights = loadFromJson();
    }

    public static FlightStorage getInstance() throws Exception {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    private List<Flight> loadFromJson() throws Exception {
        List<Flight> flights = new ArrayList<>();
        JSONArray jsonArray = JsonFileManager.readJsonArray(FILENAME);
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        LocationStorage locationStorage = LocationStorage.getInstance();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            
            Plane plane = planeStorage.getPlaneById(json.getString("plane"));
            Location departureLoc = locationStorage.getLocationById(json.getString("departureLocation"));
            Location arrivalLoc = locationStorage.getLocationById(json.getString("arrivalLocation"));

            Flight flight;
            if (json.isNull("scaleLocation")) {
                flight = new Flight(
                    json.getString("id"),
                    plane,
                    departureLoc,
                    arrivalLoc,
                    LocalDateTime.parse(json.getString("departureDate"), DATE_FORMATTER),
                    json.getInt("hoursDurationArrival"),
                    json.getInt("minutesDurationArrival")
                );
            } else {
                Location scaleLoc = locationStorage.getLocationById(json.getString("scaleLocation"));
                flight = new Flight(
                    json.getString("id"),
                    plane,
                    departureLoc,
                    scaleLoc,
                    arrivalLoc,
                    LocalDateTime.parse(json.getString("departureDate"), DATE_FORMATTER),
                    json.getInt("hoursDurationArrival"),
                    json.getInt("minutesDurationArrival"),
                    json.getInt("hoursDurationScale"),
                    json.getInt("minutesDurationScale")
                );
            }
            flights.add(flight);
        }
        return flights;
    }

    public Flight getFlightById(String id) {
        return flights.stream()
            .filter(flight -> flight.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }

    public boolean addFlight(Flight newFlight) {
        try {
            flights.add(newFlight);
            System.out.println("Vuelo añadido (sin persistencia JSON aún): " + newFlight.getId());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al añadir vuelo: " + e.getMessage());
            return false;
        }
    }
    public boolean updateFlight(Flight updatedFlight) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getId().equals(updatedFlight.getId())) {
                flights.set(i, updatedFlight);
                return true;
            }
        }
        return false;
    }
}
