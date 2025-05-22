
package airport.models.storages;

import airport.models.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LocationStorage {
    private static LocationStorage instance;
    private List<Location> locations;
    private static final String FILENAME = "locations.json";

    private LocationStorage() throws Exception {
        this.locations = loadFromJson();
        sortLocations();
    }

    public static LocationStorage getInstance() throws Exception {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    // Método para ordenar las ubicaciones por ID
    private void sortLocations() {
        locations.sort(Comparator.comparing(Location::getAirportId));
    }
    private List<Location> loadFromJson() throws Exception {
        List<Location> locations = new ArrayList<>();
        JSONArray jsonArray = JsonFileManager.readJsonArray(FILENAME);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Location location = new Location(
                json.getString("airportId"),
                json.getString("airportName"),
                json.getString("airportCity"),
                json.getString("airportCountry"),
                json.getDouble("airportLatitude"),
                json.getDouble("airportLongitude")
            );
            locations.add(location);
        }
        return locations;
    }

    public Location getLocationById(String id) {
        return locations.stream()
            .filter(loc -> loc.getAirportId().equals(id))
            .findFirst()
            .orElse(null);
    }
    public List<Location> getAllLocations() {
        return new ArrayList<>(locations);
    }
    
    public boolean addLocation(Location newLocation) {
        try {
            // Verificación adicional
            if (locations.stream().anyMatch(l -> l.getAirportId().equals(newLocation.getAirportId()))) {
                System.err.println("Error: ID de ubicación duplicado detectado en Storage");
                return false;
            }
            locations.add(newLocation);
            sortLocations(); // Ordenar después de añadir
            System.out.println("Ubicación añadida (sin persistencia JSON aún): " + newLocation.getAirportId());
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al añadir ubicación: " + e.getMessage());
            return false;
        }
    }
}