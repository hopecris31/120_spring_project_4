package proj4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * represents a deck object
 */

public class Deck {
    //ask about "field can be converted to local variable, when i declared private cards up here, moved to createDeck
    private ArrayList<Card> deck;
    private int nextToDeal;
    public final String[] SUITS_STRING = {"Spades", "Hearts", "Diamonds", "Clubs"};
    public final String[] RANKS_STRING = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    public final int[] RANKS_INT = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    public final int[] SUITS_INT = {0, 1, 2, 3};

    /**
     * deck constructor
     */
    public Deck(){
        this.nextToDeal = 0;
        this.deck = createDeck(RANKS_STRING, SUITS_STRING);
        this.deck = createDeck(RANKS_INT, SUITS_INT);
    }


    private ArrayList<Card> createDeck(String[] ranks, String[] suits){
        ArrayList<Card> cards = new ArrayList<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                Card card = new Card(rank, suit);
                cards.add(card);}
        }
        return cards;
    }

    private ArrayList<Card> createDeck(int[] ranks, int[] suits){
        ArrayList<Card> cards = new ArrayList<>();
        for (int rank : ranks) {
            for (int suit : suits) {
                Card card = new Card(rank, suit);
                cards.add(card);}
        }
        return cards;
    }

    /**
     * shuffles the deck
     */
    public void shuffle(){
        int deckSize = this.deck.size();
        for (int i = 0; i < deckSize ; i++){
            int swapIndex = ThreadLocalRandom.current().nextInt(0, deckSize);
            Collections.swap(this.deck, i, swapIndex);
        }
    }

    /**
     * checks to see of there is enough undealt cards to create a hand of given size
     * @param handSize number of cards in a hand
     * @return True if enough cards, false if not
     */
    public boolean enoughInDeck(int handSize) { //if nextToDeal is at end, return false
        return this.size() >= handSize;
    }

    public boolean isEmpty(){
        return this.nextToDeal != this.deck.size();
    }


    /**
     * deals the next card from the "top" of the deck
     * @return a single card
     */
    public Card deal() {
        if (this.nextToDeal == this.deck.size()){ //😍😍😍
            return null;}
        else{
            Card dealtCard = deck.get(nextToDeal);
            this.nextToDeal++;
            return dealtCard;
        }
    }

    /**
     * gets the remaining undealt cards in the deck
     * @return number of undealt cards
     */
    public int size(){
        return this.deck.size() - this.nextToDeal;
    }

    /**
     * resets the deck, all cards are undealt
     */
    public void gather(){
        this.nextToDeal = 0;
    }

    public String toString(){ //sublist of undealt cards
        return String.valueOf(this.deck.subList(nextToDeal, deck.size()));
    }
}
