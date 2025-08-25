package entitys;

public class Ingredient {
    private int  idIngredient;
    private String name;
    private int stock;
    private double pricePerUnit;

    public Ingredient(int idIngredient, String name, int stock, double pricePerUnit) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.stock = stock;
        this.pricePerUnit = pricePerUnit;
    }


    public int getIdIngredient() {
        return idIngredient;
    }


    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }


    public double getPricePerUnit() {
        return pricePerUnit;
    }


    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    
}
