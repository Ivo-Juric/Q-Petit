package entitys;

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
}
