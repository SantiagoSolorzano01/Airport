package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import airport.models.storages.FlightStorage;
import airport.models.storages.LocationStorage;
import airport.models.storages.PlaneStorage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.util.List;
import java.util.ArrayList;

public class FlightController {

    private final FlightStorage flightStorage;
    private final PlaneStorage planeStorage;
    private final LocationStorage locationStorage;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FlightController() throws Exception {
        this.flightStorage = FlightStorage.getInstance();
        this.planeStorage = PlaneStorage.getInstance();
        this.locationStorage = LocationStorage.getInstance();
    }

    // DEVUELVE COPIAS (Prototype)
    public List<Flight> getAllFlightsSorted() throws Exception {
        List<Flight> originals = flightStorage.getAllFlightsSorted();
        List<Flight> clones = new ArrayList<>();
        for (Flight f : originals) {
            clones.add(f.clone());
        }
        return clones;
    }

    public Flight getFlightById(String id) {
        Flight original = flightStorage.getFlightById(id);
        return (original != null) ? original.clone() : null;
    }

    public List<Flight> getAllFlights() {
        List<Flight> originals = flightStorage.getAllFlights();
        List<Flight> clones = new ArrayList<>();
        for (Flight f : originals) {
            clones.add(f.clone());
        }
        return clones;
    }

