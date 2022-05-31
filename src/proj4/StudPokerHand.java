package proj4;

import java.util.ArrayList;
import java.util.Collections;


public class StudPokerHand implements Hand{

    public final int TARGET_LENGTH = 5; // the size of desired hand

    public ArrayList<Card> studHand;
    public CommunityCardSet cc;
    public final int STUD_HAND_SIZE = 2;
    ArrayList<Card> allCards = new ArrayList<>();

    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        ArrayList<Card> cardsCopy = new ArrayList<>();
        Collections.copy(cardsCopy, cardList);
        this.studHand = cardsCopy;
        this.cc = cc;
    }

    public void addCard(Card card) {
        if (this.studHand.size() < STUD_HAND_SIZE) {
            this.studHand.add(card);
        }
    }

    public Card getIthCard(int index){
        if(index > STUD_HAND_SIZE -1 || index < 0){
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
    public int compareTo(StudPokerHand other){
        PokerHand thisBest = this.getBestFiveCardHand();
        PokerHand otherBest = other.getBestFiveCardHand();
        return thisBest.compareTo(otherBest);
    }

    private ArrayList<Card> getAllCards(){ //gets all cards to be used in getAllFiveCardHands

        for(Card card : this.cc.communityCards){ // do this for when an object contains arraylist "unpack"
            allCards.add(card);
        }
        for(Card card : this.studHand){
            allCards.add(card);
        }
        return allCards;
    }

    private static ArrayList<ArrayList<Card>> getAllCombos(ArrayList<Card> allHandCards, int targetLength){
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>();

        if(targetLength == 1){
            makeSingleCombo(allHandCards, allCombos); //makes a combo of size 1
        }
        else if(targetLength == allHandCards.size()){ // makes a combo of size target length
            makeOwnCombo(allHandCards, allCombos);
        }
        else{
            Card firstCard = allHandCards.get(0);
            ArrayList<Card> rest = new ArrayList<>(allHandCards.subList(1, allHandCards.size()));
            getCombosWithFirst(rest, targetLength, firstCard, allCombos);
            getCombosWithoutFirst(rest, targetLength, allCombos);
        }
        return allCombos;
    }

    private static void getCombosWithoutFirst(ArrayList<Card> rest, int targetLength, ArrayList<ArrayList<Card>> allCombos){
        ArrayList<ArrayList<Card>> combos = getAllCombos(rest, targetLength); // recursive call
        allCombos.addAll(combos);
    }

    private static void getCombosWithFirst(ArrayList<Card> rest, int targetLength, Card cardToAdd, ArrayList<ArrayList<Card>> allCombos){
        ArrayList<ArrayList<Card>> combos = getAllCombos(rest, targetLength-1); // recursive call
        for(ArrayList<Card> combo : combos){
            combo.add(cardToAdd);
        }
        allCombos.addAll(combos);
    }

    private static void makeSingleCombo(ArrayList<Card> cardList, ArrayList<ArrayList<Card>> allCombos){
        for(Card card : cardList){
            ArrayList<Card> singleCombo = new ArrayList<>();
            singleCombo.add(card);
            allCombos.add(singleCombo);
        }
    }

    private static void makeOwnCombo(ArrayList<Card> allHandCards, ArrayList<ArrayList<Card>> allCombos){
        ArrayList<Card> handCardsCopy = new ArrayList<>(allHandCards);
        allCombos.add(handCardsCopy);
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
