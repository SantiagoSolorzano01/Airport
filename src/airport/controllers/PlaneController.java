package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Plane;
import airport.models.storages.PlaneStorage;
import java.util.List;
import java.util.ArrayList;

public class PlaneController {

    private final PlaneStorage planeStorage;

    public PlaneController() throws Exception {
        this.planeStorage = PlaneStorage.getInstance();
    }

    public Response registerPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            // 1. Validación de campos obligatorios no vacíos
            if (id == null || id.trim().isEmpty()
                    || brand == null || brand.trim().isEmpty()
                    || model == null || model.trim().isEmpty()
                    || maxCapacity == null || maxCapacity.trim().isEmpty()
                    || airline == null || airline.trim().isEmpty()) {
                return new Response("Todos los campos son obligatorios y no pueden estar vacíos", Status.BAD_REQUEST);
            }

            // 2. Validación de formato del ID del avión (XXYYYYY)
            if (id.length() != 7 || !id.matches("^[A-Z]{2}\\d{5}$")) {
                return new Response("El ID del avión debe seguir el formato XXYYYYY donde:\n"
                        + "- XX son 2 letras mayúsculas\n"
                        + "- YYYYY son 5 dígitos numéricos", Status.BAD_REQUEST);
            }

            // 3. Validación de ID único
            if (planeStorage.getPlaneById(id) != null) {
                return new Response("El ID de avión ya existe", Status.BAD_REQUEST);
            }

            // 4. Validación de capacidad
            int capacity;
            try {
                capacity = Integer.parseInt(maxCapacity);
                if (capacity <= 0) {
                    return new Response("La capacidad máxima debe ser un número positivo", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("La capacidad máxima debe ser un número válido", Status.BAD_REQUEST);
            }

            // Crear y guardar el nuevo avión
            Plane newPlane = new Plane(id, brand, model, capacity, airline);

            if (planeStorage.addPlane(newPlane)) {
                return new Response("Avión registrado exitosamente", Status.CREATED, newPlane);
            } else {
                return new Response("Error al guardar avión", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todos los aviones ordenados y devolver clones (Prototype)
    public List<Plane> getAllPlanesSorted() {
        List<Plane> originals = planeStorage.getAllPlanes();
        List<Plane> clones = new ArrayList<>();
        for (Plane p : originals) {
            clones.add(p.clone());
        }
        return clones;
    }
}