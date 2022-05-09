package Model;

import Model.Item.InventoryItem;

import java.util.HashMap;

public class Inventory {
    private static HashMap<String, InventoryItem> inventoryItemMap;
    private static Inventory object = null;
    private Inventory(){

    }
    public static Inventory getInstance(){
        inventoryItemMap = new HashMap<>();
        if (object == null)
            object = new Inventory();
        return object;
    }

    public static HashMap<String, InventoryItem> getInventoryItemMap() {
        return inventoryItemMap;
    }

    public static void setInventoryItemMap(HashMap<String, InventoryItem> inventoryItemMap) {
        Inventory.inventoryItemMap = inventoryItemMap;
    }
}
