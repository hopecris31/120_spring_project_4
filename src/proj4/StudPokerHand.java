/**
 * represents a stud poker hand
 */

package proj4;

import java.util.ArrayList;


public class StudPokerHand implements Hand {

    public final int TARGET_LENGTH = 5; // the size of desired hand
    public final int STUD_HAND_SIZE = 2;
    private ArrayList<Card> studHand;
    private CommunityCardSet cc;
    ArrayList<Card> allCards = new ArrayList<>();

    /**
     * StudPokerHand Constructor
     * @param cc, a set of community cards
     * @param cardList, a list of cards for the stud hand
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        ArrayList<Card> cardsCopy = new ArrayList<>(cardList);
        this.studHand = cardsCopy;
        this.cc = cc;
    }

    /**
     * adds a card to the hand
     * @param card a card to add
     */
    public void addCard(Card card) {
        if (this.studHand.size() < STUD_HAND_SIZE) {
            this.studHand.add(card);
        }
    }

    /**
     * get the card at specified index
     * @param index an index of a card
     * @return the index of the card
     */
    public Card getIthCard(int index) {
        if (index > STUD_HAND_SIZE - 1 || index < 0) {
            return null;
        }
        return this.studHand.get(index);
    }

    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */
    public int compareTo(StudPokerHand other) {
        PokerHand thisBest = this.getBestFiveCardHand();
        PokerHand otherBest = other.getBestFiveCardHand();
        return thisBest.compareTo(otherBest);
    }

    /**
     * adds all the community cards and stud hand cards into one list
     * @return ArrayList of all cards
     */
    private ArrayList<Card> getAllCards() { //gets all cards to be used in getAllFiveCardHands

        // do this for when an object contains arraylist "unpack"
        allCards.addAll(this.cc.communityCards);
        allCards.addAll(this.studHand);
        return allCards;
    }

    /**
     * gets all combinations from a list of cards
     * @param allHandCards the cards of which combinations are to be made from
     * @param targetLength the length of the combination to be made
     * @return an ArrayList containing every possible combination, each combo represented as an ArrayList
     */
    private static ArrayList<ArrayList<Card>> getAllCombos(ArrayList<Card> allHandCards, int targetLength) {
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>();
        if (targetLength == 1) {
            ArrayList<ArrayList<Card>>singleCombos = makeSingleCombo(allHandCards);
            allCombos.addAll(singleCombos);
            }
        else if (targetLength == allHandCards.size()) {
            ArrayList<Card> ownCombo = makeOwnCombo(allHandCards);
            allCombos.add(ownCombo);
            }
        else {
            Card firstCard = allHandCards.get(0);
            ArrayList<Card> rest = new ArrayList<>(allHandCards.subList(1, allHandCards.size()));
            ArrayList<ArrayList<Card>> combosWithFirst = getCombosWithFirst(rest, targetLength, firstCard);
            allCombos.addAll(combosWithFirst);
            ArrayList<ArrayList<Card>> combosWithoutFirst = getCombosWithoutFirst(rest, targetLength);
            allCombos.addAll(combosWithoutFirst);
        }
        return allCombos;
    }

    /**
     * gets all combinations from a given list
     * @param rest the rest of a list, missing the first element
     * @param targetLength the length of the combination to be made
     * @returnan ArrayList containing every possible combination, each combo represented as an ArrayList
     */
    private static ArrayList<ArrayList<Card>> getCombosWithoutFirst(ArrayList<Card> rest, int targetLength) {
        return getAllCombos(rest, targetLength);
    }

    /**
     * gets all combinations of a list, then adds cardToAdd to all combinations
     * @param rest the rest of a list, not containing the first element (this is the cardToAdd)
     * @param targetLength the length of the combination to be made
     * @param cardToAdd card to add to all combos after combos have been made
     * @return ArrayList containing every possible combination, each combo represented as an ArrayList
     */
    private static ArrayList<ArrayList<Card>> getCombosWithFirst(ArrayList<Card> rest, int targetLength, Card cardToAdd) {
        ArrayList<ArrayList<Card>> combos = getAllCombos(rest, targetLength - 1); // recursive call
        for (ArrayList<Card> combo : combos) {
            combo.add(cardToAdd);
        }
        return combos;
    }

    /**
     * Makes a combination of size 1, containing a single card
     * @param cardList a list of cards
     * @return ArrayList containing every combination, each combo represented as an ArrayList containing a single card
     */
    private static ArrayList<ArrayList<Card>> makeSingleCombo(ArrayList<Card> cardList) {
        ArrayList<ArrayList<Card>> allSingles = new ArrayList<>();
        for (Card card : cardList) {
            ArrayList<Card> singleCombo = new ArrayList<>();
            singleCombo.add(card);
            allSingles.add(singleCombo);
    }
        return allSingles;
}

    /**
     * makes a single combination of the size of the list, all cards in the list are a single combo
     * @param allHandCards a list containing cards
     * @return ArrayList containing the single combination, the combo represented as an ArrayList containing all hand cards
     */
    private static ArrayList<Card> makeOwnCombo(ArrayList<Card> allHandCards){
        return new ArrayList<>(allHandCards);
    }


    /**
     * Given all of the possible hands from stud and community cards, returns the best possible hand
     * @return the best possible poker hand possible from all cards
     */
    private PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);

        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }

    /**
     * given all of the community cards and stud hand cards, creates every possible 5 card hand
     * @return a list of all the possible poker hands
     */
    private ArrayList<PokerHand> getAllFiveCardHands(){
        ArrayList<Card> allCards = getAllCards();
        ArrayList<ArrayList<Card>> allCombos = getAllCombos(allCards, TARGET_LENGTH);
        ArrayList<PokerHand> handCombos = new ArrayList<>();

        for(ArrayList<Card> cardList : allCombos){
            PokerHand hand = new PokerHand(cardList);
            handCombos.add(hand);
        }
        return handCombos;
    }

    /**
     * checks the equality of a StudPokerHand object
     * @param other another object to compare
     * @return True if same object, False if else
     */
    public boolean equals(StudPokerHand other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof StudPokerHand)) {
            return false;
        } else {
            StudPokerHand otherStudHand = (StudPokerHand) other;
            return this.studHand == otherStudHand.studHand;
        }
    }

    @Override
    public String toString(){
        String shString = String.valueOf(this.studHand);
        return shString;
    }

}
