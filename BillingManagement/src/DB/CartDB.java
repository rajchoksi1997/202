package DB;

import Model.Cart;
import Model.Inventory;
import Model.Item.CartItem;
import Model.Item.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDB {

    public Cart createCart(Cart cart,List<List<String>> records){

        List<CartItem> cartItemsList = new ArrayList<>();
        Double cardNumber = null;
        int index = 1;
        while (index<records.size()){
            List<String> record = records.get(index);

            if(record.size()==3){
                cardNumber = Double.valueOf(record.get(2));
            }
            String itemName = record.get(0);
            int qty = Integer.parseInt(record.get(1));
            CartItem item = new CartItem(itemName, qty);

            cartItemsList.add(item);

            index++;
        }
        cart.setCartItemList(cartItemsList);
        cart.setCardNumber(cardNumber);
        return cart;

    }

    public List<CartItem> validateCart(Cart cart, Inventory inventory, int maxEssential, int maxLuxury, int maxMisc){

        List<CartItem> invalidItems = new ArrayList<>();
        int essentialItems = 0;
        int luxuryItems = 0;
        int miscItems = 0;
        List<CartItem> cartItemList = cart.getCartItemList();
        for(CartItem item: cartItemList){
            String cartItemName = item.getName().strip().toLowerCase();
            int itemQty = item.getQuantity();

            InventoryItem inventoryItem = inventory.getInventoryItemMap().get(cartItemName);

            if(inventoryItem.getQuantity()<itemQty){
                invalidItems.add(item);
            }else{

                if(inventoryItem.getCategory().equalsIgnoreCase("Essentials")){
                    essentialItems+=itemQty;
                    if(essentialItems>maxEssential){
                        invalidItems.add(item);
                    }
                }else if(inventoryItem.getCategory().equalsIgnoreCase("Luxury")){
                    luxuryItems+=itemQty;
                    if(luxuryItems>maxLuxury){
                        invalidItems.add(item);
                    }
                }else if(inventoryItem.getCategory().equalsIgnoreCase("Misc")){
                    miscItems+=itemQty;
                    if(miscItems>maxMisc){
                        invalidItems.add(item);
                    }
                }
            }
        }
        return invalidItems;
    }


}
