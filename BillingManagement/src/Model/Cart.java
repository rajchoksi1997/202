package Model;

import Model.Item.CartItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<CartItem> cartItemList;

    private Double cardNumber;
    private static Cart object= null;
    private Cart(){}

    public static Cart getInstance(){
        cartItemList = new ArrayList<>();
        if(object==null){
            object = new Cart();
        }
        return object;
    }

    public static List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public static void setCartItemList(List<CartItem> cartItemList) {
        Cart.cartItemList = cartItemList;
    }

    public Double getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Double cardNumber) {
        this.cardNumber = cardNumber;
    }
}
