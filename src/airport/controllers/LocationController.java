package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Location;
import airport.models.storages.LocationStorage;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Santiago Solorzano
 */
public class LocationController {

    private final LocationStorage locationStorage;

    public LocationController() throws Exception {
        this.locationStorage = LocationStorage.getInstance();
    }

    public Response registerLocation(String airportId, String name, String city,
            String country, String latitude, String longitude) {
        try {
            // 1. Validación de campos obligatorios no vacíos
            if (airportId == null || airportId.trim().isEmpty()
                    || name == null || name.trim().isEmpty()
                    || city == null || city.trim().isEmpty()
                    || country == null || country.trim().isEmpty()
                    || latitude == null || latitude.trim().isEmpty()
                    || longitude == null || longitude.trim().isEmpty()) {
                return new Response("Todos los campos son obligatorios y no pueden estar vacíos", Status.BAD_REQUEST);
            }

            // 2. Validación de formato del ID del aeropuerto
            if (airportId.length() != 3 || !airportId.matches("[A-Z]{3}")) {
                return new Response("El ID de aeropuerto debe contener exactamente 3 letras mayúsculas", Status.BAD_REQUEST);
            }

            // 3. Validación de unicidad del ID
            if (locationStorage.getLocationById(airportId) != null) {
                return new Response("El ID de aeropuerto ya existe", Status.BAD_REQUEST);
            }

            // 4. Validación de coordenadas
            double lat, lon;
            try {
                // Validar formato numérico
                lat = Double.parseDouble(latitude);
                lon = Double.parseDouble(longitude);

                // Validar número de decimales
                if (latitude.split("\\.").length > 1 && latitude.split("\\.")[1].length() > 4) {
                    return new Response("La latitud no puede tener más de 4 decimales", Status.BAD_REQUEST);
                }
                if (longitude.split("\\.").length > 1 && longitude.split("\\.")[1].length() > 4) {
                    return new Response("La longitud no puede tener más de 4 decimales", Status.BAD_REQUEST);
                }

                // Validar rangos geográficos
                if (lat < -90 || lat > 90) {
                    return new Response("Latitud debe estar entre -90 y 90", Status.BAD_REQUEST);
                }
                if (lon < -180 || lon > 180) {
                    return new Response("Longitud debe estar entre -180 y 180", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Las coordenadas deben ser números válidos", Status.BAD_REQUEST);
            }

            // Crear y guardar la nueva ubicación
            Location newLocation = new Location(airportId, name, city, country, lat, lon);

            if (locationStorage.addLocation(newLocation)) {
                return new Response("Aeropuerto registrado exitosamente", Status.CREATED, newLocation);
            } else {
                return new Response("Error al guardar aeropuerto", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todas las ubicaciones ordenadas y retornar clones (Prototype)
    public List<Location> getAllLocationsSorted() {
        List<Location> originals = locationStorage.getAllLocations();
        List<Location> clones = new ArrayList<>();
        for (Location l : originals) {
            clones.add(l.clone());
        }
        return clones;
    }
}