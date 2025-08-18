package entitys;

public class Supply {
    private int idSupply;
    private String name;
    private String brand;
    private int availableQuantity;

    public Supply(int idSupply, String name, String brand, int availableQuantity) {
        this.idSupply = idSupply;
        this.name = name;
        this.brand = brand;
        this.availableQuantity = availableQuantity;
    }
}
