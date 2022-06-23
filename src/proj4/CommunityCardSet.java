/**
 * represents the community cards
 */

package proj4;

import java.util.ArrayList;

public class CommunityCardSet implements Hand{

    public ArrayList<Card> communityCards;
    public final int COMMUNITY_HAND_SIZE = 5;

    /**
     * constructor, initializes the community cards
     * @param cardList a list of cards to be added to the community cards
     */
    public CommunityCardSet(ArrayList<Card> cardList) {
        this.communityCards = new ArrayList<>(cardList);
    }

    /**
     * adds a card to the hand
     * @param card a card to add
     */
    public void addCard(Card card) {
        if (this.communityCards.size() < COMMUNITY_HAND_SIZE) {
            this.communityCards.add(card);
        }
    }

    /**
     * gets the card at specified index
     * @param index index of a card to find
     * @return the card at specified index
     */
    public Card getIthCard(int index){
        if(index > COMMUNITY_HAND_SIZE -1 || index < 0){
            return null;
        }
        return this.communityCards.get(index);
    }

    public String toString(){
        return String.valueOf(this.communityCards);
    }

}
