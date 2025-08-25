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

    public int getIdSupply() {
        return idSupply;
    }

    public void setIdSupply(int idSupply) {
        this.idSupply = idSupply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    
}
