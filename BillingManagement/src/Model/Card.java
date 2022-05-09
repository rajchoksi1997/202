package Model;

import java.util.HashSet;

public class Card {
    private static HashSet<Double> cards;

    private static Card object = null;

    private Card(){

    }

    public static Card getInstance(){
        cards = new HashSet<>();
        if (object == null)
            object = new Card();
        return object;
    }

    public HashSet<Double> getCards() {
        return cards;
    }

    public void setCards(HashSet<Double> cards) {
        this.cards = cards;
    }
}
