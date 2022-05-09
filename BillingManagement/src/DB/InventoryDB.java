package DB;

import Model.Inventory;
import Model.Item.InventoryItem;

import java.util.HashMap;
import java.util.List;

public class InventoryDB {

    public int createInventory(Inventory inventory, List<List<String>> inventoryRecords){

        HashMap<String, InventoryItem> inventoryItemMap = inventory.getInventoryItemMap();

        int index =2;
        while (true){
            if(inventoryRecords.get(index).size()<4){
                break;
            }
            List<String> stringList = inventoryRecords.get(index);

            String itemName = stringList.get(0).strip().toLowerCase();
            String category = stringList.get(1);
            int qty = Integer.parseInt(stringList.get(2));
            double price = Double.parseDouble(stringList.get(3));

            InventoryItem item = new InventoryItem(itemName,qty, price, category);

            inventoryItemMap.put(itemName, item);
            index++;
        }
        inventory.setInventoryItemMap(inventoryItemMap);
        return index;
    }
}
