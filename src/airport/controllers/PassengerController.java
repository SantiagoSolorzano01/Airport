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

    public Response updatePassenger(
            String idText,
            String firstName,
            String lastName,
            String yearText,
            String monthText,
            String dayText,
            String phoneCodeText,
            String phoneText,
            String country
    ) {
        try {
            // Validación de campos vacíos
            if (idText == null || idText.trim().isEmpty()
                    || firstName == null || firstName.trim().isEmpty()
                    || lastName == null || lastName.trim().isEmpty()
                    || yearText == null || yearText.trim().isEmpty()
                    || monthText == null || monthText.trim().isEmpty()
                    || dayText == null || dayText.trim().isEmpty()
                    || phoneCodeText == null || phoneCodeText.trim().isEmpty()
                    || phoneText == null || phoneText.trim().isEmpty()
                    || country == null || country.trim().isEmpty()) {
                return new Response("Todos los campos son obligatorios.", Status.BAD_REQUEST);
            }

            // ID válido (mayor o igual a 0, máximo 15 dígitos)
            long id;
            try {
                id = Long.parseLong(idText.trim());
                if (id < 0) {
                    return new Response("El ID debe ser un número mayor o igual a cero.", Status.BAD_REQUEST);
                }
                if (idText.trim().length() > 15) {
                    return new Response("El ID no puede tener más de 15 dígitos.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID inválido. Debe ser un número.", Status.BAD_REQUEST);
            }

            // Buscar pasajero existente
            Passenger passenger = passengerStorage.getPassengerById(id);
            if (passenger == null) {
                return new Response("No se encontró el pasajero con ese ID.", Status.NOT_FOUND);
            }

            // Fecha de nacimiento válida
            LocalDate birthDate;
            try {
                int year = Integer.parseInt(yearText.trim());
                int month = Integer.parseInt(monthText.trim());
                int day = Integer.parseInt(dayText.trim());

                // Validación básica de valores de fecha
                if (month < 1 || month > 12 || day < 1 || day > 31) {
                    return new Response("Fecha de nacimiento inválida.", Status.BAD_REQUEST);
                }

                birthDate = LocalDate.of(year, month, day);

                LocalDate hoy = LocalDate.now();
                if (birthDate.isAfter(hoy)) {
                    return new Response("La fecha de nacimiento no puede ser en el futuro.", Status.BAD_REQUEST);
                }
                if (year < 1850) {
                    return new Response("El año de nacimiento es demasiado antiguo (antes de 1850).", Status.BAD_REQUEST);
                }

                // Validación de mayoría de edad
                int edad = Period.between(birthDate, hoy).getYears();
                if (edad < 18) {
                    return new Response("El pasajero debe ser mayor de edad (18+ años).", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Fecha de nacimiento inválida: " + e.getMessage(), Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new Response("Año, mes y día deben ser números válidos.", Status.BAD_REQUEST);
            }

            // Código de región válido (mayor o igual a 0, máximo 3 dígitos)
            int phoneCode;
            try {
                phoneCode = Integer.parseInt(phoneCodeText.trim());
                if (phoneCode < 0) {
                    return new Response("Código de región inválido. Debe ser mayor o igual a 0.", Status.BAD_REQUEST);
                }
                if (phoneCodeText.trim().length() > 3) {
                    return new Response("Código de región no puede tener más de 3 dígitos.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Código de región inválido. Debe ser un número.", Status.BAD_REQUEST);
            }

            // Teléfono válido (mayor o igual a 0, máximo 11 dígitos)
            long phone;
            try {
                phone = Long.parseLong(phoneText.trim());
                if (phone < 0) {
                    return new Response("Número de teléfono inválido. Debe ser mayor o igual a 0.", Status.BAD_REQUEST);
                }
                if (phoneText.trim().length() > 11) {
                    return new Response("Número de teléfono no puede tener más de 11 dígitos.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Número de teléfono inválido. Debe ser un número.", Status.BAD_REQUEST);
            }

            // País válido (ya validado que no está vacío)
            country = country.trim();

            // Actualizar datos
            passenger.setFirstname(firstName.trim());
            passenger.setLastname(lastName.trim());
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(phoneCode);
            passenger.setPhone(phone);
            passenger.setCountry(country);

            return new Response("Pasajero actualizado exitosamente.", Status.OK, passenger);

        } catch (Exception ex) {
            return new Response("Error inesperado: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response registerPassenger(
            String idText,
            String firstName,
            String lastName,
            String yearText,
            String monthText,
            String dayText,
            String phoneCodeText,
            String phoneText,
            String country
    ) {
        try {
            // 1. Validación de campos obligatorios no vacíos
            if (idText == null || idText.trim().isEmpty()
                    || firstName == null || firstName.trim().isEmpty()
                    || lastName == null || lastName.trim().isEmpty()
                    || yearText == null || yearText.trim().isEmpty()
                    || monthText == null || monthText.trim().isEmpty()
                    || dayText == null || dayText.trim().isEmpty()
                    || phoneCodeText == null || phoneCodeText.trim().isEmpty()
                    || phoneText == null || phoneText.trim().isEmpty()
                    || country == null || country.trim().isEmpty()) {
                return new Response("Todos los campos son obligatorios y no pueden estar vacíos", Status.BAD_REQUEST);
            }

            // 2. Validación y conversión de ID (mayor o igual a 0, máximo 15 dígitos)
            long id;
            try {
                id = Long.parseLong(idText.trim());
                if (id < 0) {
                    return new Response("ID debe ser un número mayor o igual a 0", Status.BAD_REQUEST);
                }
                if (idText.trim().length() > 15) {
                    return new Response("ID no puede tener más de 15 dígitos", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID debe ser un número válido", Status.BAD_REQUEST);
            }

            // Validación de unicidad de ID
            if (passengerStorage.getPassengerById(id) != null) {
                return new Response("El ID de pasajero ya existe", Status.BAD_REQUEST);
            }

            // Validación de fecha de nacimiento
            LocalDate birthDate;
            try {
                int year = Integer.parseInt(yearText.trim());
                int month = Integer.parseInt(monthText.trim());
                int day = Integer.parseInt(dayText.trim());

                // Validación básica de valores de fecha
                if (month < 1 || month > 12 || day < 1 || day > 31) {
                    return new Response("Fecha de nacimiento inválida", Status.BAD_REQUEST);
                }

                birthDate = LocalDate.of(year, month, day);

                // Validación de fecha futura
                LocalDate hoy = LocalDate.now();
                if (birthDate.isAfter(hoy)) {
                    return new Response("Fecha de nacimiento no puede ser futura", Status.BAD_REQUEST);
                }

                // Validación de año mínimo (1850)
                if (year < 1850) {
                    return new Response("El año de nacimiento es demasiado antiguo (antes de 1850)", Status.BAD_REQUEST);
                }

                // Validación de mayoría de edad
                int age = Period.between(birthDate, hoy).getYears();
                if (age < 18) {
                    return new Response("El pasajero debe ser mayor de edad (18+ años)", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Fecha de nacimiento inválida: " + e.getMessage(), Status.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new Response("Año, mes y día deben ser números válidos", Status.BAD_REQUEST);
            }

            // Validación de código telefónico (mayor o igual a 0, máximo 3 dígitos)
            int phoneCode;
            try {
                phoneCode = Integer.parseInt(phoneCodeText.trim());
                if (phoneCode < 0) {
                    return new Response("Código telefónico debe ser mayor o igual a 0", Status.BAD_REQUEST);
                }
                if (phoneCodeText.trim().length() > 3) {
                    return new Response("Código telefónico no puede tener más de 3 dígitos", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Código telefónico debe ser un número válido", Status.BAD_REQUEST);
            }

            // Validación de teléfono (mayor o igual a 0, máximo 11 dígitos)
            long phone;
            try {
                phone = Long.parseLong(phoneText.trim());
                if (phone < 0) {
                    return new Response("Número de teléfono debe ser mayor o igual a 0", Status.BAD_REQUEST);
                }
                if (phoneText.trim().length() > 11) {
                    return new Response("Número de teléfono no puede tener más de 11 dígitos", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Número de teléfono debe ser un número válido", Status.BAD_REQUEST);
            }

            // Validación de país (ya validado que no está vacío)
            country = country.trim();

            // Crear y guardar el pasajero
            Passenger newPassenger = new Passenger(id, firstName.trim(), lastName.trim(), birthDate, phoneCode, phone, country);
            boolean success = passengerStorage.addPassenger(newPassenger);

            if (success) {
                return new Response("Pasajero registrado exitosamente", Status.CREATED, newPassenger);
            } else {
                return new Response("Error al registrar pasajero", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response addPassengerToFlight(String passengerIdText, String flightId) {
        try {
            // Validación de campos vacíos
            if (passengerIdText == null || passengerIdText.trim().isEmpty()
                    || flightId == null || flightId.trim().isEmpty()) {
                return new Response("El ID del pasajero y el ID del vuelo son obligatorios.", Status.BAD_REQUEST);
            }

            // Validación del ID del pasajero
            long passengerId;
            try {
                passengerId = Long.parseLong(passengerIdText.trim());
                if (passengerId <= 0) {
                    return new Response("El ID del pasajero debe ser un número positivo.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID de pasajero inválido.", Status.BAD_REQUEST);
            }

            // Validación del ID del vuelo
            if (flightId.trim().length() != 6) {
                return new Response("El ID del vuelo debe tener exactamente 6 caracteres.", Status.BAD_REQUEST);
            }

            // Buscar pasajero
            Passenger passenger = passengerStorage.getPassengerById(passengerId);
            if (passenger == null) {
                return new Response("No se encontró el pasajero con ese ID.", Status.NOT_FOUND);
            }

            // Buscar vuelo (asumiendo que tienes un FlightStorage similar)
            Flight flight = FlightStorage.getInstance().getFlightById(flightId.trim());
            if (flight == null) {
                return new Response("No se encontró el vuelo con ese ID.", Status.NOT_FOUND);
            }

            // Verificar si el pasajero ya está en el vuelo
            if (passenger.getFlights().contains(flight)) {
                return new Response("El pasajero ya está registrado en este vuelo.", Status.BAD_REQUEST);
            }

            // Verificar capacidad del vuelo (si aplica)
            if (flight.getPassengers().size() >= flight.getPlane().getMaxCapacity()) {
                return new Response("El vuelo ha alcanzado su capacidad máxima.", Status.BAD_REQUEST);
            }

            // Agregar pasajero al vuelo y viceversa
            passenger.addFlight(flight);
            flight.addPassenger(passenger);

            // Actualizar en el almacenamiento
            passengerStorage.updatePassenger(passenger);
            FlightStorage.getInstance().updateFlight(flight);

            return new Response("Pasajero agregado al vuelo exitosamente.", Status.OK, passenger);

        } catch (Exception ex) {
            return new Response("Error inesperado al agregar pasajero al vuelo: " + ex.getMessage(),
                    Status.INTERNAL_SERVER_ERROR);
        }
    }

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
