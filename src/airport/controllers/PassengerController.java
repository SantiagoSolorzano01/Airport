package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight;
import airport.models.Passenger;
import airport.models.storages.FlightStorage;
import airport.models.storages.PassengerStorage;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago Solorzano
 */
public class PassengerController {

    private final PassengerStorage passengerStorage;

    public PassengerController() throws Exception {
        this.passengerStorage = PassengerStorage.getInstance();
    }

    // ... (métodos de registro y actualización igual que antes) ...

    public List<Passenger> getAllPassengersSorted() {
        List<Passenger> originals = passengerStorage.getAllPassengersSortedById();
        List<Passenger> clones = new ArrayList<>();
        for (Passenger p : originals) {
            clones.add(p.clone());
        }
        return clones;
    }

    public Response getPassengerFlights(String passengerIdText) {
        try {
            // Validación básica
            if (passengerIdText == null || passengerIdText.trim().isEmpty()) {
                return new Response("El ID del pasajero es requerido", Status.BAD_REQUEST);
            }

            // Validar formato numérico y longitud
            if (!passengerIdText.matches("\\d{1,15}")) {
                return new Response("ID inválido. Debe ser numérico (1-15 dígitos)", Status.BAD_REQUEST);
            }

            long passengerId = Long.parseLong(passengerIdText);

            // Buscar pasajero
            Passenger passenger = passengerStorage.getPassengerById(passengerId);
            if (passenger == null) {
                return new Response("No se encontró el pasajero con ID: " + passengerId, Status.NOT_FOUND);
            }

            // Obtener vuelos y retornar copias
            ArrayList<Flight> flights = passenger.getFlights();
            ArrayList<Flight> clones = new ArrayList<>();
            for (Flight f : flights) {
                clones.add(f.clone());
            }
            return new Response("Vuelos obtenidos exitosamente", Status.OK, clones);

        } catch (NumberFormatException e) {
            return new Response("Error en el formato del ID", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}