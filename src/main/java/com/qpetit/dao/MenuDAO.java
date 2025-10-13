package com.qpetit.dao;

import com.qpetit.entities.Menu;
import com.qpetit.entities.Supplier;
import com.qpetit.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class MenuDAO {

    //IMPORTANTE: ¿Q pingo es la variable isOwned en la clase Menu? pq no esta en la tabla Menus

    private static final String OWNED_DESC = "OWNED";
    private static final String NOT_OWNED_DESC = "NOT_OWNED";

    /**
     * Insert a menu into Menus. Sets the generated menu_ID back on the menu object.
     * Throws SQLException if required MenuTypes/MenuCategories/Suppliers rows do not exist.
     */

    public void insert(Menu menu) throws SQLException {
        String sql = "INSERT INTO Menus " +
                "(name, category_id, menu_type_id, supplier_id, base_price, min_guest_count, is_starter, is_main_course, is_dessert, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, menu.getMenuName());

            // category: strict (throw if not exists)
            if (menu.getCategory() != null) {
                Integer categoryId = getCategoryIdStrict(conn, menu.getCategory());
                stmt.setInt(2, categoryId);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            // menu_type -> strict: throw if not exists
            Integer menuTypeId = getMenuTypeIdStrict(conn, menu.isOwned());
            stmt.setInt(3, menuTypeId);

            if (menu.getSupplier() != null) {
                // strict supplier lookup
                Integer supplierId = menu.getSupplier().getIdSupplier();
                // verify exists (and optionally load full supplier if needed)
                getSupplierByIdStrict(conn, supplierId);
                stmt.setInt(4, supplierId);
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            // base price comes from static Menu.basePricexPerson
            stmt.setBigDecimal(5, BigDecimal.valueOf(Menu.getBasePricexPerson()));

            stmt.setInt(6, menu.getMinNumberDiners());
            stmt.setBoolean(7, menu.isIncludeEntree());
            stmt.setBoolean(8, menu.isIncludeMainDish());
            stmt.setBoolean(9, menu.isIncludeDessert());
            stmt.setString(10, menu.getObservations());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    menu.setIdMenu(rs.getInt(1));
                }
            }
        }
    }

    /**
     * Return all menus.
     * Will throw SQLException if referential rows (category/menu type/supplier) are missing or invalid.
     */

    public List<Menu> getAll() throws SQLException {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM Menus";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                menus.add(mapRowToMenu(conn, rs));
            }
        }
        return menus;
    }

    /**
     * Get a menu by id.
     * Will throw SQLException if referential rows are missing/invalid.
     */
    public Menu getById(int id) throws SQLException {
        Menu menu = null;
        String sql = "SELECT * FROM Menus WHERE menu_id = ?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    menu = mapRowToMenu(conn, rs);
                }
            }
        }
        return menu;
    }

    /**
     * Update an existing menu.
     * Throws SQLException if required MenuTypes/MenuCategories/Suppliers rows do not exist.
     */
    public void update(Menu menu) throws SQLException {
        String sql = "UPDATE Menus SET name=?, category_id=?, menu_type_id=?, supplier_id=?, base_price=?, min_guest_count=?, is_starter=?, is_main_course=?, is_dessert=?, notes=? " +
                "WHERE menu_id=?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, menu.getMenuName());

            if (menu.getCategory() != null) {
                Integer categoryId = getCategoryIdStrict(conn, menu.getCategory());
                stmt.setInt(2, categoryId);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            Integer menuTypeId = getMenuTypeIdStrict(conn, menu.isOwned());
            stmt.setInt(3, menuTypeId);

            if (menu.getSupplier() != null) {
                Integer supplierId = menu.getSupplier().getIdSupplier();
                getSupplierByIdStrict(conn, supplierId);
                stmt.setInt(4, supplierId);
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setBigDecimal(5, BigDecimal.valueOf(Menu.getBasePricexPerson()));
            stmt.setInt(6, menu.getMinNumberDiners());
            stmt.setBoolean(7, menu.isIncludeEntree());
            stmt.setBoolean(8, menu.isIncludeMainDish());
            stmt.setBoolean(9, menu.isIncludeDessert());
            stmt.setString(10, menu.getObservations());

            stmt.setInt(11, menu.getIdMenu());

            stmt.executeUpdate();
        }
    }

    /**
     * Delete menu by id.
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Menus WHERE menu_id = ?";
        try (Connection conn = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Map a ResultSet row to a Menu object.
     * Uses strict helpers: if referenced MenuTypes/MenuCategories/Suppliers rows are missing,
     * this method will throw SQLException.
     */
    private Menu mapRowToMenu(Connection conn, ResultSet rs) throws SQLException {
        int menuId = rs.getInt("menu_Id");
        String name = rs.getString("name");

        Category category = null;
        int categoryId = rs.getInt("category_Id");
        if (!rs.wasNull()) {
            category = getCategoryEnumFromIdStrict(conn, categoryId);
        }

        // menu_type -> read description from MenuTypes and map to boolean (strict)
        boolean isOwned = false;
        int menuTypeId = rs.getInt("menu_type_Id");
        if (!rs.wasNull()) {
            String desc = getMenuTypeDescriptionByIdStrict(conn, menuTypeId);
            isOwned = desc.equalsIgnoreCase(OWNED_DESC);
        }

        Supplier supplier = null;
        int supplierId = rs.getInt("supplier_Id");
        if (!rs.wasNull()) {
            supplier = getSupplierByIdStrict(conn, supplierId);
        }

        BigDecimal basePriceBD = rs.getBigDecimal("base_price");
        if (basePriceBD != null) {
            Menu.setBasePricexPerson(basePriceBD.doubleValue());
        }

        int minGuestCount = rs.getInt("min_guest_count");
        boolean isStarter = rs.getBoolean("is_starter");
        boolean isMainCourse = rs.getBoolean("is_main_course");
        boolean isDessert = rs.getBoolean("is_dessert");
        String notes = rs.getString("notes");

        return new Menu(menuId, name, category, isOwned, supplier, minGuestCount,
                isStarter, isMainCourse, isDessert, notes);
    }

    /**
     * Strict: return Supplier object by id (loads name and contact).
     * Throws SQLException if supplier not found.
     */
    private Supplier getSupplierByIdStrict(Connection conn, int supplierId) throws SQLException {
        String sql = "SELECT supplier_id, name, contact FROM Suppliers WHERE supplier_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("supplier_Id");
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    return new Supplier(id, name, contact);
                }
            }
        }
        throw new SQLException("Supplier with supplier_Id=" + supplierId + " not found in Suppliers table.");
    }

    /**
     * Strict: return existing category_ID for given Category enum.
     * Throws SQLException if not found (no automatic creation).
     */
    private Integer getCategoryIdStrict(Connection conn, Category category) throws SQLException {
        String selectSql = "SELECT category_id FROM MenuCategories WHERE description = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setString(1, category.name());
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("category_Id");
                }
            }
        }
        throw new SQLException("MenuCategories entry with description '" + category.name() + "' not found. Create it first or use an existing category.");
    }

    /*
     * Strict: given a category_ID, return the Category enum (by matching MenuCategories to enum name).
     * Throws SQLException if the category row is missing or if description doesn't match the enum.
     */
    private Category getCategoryEnumFromIdStrict(Connection conn, int categoryId) throws SQLException {
        String sql = "SELECT description FROM MenuCategories WHERE category_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String desc = rs.getString("description");
                    try {
                        return Category.valueOf(desc);
                    } catch (IllegalArgumentException ex) {
                        throw new SQLException("MenuCategories.description '" + desc + "' does not match Category enum.");
                    }
                }
            }
        }
        throw new SQLException("MenuCategories with category_Id=" + categoryId + " not found.");
    }

    /**
     * Strict: return the MenuTypes.type_ID for the boolean isOwned.
     * Throws SQLException if the description is not present in MenuTypes.
     */
    private Integer getMenuTypeIdStrict(Connection conn, boolean isOwned) throws SQLException {
        String targetDesc = isOwned ? OWNED_DESC : NOT_OWNED_DESC;

        String selectSql = "SELECT type_id FROM MenuTypes WHERE description = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setString(1, targetDesc);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("type_ID");
                }
            }
        }

        // Not found -> fail hard
        throw new SQLException("MenuTypes entry with description '" + targetDesc + "' not found. " +
                "Create it first (INSERT INTO MenuTypes(description) VALUES('" + targetDesc + "')) or adjust OWNED_DESC/NOT_OWNED_DESC.");
    }

    /**
     * Strict: Return MenuTypes.description for the provided type_ID (or throw).
     */
    private String getMenuTypeDescriptionByIdStrict(Connection conn, int typeId) throws SQLException {
        String sql = "SELECT description FROM MenuTypes WHERE type_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("description");
                }
            }
        }
        throw new SQLException("MenuTypes with type_ID=" + typeId + " not found.");
    }
}
