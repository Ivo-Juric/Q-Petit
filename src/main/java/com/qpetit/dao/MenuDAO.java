package com.qpetit.dao;

import com.qpetit.entities.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    // Simulación de base de datos en memoria
    private List<Menu> menuList = new ArrayList<>();

    // CREATE
    public void create(Menu menu) {
        menuList.add(menu);
        System.out.println("✅ Menú agregado correctamente: " + menu.getMenuName());
    }

    // UPDATE
    public void update(Menu menu) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getIdMenu() == menu.getIdMenu()) {
                menuList.set(i, menu);
                System.out.println("✅ Menú actualizado correctamente: " + menu.getMenuName());
                return;
            }
        }
        System.out.println("⚠️ No se encontró el menú con ID: " + menu.getIdMenu());
    }

    // DELETE
    public void delete(int idMenu) {
        boolean removed = menuList.removeIf(menu -> menu.getIdMenu() == idMenu);
        if (removed) {
            System.out.println("🗑️ Menú eliminado correctamente (ID: " + idMenu + ")");
        } else {
            System.out.println("⚠️ No se encontró el menú con ID: " + idMenu);
        }
    }

    // READ ALL
    public List<Menu> getAll() {
        return new ArrayList<>(menuList);
    }

    // READ BY ID
    public Menu getById(int idMenu) {
        for (Menu menu : menuList) {
            if (menu.getIdMenu() == idMenu) {
                return menu;
            }
        }
        return null;
    }
}
