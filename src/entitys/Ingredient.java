package entitys;

public class Ingredient {
    private int  idIngredient;
    private String name;
    private int stock;
    private double unitPrice;

    public Ingredient(int idIngredient, String name, int stock, double unitPrice) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.stock = stock;
        this.unitPrice = unitPrice;
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


    public double getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }



    
}
