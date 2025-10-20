package com.qpetit.controllers;

import com.qpetit.dao.ClientDAO;
import com.qpetit.entities.Client;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ClientController implements Administrator {
    private ClientDAO clientDAO;
    public ClientController() {
        clientDAO = new ClientDAO();
    }

    @Override
    public void create(Object o) {
        if (o instanceof Client) {
            try {
                Client client = (Client) o;
                clientDAO.insert(client);
                System.out.println("✅ Cliente agregado correctamente: " + client.getDNI());
            } catch (SQLException e) {
                System.err.println("❌ Error al insertar cliente: " + e.getMessage());
            }
        }
    }
    @Override
    public void delete(Object o) {
        if (o instanceof Client) {
            Client client = (Client) o;
            try {
                clientDAO.delete(client.getDNI());
                System.out.println("✅ Cliente eliminado correctamente: " + client.getDNI());
            } catch (SQLException e) {
                System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            }
        } else if (o instanceof String) { // también permite pasar solo el DNI
            try {
                clientDAO.delete((String) o);
                System.out.println("✅ Cliente eliminado correctamente: " + o);
            } catch (SQLException e) {
                System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            }
        } else {
            System.err.println("❌ Objeto no válido para eliminar (debe ser Client o DNI).");
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Client) {
            Client client = (Client) o;
            try {
                clientDAO.update(client);
                System.out.println("✅ Cliente actualizado correctamente: " + client.getDNI());
            } catch (SQLException e) {
                System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            }
        } else {
            System.err.println("❌ Objeto no válido para actualizar (no es un Client).");
        }
    }

    public Client getById(String dni) {
        try {
            return clientDAO.getById(dni);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar cliente por DNI: " + e.getMessage());
            return null;
        }
    }
    public List<Object> getAll() {
        try {
            return Collections.singletonList(clientDAO.getAll());
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener lista de clientes: " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

}
