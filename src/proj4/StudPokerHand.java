package proj4;

import java.util.ArrayList;


public class StudPokerHand {

    public final int TARGET_LENGTH = 5;

    public ArrayList<Card> studHand;
    public CommunityCardSet cc;
    public final int HAND_SIZE = 2;

    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        this.studHand = new ArrayList<>(cardList);
        this.cc = cc;
    }

    public void addCard(Card card) {
        if (this.studHand.size() < HAND_SIZE) {
            this.studHand.add(card);
        }
    }

    public Card getIthCard(int index){
        if(index > HAND_SIZE -1 || index < 0){
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

    private ArrayList<PokerHand> getAllFiveCardHands(ArrayList<Card> cards, int targetLength){
        ArrayList<ArrayList <Card>> allCombos = getAllCombos(cards, targetLength);
        ArrayList<PokerHand> handCombos = new ArrayList<>();

        for(ArrayList<Card> cardList : allCombos){
            PokerHand hand = new PokerHand(cardList);
            handCombos.add(hand);
        }
        return handCombos;
    }

    private ArrayList<Card> getAllCards(){
        ArrayList<Card> allCards = new ArrayList<>();
        for(Card card : this.cc.communityCards){ // do this for when an object contains arraylist "unpack"
            allCards.add(card);
        }
        for(Card card : this.studHand){
            allCards.add(card);
        }
        return allCards;
    }


    private static ArrayList<ArrayList <Card>> getAllCombos(ArrayList<Card> hand, int targetLength){
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>();
        if(targetLength == 1){
            getSingleCombos(hand, allCombos);
        }
        else if(targetLength == hand.size()){
            makeOwnCombo(hand, allCombos);
        }
        else{
            Card firstCard = hand.get(0);
            ArrayList<Card> rest = new ArrayList<>(hand.subList(1, hand.size()));
            getCombosWithFirst(rest, targetLength, allCombos, firstCard);
            getCombosWithoutFirst(rest, targetLength, allCombos);
        }
        return allCombos;
    }

    private static void getCombosWithoutFirst(ArrayList <Card> rest, int targetLength, ArrayList<ArrayList<Card>> allCombos){
        ArrayList<ArrayList <Card>> combos = getAllCombos(rest, targetLength);
        for(ArrayList <Card> combo : combos){
            allCombos.add(combo);
        }
    }

    private static void getCombosWithFirst(ArrayList <Card> rest, int targetLength, ArrayList<ArrayList<Card>> allCombos,
                                           Card cardToAdd){

        ArrayList<ArrayList <Card>> combos = getAllCombos(rest, targetLength-1);
        for(ArrayList <Card> combo : combos){//add first element back
            combo.add(cardToAdd);
        }
        for(ArrayList <Card> combo : combos){
            allCombos.add(combo);
        }
    }

    private static void makeOwnCombo(ArrayList<Card> hand, ArrayList<ArrayList<Card>> allCombos){
        ArrayList<Card> handCopy = new ArrayList<>();
        for(Card card : hand){ // copies hand
            handCopy.add(card);
        }
        allCombos.add(handCopy);
    }

    private static void getSingleCombos(ArrayList<Card> hand, ArrayList<ArrayList<Card>> allCombos){
        for(Card card : hand){
            ArrayList<Card> singleCombo = new ArrayList<>();
            singleCombo.add(card);
            allCombos.add(singleCombo);
        }
    }

    private PokerHand getBestFiveCardHand()
    {

        ArrayList<PokerHand> hands = getAllFiveCardHands(getAllCards(), TARGET_LENGTH);
        PokerHand bestSoFar = hands.get(0);

        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }


    public String toString(){
        return String.valueOf(this.studHand);
    }

}
