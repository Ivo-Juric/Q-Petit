package com.qpetit.controllers;

import com.qpetit.dao.MenuDAO;
import com.qpetit.entities.Menu;
import java.util.List;

public class MenuController implements Administrator {

    private MenuDAO mdao;

    public MenuController() {
        mdao = new MenuDAO();
    }

    @Override
    public void create(Object o) {
        try {
            if (o instanceof Menu) {
                Menu menu = (Menu) o;
                mdao.create(menu);
            } else {
                System.out.println("❌ Error: el objeto no es una instancia válida de Menu.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ ¡ERROR al crear el menú!");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        try {
            if (o instanceof Menu) {
                Menu menu = (Menu) o;
                mdao.update(menu);
            } else {
                System.out.println("❌ Error: el objeto no es una instancia válida de Menu.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ ¡ERROR al actualizar el menú!");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        try {
            if (o instanceof Menu) {
                Menu menu = (Menu) o;
                mdao.delete(menu.getIdMenu());
            } else {
                System.out.println("❌ Error: el objeto no es una instancia válida de Menu.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ ¡ERROR al eliminar el menú!");
            e.printStackTrace();
        }
    }

    public List<Menu> getAll() {
        return mdao.getAll();
    }

    public Menu getById(int idMenu) {
        return mdao.getById(idMenu);
    }
}
