/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.models.Plane;
import airport.models.storages.PlaneStorage;

public class PlaneController {
    private final PlaneStorage planeStorage;

    public PlaneController() throws Exception {
        this.planeStorage = PlaneStorage.getInstance();
    }

    public Response registerPlane(String id, String brand, String model, String maxCapacity, String airline) {
        try {
            if (id == null || id.trim().isEmpty()) {
                return new Response("El campo ID es obligatorio", Status.BAD_REQUEST);
            }
            if (brand == null || brand.trim().isEmpty()) {
                return new Response("El campo Marca es obligatorio", Status.BAD_REQUEST);
            }
            if (model == null || model.trim().isEmpty()) {
                return new Response("El campo Modelo es obligatorio", Status.BAD_REQUEST);
            }
            if (airline == null || airline.trim().isEmpty()) {
                return new Response("El campo Aerolínea es obligatorio", Status.BAD_REQUEST);
            }
            if (maxCapacity == null || maxCapacity.trim().isEmpty()) {
                return new Response("El campo Capacidad Máxima es obligatorio", Status.BAD_REQUEST);
            }
            if (planeStorage.getPlaneById(id) != null) {
                return new Response("El ID de avión ya existe", Status.BAD_REQUEST);
            }
            int capacity = Integer.parseInt(maxCapacity);
            if (capacity <= 0) {
                return new Response("Capacidad debe ser positiva", Status.BAD_REQUEST);
            }

            Plane newPlane = new Plane(id, brand, model, capacity, airline);

            if (planeStorage.addPlane(newPlane)) {
                return new Response("Avión registrado exitosamente", Status.CREATED, newPlane);
            } else {
                return new Response("Error al guardar avión", Status.INTERNAL_SERVER_ERROR);
            }

        } catch (NumberFormatException e) {
            return new Response("Capacidad debe ser un número válido", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Error inesperado: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}