    public Response registerFlight(
            String flightId,
            String planeId,
            String departureLocId,
            String arrivalLocId,
            String scaleLocId,
            String year,
            String month,
            String day,
            String hour,
            String minute,
            String hoursArrival,
            String minsArrival,
            String hoursScale,
            String minsScale
    ) {
        try {
            if (flightId == null || flightId.trim().isEmpty()
                    || planeId == null || planeId.trim().isEmpty()
                    || departureLocId == null || departureLocId.trim().isEmpty()
                    || arrivalLocId == null || arrivalLocId.trim().isEmpty()
                    || year == null || year.trim().isEmpty()
                    || month == null || month.trim().isEmpty()
                    || day == null || day.trim().isEmpty()
                    || hour == null || hour.trim().isEmpty()
                    || minute == null || minute.trim().isEmpty()
                    || hoursArrival == null || hoursArrival.trim().isEmpty()
                    || minsArrival == null || minsArrival.trim().isEmpty()) {
                return new Response("Todos los campos obligatorios deben estar completos", Status.BAD_REQUEST);
            }

            if (flightId.length() != 6 || !flightId.matches("^[A-Z]{3}\\d{3}$")) {
                return new Response("El ID del vuelo debe seguir el formato XXXYYY donde:\n"
                        + "- XXX son 3 letras mayúsculas\n"
                        + "- YYY son 3 dígitos numéricos", Status.BAD_REQUEST);
            }

            if (flightStorage.getFlightById(flightId) != null) {
                return new Response("El ID de vuelo ya existe", Status.BAD_REQUEST);
            }

            LocalDateTime departureDate;
            try {
                int y = Integer.parseInt(year);
                int m = Integer.parseInt(month);
                int d = Integer.parseInt(day);
                int h = Integer.parseInt(hour);
                int min = Integer.parseInt(minute);

                departureDate = LocalDateTime.of(y, m, d, h, min);

                if (departureDate.isBefore(LocalDateTime.now())) {
                    return new Response("La fecha de salida no puede ser en el pasado", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Fecha u hora de salida inválidas", Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new Response("Los valores de fecha y hora deben ser números válidos", Status.BAD_REQUEST);
            }

            int hoursArr, minsArr;
            try {
                hoursArr = Integer.parseInt(hoursArrival);
                minsArr = Integer.parseInt(minsArrival);

                if (hoursArr <= 0 && minsArr <= 0) {
                    return new Response("La duración del vuelo debe ser mayor a 00:00", Status.BAD_REQUEST);
                }
                if (hoursArr < 0 || minsArr < 0 || minsArr >= 60) {
                    return new Response("Duración de vuelo inválida", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Las duraciones deben ser números válidos", Status.BAD_REQUEST);
            }

            boolean hasScale = scaleLocId != null && !scaleLocId.trim().isEmpty();
            int hoursSc = 0, minsSc = 0;

            if (hasScale) {
                if (hoursScale == null || hoursScale.trim().isEmpty()
                        || minsScale == null || minsScale.trim().isEmpty()) {
                    return new Response("Debe especificar la duración de la escala", Status.BAD_REQUEST);
                }

                try {
                    hoursSc = Integer.parseInt(hoursScale);
                    minsSc = Integer.parseInt(minsScale);

                    if (hoursSc == 0 && minsSc == 0) {
                        return new Response("La duración de la escala no puede ser 00:00", Status.BAD_REQUEST);
                    }

                    if (hoursSc < 0 || minsSc < 0 || minsSc >= 60) {
                        return new Response("Duración de escala inválida", Status.BAD_REQUEST);
                    }
                } catch (NumberFormatException e) {
                    return new Response("Las duraciones de escala deben ser números válidos", Status.BAD_REQUEST);
                }
            } else {
                if ((hoursScale != null && !hoursScale.trim().isEmpty() && !hoursScale.equals("0"))
                        || (minsScale != null && !minsScale.trim().isEmpty() && !minsScale.equals("0"))) {
                    return new Response("No puede especificar duración de escala si no seleccionó una ubicación de escala", Status.BAD_REQUEST);
                }
            }

            Plane plane = planeStorage.getPlaneById(planeId);
            if (plane == null) {
                return new Response("Avión no encontrado", Status.NOT_FOUND);
            }

            Location departure = locationStorage.getLocationById(departureLocId);
            Location arrival = locationStorage.getLocationById(arrivalLocId);

            if (departure == null || arrival == null) {
                return new Response("Ubicación no encontrada", Status.NOT_FOUND);
            }
            if (departure.equals(arrival)) {
                return new Response("El lugar de salida no puede ser el mismo que el de llegada", Status.BAD_REQUEST);
            }

            Flight newFlight;
            if (!hasScale) {
                newFlight = new Flight(
                        flightId, plane, departure, arrival,
                        departureDate, hoursArr, minsArr
                );
            } else {
                Location scale = locationStorage.getLocationById(scaleLocId);
                if (scale == null) {
                    return new Response("Ubicación de escala no encontrada", Status.NOT_FOUND);
                }
                if (scale.equals(departure) || scale.equals(arrival)) {
                    return new Response("La ubicación de escala no puede ser igual al origen o destino", Status.BAD_REQUEST);
                }

                newFlight = new Flight(
                        flightId, plane, departure, scale, arrival,
                        departureDate, hoursArr, minsArr, hoursSc, minsSc
                );
            }

            if (flightStorage.addFlight(newFlight)) {
                return new Response("Vuelo registrado exitosamente", Status.CREATED, newFlight);
            } else {
                return new Response("Error al guardar el vuelo", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response delayFlight(String flightId, String hoursStr, String minutesStr) {
        try {
            if (flightId == null || flightId.trim().isEmpty()) {
                return new Response("Debe seleccionar un vuelo", Status.BAD_REQUEST);
            }

            int hours, minutes;
            try {
                hours = Integer.parseInt(hoursStr);
                minutes = Integer.parseInt(minutesStr);
            } catch (NumberFormatException e) {
                return new Response("Horas y minutos deben ser números válidos", Status.BAD_REQUEST);
            }

            if (hours < 0 || hours > 48) {
                return new Response("Las horas de retraso deben estar entre 0 y 48", Status.BAD_REQUEST);
            }
            if (minutes < 0 || minutes >= 60) {
                return new Response("Los minutos deben estar entre 0 y 59", Status.BAD_REQUEST);
            }
            if (hours == 0 && minutes == 0) {
                return new Response("No se ha especificado retraso", Status.BAD_REQUEST);
            }

            Flight flight = flightStorage.getFlightById(flightId);
            if (flight == null) {
                return new Response("Vuelo no encontrado", Status.NOT_FOUND);
            }

            if (flight.getDepartureDate().isBefore(LocalDateTime.now())) {
                return new Response("No se puede retrasar un vuelo que ya ha partido", Status.BAD_REQUEST);
            }

            flight.delay(hours, minutes);

            if (flightStorage.updateFlight(flight)) {
                String newDeparture = flight.getDepartureDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                String newArrival = flight.calculateArrivalDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                return new Response(
                        String.format("Vuelo retrasado:<br>• Nueva salida: %s<br>• Nueva llegada: %s",
                                newDeparture, newArrival),
                        Status.OK,
                        flight
                );
            } else {
                return new Response("Error al guardar los cambios", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}