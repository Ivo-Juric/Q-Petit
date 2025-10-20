package com.qpetit.controllers;

public class ClientController implements ClientAdministrator{

    public ClientController() {
        clientDAO = new ClientDAO();
    }

    @Override
    public void createClient() {

    }

    @Override
    public void deleteClient() {

    }

    @Override
    public void updateClient() {

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
