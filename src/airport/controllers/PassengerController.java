/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Passenger;
import airport.models.storages.PassengerStorage;
import java.time.LocalDate;
import java.time.Period;

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
            if (idText == null || idText.trim().isEmpty() ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null  || lastName.trim().isEmpty()  ||
                yearText == null  || yearText.trim().isEmpty()  ||
                monthText == null || monthText.trim().isEmpty() ||
                dayText == null   || dayText.trim().isEmpty()   ||
                phoneCodeText == null || phoneCodeText.trim().isEmpty() ||
                phoneText == null || phoneText.trim().isEmpty() ||
                country == null   || country.trim().isEmpty()) {
                return new Response("Todos los campos son obligatorios.", Status.BAD_REQUEST);
            }

            // ID válido
            long id;
            try {
                id = Long.parseLong(idText.trim());
                if (id <= 0) {
                    return new Response("El ID debe ser un número positivo.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("ID inválido.", Status.BAD_REQUEST);
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

                birthDate = LocalDate.of(year, month, day);

                LocalDate hoy = LocalDate.now();
                if (birthDate.isAfter(hoy)) {
                    return new Response("La fecha de nacimiento no puede ser en el futuro.", Status.BAD_REQUEST);
                }
                if (year < 1900) {
                    return new Response("El año de nacimiento es demasiado antiguo.", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Fecha de nacimiento inválida.", Status.BAD_REQUEST);
            }

            // Código de región válido
            int phoneCode;
            try {
                phoneCode = Integer.parseInt(phoneCodeText.trim());
                if (phoneCode <= 0) {
                    return new Response("Código de región inválido.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Código de región inválido.", Status.BAD_REQUEST);
            }

            // Teléfono válido
            long phone;
            try {
                phone = Long.parseLong(phoneText.trim());
                if (phone <= 0) {
                    return new Response("Número de teléfono inválido.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Número de teléfono inválido.", Status.BAD_REQUEST);
            }

            // País válido
            if (country.trim().isEmpty()) {
                return new Response("El país es obligatorio.", Status.BAD_REQUEST);
            }

            // Actualizar datos
            passenger.setFirstname(firstName.trim());
            passenger.setLastname(lastName.trim());
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(phoneCode);
            passenger.setPhone(phone);
            passenger.setCountry(country.trim());

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
            // 1. Validación de campos obligatorios
            if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                country == null || country.trim().isEmpty()) {
                return new Response("Nombre, apellido y país son obligatorios", Status.BAD_REQUEST);
            }
            
            // 2. Validación y conversión de ID
            long id;
            try {
                id = Long.parseLong(idText);
                if (id <= 0) {
                    return new Response("ID debe ser un número positivo", Status.BAD_REQUEST);
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
                int year = Integer.parseInt(yearText);
                int month = Integer.parseInt(monthText);
                int day = Integer.parseInt(dayText);
                
                birthDate = LocalDate.of(year, month, day);
                if (birthDate.isAfter(LocalDate.now())) {
                    return new Response("Fecha de nacimiento no puede ser futura", Status.BAD_REQUEST);
                }
                
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                if (age < 18) {
                    return new Response("El pasajero debe ser mayor de edad", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                return new Response("Fecha de nacimiento inválida", Status.BAD_REQUEST);
            }

            // Validación de teléfono
            int phoneCode;
            long phone;
            try {
                phoneCode = Integer.parseInt(phoneCodeText);
                phone = Long.parseLong(phoneText);
                if (phone <= 0) {
                    return new Response("Número de teléfono inválido", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Código y número de teléfono deben ser numéricos", Status.BAD_REQUEST);
            }

            // Verificar si el pasajero ya existe
            if (passengerStorage.getPassengerById(id) != null) {
                return new Response("El pasajero con este ID ya existe", Status.BAD_REQUEST);
            }

            // Crear y guardar el pasajero
            Passenger newPassenger = new Passenger(id, firstName, lastName, birthDate, phoneCode, phone, country);
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
}
