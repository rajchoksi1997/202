package DB;

import Model.Card;

import java.util.HashSet;
import java.util.List;

public class CardDB {

    public Card addCards(Card cardList,int index, List<List<String>> records){

        while (index<records.size()){

            if(records.get(index).size()>0 && records.get(index).get(0).equalsIgnoreCase("cardnumber")){
                break;
            }
            index++;
        }

        HashSet set = new HashSet<>();
        index++;
        while (index<records.size()){
            Double d = Double.valueOf(records.get(index).get(0));
            set.add(d);
            index++;
        }
        cardList.setCards(set);
        return cardList;

    }


}
