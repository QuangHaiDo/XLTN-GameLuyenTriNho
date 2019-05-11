package Objects;

import java.util.ArrayList;

public class CardList {

    ArrayList<Card> List = new ArrayList<Card>();

    public ArrayList<Card> getList() {
        return List;
    }

    public void addCard(Card b) {
        List.add(b);
    }

    public void generateCardList(int n){
        this.List.clear();
        for (int i=0;i<n;i++){
            //this.addCard(new Card());
            List.add(new Card());
        }
    }

    public void showCardList(){
        for (Card b : List){
            System.out.print( List.indexOf(b) +" " + b.getValue()+" "+ b.getSuit() +" "+ b.getColor()+" ");
        }
        System.out.println();
    }

}
