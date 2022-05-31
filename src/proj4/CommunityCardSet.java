package proj4;

import java.util.ArrayList;

public class CommunityCardSet implements Hand{

    public ArrayList<Card> communityCards;
    public final int HAND_SIZE = 5;

    public CommunityCardSet(ArrayList<Card> cardList) {
        this.communityCards = new ArrayList<>(cardList);

    }

    public void addCard(Card card) {
        if (this.communityCards.size() < HAND_SIZE) {
            this.communityCards.add(card);
        }
    }

    public Card getIthCard(int index){
        if(index > HAND_SIZE -1 || index < 0){
            return null;
        }
        return this.communityCards.get(index);
    }

    public String toString(){
        return String.valueOf(this.communityCards);
    }

}
