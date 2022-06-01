package proj4;

import java.util.ArrayList;
import java.util.Collections;


public class StudPokerHand implements Hand {

    public final int TARGET_LENGTH = 5; // the size of desired hand

    public ArrayList<Card> studHand;
    public CommunityCardSet cc;
    public final int STUD_HAND_SIZE = 2;
    ArrayList<Card> allCards = new ArrayList<>();

    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        ArrayList<Card> cardsCopy = new ArrayList<>(cardList);
        this.studHand = cardsCopy;
        this.cc = cc;
    }

    public void addCard(Card card) {
        if (this.studHand.size() < STUD_HAND_SIZE) {
            this.studHand.add(card);
        }
    }

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

    public ArrayList<Card> getAllCards() { //gets all cards to be used in getAllFiveCardHands

        // do this for when an object contains arraylist "unpack"
        allCards.addAll(this.cc.communityCards);
        allCards.addAll(this.studHand);
        return allCards;
    }

    public static ArrayList<ArrayList<Card>> getAllCombos(ArrayList<Card> allHandCards, int targetLength) {
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>();

        if (targetLength == 1) {
            ArrayList<ArrayList<Card>>singleCombos = makeSingleCombo(allHandCards);
            allCombos.addAll(singleCombos);
            }
            //makes a combo of size 1
        else if (targetLength == allHandCards.size()) { // makes a combo of size target length
            ArrayList<Card> ownCombo = makeOwnCombo(allHandCards);
            allCombos.add(ownCombo);
        } else {
            Card firstCard = allHandCards.get(0);
            ArrayList<Card> rest = new ArrayList<>(allHandCards.subList(1, allHandCards.size()));
            ArrayList<ArrayList<Card>> combosWithFirst = getCombosWithFirst(rest, targetLength, firstCard);
            allCombos.addAll(combosWithFirst);
            ArrayList<ArrayList<Card>> combosWithoutFirst = getCombosWithoutFirst(rest, targetLength);
            allCombos.addAll(combosWithoutFirst);
        }
        return allCombos;
    }

    private static ArrayList<ArrayList<Card>> getCombosWithoutFirst(ArrayList<Card> rest, int targetLength) {
        return getAllCombos(rest, targetLength);
    }

    private static ArrayList<ArrayList<Card>> getCombosWithFirst(ArrayList<Card> rest, int targetLength, Card cardToAdd) {
        ArrayList<ArrayList<Card>> combos = getAllCombos(rest, targetLength - 1); // recursive call
        for (ArrayList<Card> combo : combos) {
            combo.add(cardToAdd);
        }
        return combos;
    }

    public static ArrayList<ArrayList<Card>> makeSingleCombo(ArrayList<Card> cardList) {
        ArrayList<ArrayList<Card>> allSingles = new ArrayList<>();
        for (Card card : cardList) {
            ArrayList<Card> singleCombo = new ArrayList<>();
            singleCombo.add(card);
            allSingles.add(singleCombo);
    }
        return allSingles;
}

    private static ArrayList<Card> makeOwnCombo(ArrayList<Card> allHandCards){
        return new ArrayList<>(allHandCards);
    }


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


    public String toString(){
        return String.valueOf(this.studHand);
    }

}
