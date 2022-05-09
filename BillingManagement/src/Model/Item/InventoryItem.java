package Model.Item;

import Model.Item.Item;

public class InventoryItem extends Item {
    private double price;
    private String category;

    public InventoryItem(String name,int quantity,double price, String category) {
        this.price = price;
        this.category = category;
        super.name=name;
        super.quantity=quantity;
    }

    public String getName(){
        return super.name;
    }

    public int getQuantity(){
        return super.quantity;
    }

    public void setName(String name){
        super.name = name;
    }

    public void setQuantity(int quantity){
        super.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
