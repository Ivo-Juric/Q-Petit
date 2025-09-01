package com.qpetit.entities;

public class Menu {
    private static double basePricexPerson;
    private int idMenu;
    private String menuName;
    private Category category;
    private boolean isOwned;
    private Supplier supplier;
    private int minNumberDiners;
    private boolean includeEntree;
    private boolean includeMainDish;
    private boolean includeDessert;
    private String observations;
    private Service service;

    public Menu(int idMenu, String menuName, Category category, boolean isOwned, Supplier supplier, int minNumberDiners,
                boolean includeEntree, boolean includeMainDish, boolean includeDessert, String observations) {
        this.idMenu = idMenu;
        this.menuName = menuName;
        this.category = category;
        this.isOwned = isOwned;
        this.supplier = supplier;
        this.minNumberDiners = minNumberDiners;
        this.includeEntree = includeEntree;
        this.includeMainDish = includeMainDish;
        this.includeDessert = includeDessert;
        this.observations = observations;
    }

    public static double getBasePricexPerson() {
        return basePricexPerson;
    }

    public static void setBasePricexPerson(double basePricexPerson) {
        Menu.basePricexPerson = basePricexPerson;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getMinNumberDiners() {
        return minNumberDiners;
    }

    public void setMinNumberDiners(int minNumberDiners) {
        this.minNumberDiners = minNumberDiners;
    }

    public boolean isIncludeEntree() {
        return includeEntree;
    }

    public void setIncludeEntree(boolean includeEntree) {
        this.includeEntree = includeEntree;
    }

    public boolean isIncludeMainDish() {
        return includeMainDish;
    }

    public void setIncludeMainDish(boolean includeMainDish) {
        this.includeMainDish = includeMainDish;
    }

    public boolean isIncludeDessert() {
        return includeDessert;
    }

    public void setIncludeDessert(boolean includeDessert) {
        this.includeDessert = includeDessert;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    
}
