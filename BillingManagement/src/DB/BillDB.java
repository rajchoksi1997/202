package DB;

import Model.Cart;
import Model.Inventory;
import Model.Item.CartItem;
import Model.Item.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class BillDB {

    public List<List<String>> createBill(Cart cart, Inventory inventory){
        List<List<String>> records = new ArrayList<>();
        List<String> column = new ArrayList<>();

        column.add("Item");
        column.add("Quantity");
        column.add("Price");
        column.add("TotalPrice");

        records.add(column);

        double totalPrice = 0.0;

        for(CartItem item: cart.getCartItemList()){
            List<String> values = new ArrayList<>();
            String itemName = item.getName();
            int qty = item.getQuantity();

            InventoryItem inventoryItem = inventory.getInventoryItemMap().get(itemName.toLowerCase().strip());

            double itemPrice = qty*inventoryItem.getPrice();
            totalPrice = totalPrice + itemPrice;
            values.add(itemName);
            values.add(String.valueOf(qty));
            values.add(String.valueOf(itemPrice));

            records.add(values);

        }
        records.get(1).add(String.valueOf(totalPrice));

        return records;
    }
}
