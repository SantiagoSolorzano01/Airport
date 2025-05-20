/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.storages;

import airport.models.Passenger;
import java.io.File;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PassengerStorage {

    private static PassengerStorage instance;
    private List<Passenger> passengers;
    private static final String FILENAME = "../json/passengers.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private PassengerStorage() throws Exception {
        ensureDataDirectoryExists();
        this.passengers = loadFromJson();
    }

    private void ensureDataDirectoryExists() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static PassengerStorage getInstance() throws Exception {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }

    private List<Passenger> loadFromJson() throws Exception {
        List<Passenger> passengers = new ArrayList<>();
        JSONArray jsonArray = JsonFileManager.readJsonArray(FILENAME);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Passenger passenger = new Passenger(
                    json.getLong("id"),
                    json.getString("firstname"),
                    json.getString("lastname"),
                    LocalDate.parse(json.getString("birthDate"), DATE_FORMATTER),
                    json.getInt("countryPhoneCode"),
                    json.getLong("phone"),
                    json.getString("country")
            );
            passengers.add(passenger);
        }
        return passengers;
    }

    public Passenger getPassengerById(long id) {
        return passengers.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }

    public boolean addPassenger(Passenger newPassenger) {
        try {
            // Solo añade a la lista 
            passengers.add(newPassenger);
            System.out.println("Pasajero añadido: " + newPassenger.getFirstname() + " con el id: " + newPassenger.getId());
            return true;
        } catch (Exception e) {
            System.err.println("Error al añadir pasajero: " + e.getMessage());
            return false;
        }
    }

}
