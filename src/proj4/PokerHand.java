/**
 * represents a poker hand object
 */
package proj4; // do not erase. Gradescope expects this.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;



public class PokerHand implements Hand{

    public final int FLUSH_VALUE = 4;
    public final int TWO_PAIR_VALUE = 3;
    public final int PAIR_VALUE = 2;
    public final int HIGH_CARD_VALUE = 1;

    public final int HAND_SIZE = 5;
    private ArrayList<Card> hand;

    /**
     * Poker Hand constructor
     * @param cardList a list of cards to use for the poker hand
     */
    public PokerHand(ArrayList<Card> cardList) {
        ArrayList<Card> cardsCopy = new ArrayList<>(cardList);
        this.hand = cardsCopy;
    }

    /**
     * adds a card to the hand
     * @param card a card to add
     */
    public void addCard(Card card) {
        if (this.hand.size() < HAND_SIZE) {
            this.hand.add(card);
        }
    }

    /**
     * gets the card in the hand at specified index
     * @param index index of card
     * @return the index where the card is located
     */
    public Card getIthCard(int index){
        if(index > HAND_SIZE -1 || index < 0){
            return null;
        }
        return this.hand.get(index);
    }

    /**
     * gets the ranks of the cards in a hand
     * @return a list of the ranks as ints
     */
    private ArrayList<Integer> getHandRanks() {
        ArrayList<Integer> listRanks = new ArrayList<>();
        for (Card card : this.hand) {
            int rank = card.getRank();
            listRanks.add(rank);
        }
        return listRanks;
    }

    /**
     * determines if a hand is a flush
     * @return True if flush, False if not
     */
    private boolean isFlush() {
        String firstSuit = this.hand.get(0).getSuit();
        for (Card card : this.hand) {
            if (!firstSuit.equals(card.getSuit())) {
                return false;
            }
        }
        return true;
    }

    /**
     * determines if a hand is a pair
     * @return True if pair, False if not
     */
    private boolean isPair() {
        ArrayList<Integer> handRanks = this.getHandRanks();
        for (int card1 = 0; card1 < handRanks.size()-1; card1++) {
            for (int card2 = card1+1; card2 < handRanks.size(); card2++) {
                if (handRanks.get(card1).equals(handRanks.get(card2))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * determines if a hand is a two pair
     * @return True if two pair, False if not
     */
    private boolean isTwoPair() {
        if (this.isPair()) {
            int numPairs = 0;
            ArrayList<Integer> handRanks = this.getHandRanks();
            for (int card1 = 0; card1 < handRanks.size()-1; card1++) {
                for (int card2 = card1+1; card2 < handRanks.size(); card2++) {
                    if (handRanks.get(card1).equals(handRanks.get(card2))) {
                        numPairs += 1;
                    }
                }
            }
            return numPairs == 2;
        }
        return false;
    }

    /**
     * determines if a hand is a three of a kind
     * @return True if three kind, False if not
     */
    private boolean isThreeKind(){
        ArrayList<Integer> handRanks = this.getHandRanks();
        handRanks.sort(Comparator.comparing(i -> Collections.frequency(handRanks, i)).reversed());
        int occurrences = Collections.frequency(handRanks, handRanks.get(0));
        return occurrences == 3;
    }

    /**
     * determines if a hand is a four of a kind
     * @return True if four kind, False if not
     */
    private boolean isFourKind(){
        ArrayList<Integer> handRanks = this.getHandRanks();
        handRanks.sort(Comparator.comparing(i -> Collections.frequency(handRanks, i)).reversed());
        int occurrences = Collections.frequency(handRanks, handRanks.get(0));
        return occurrences == 4;
    }

    /**
     * gets the pairs in a given hand
     * @return a list of the pair ranks in a hand (one int per pair)
     */
    private ArrayList<Integer> getPairs(){
        ArrayList<Integer> listPairs = new ArrayList<>();
        if(this.isPair()){
            for (int card1 = 0; card1 < this.hand.size(); card1++) {
                for (int card2 = card1+1; card2 < this.hand.size(); card2++) {
                    if(this.hand.get(card1).getRank() == this.hand.get(card2).getRank() && !listPairs.contains(this.hand.get(card1).getRank())){
                        listPairs.add(this.hand.get(card1).getRank());
                    }
                }
            }
        }
        return listPairs;
    }

    /**
     * determines which given hand has the highest ranking card
     * @param other the other hand to compare
     * @return 1 if the original had has highest card, -1 if other hand, and 0 if hands are exactly the same
     */
    private int compareHighCard(PokerHand other){
        ArrayList<Integer> selfRanks = this.getHandRanks();
        ArrayList<Integer> otherRanks = other.getHandRanks();
        selfRanks.sort(Collections.reverseOrder());
        otherRanks.sort(Collections.reverseOrder());

        for(int card = 0; card < selfRanks.size(); card++){
            if(selfRanks.get(card) > otherRanks.get(card)){
                return 1;
            }
            else if(selfRanks.get(card) < otherRanks.get(card)){
                return -1;
            }
        }
        return 0;
    }

    /**
     * determines which hand is worth more if they are a hand of the same type
     * @param other other hand to compare
     * @return 1 if the original had ranks, -1 if other hand ranks higher, and 0 if hands are exactly the same
     */
    private int compareHandSameType(PokerHand other){
        ArrayList<Integer> selfPair = this.getPairs();
        ArrayList<Integer> otherPair = other.getPairs();
        selfPair.sort(Collections.reverseOrder());
        otherPair.sort(Collections.reverseOrder());

        if (this.isPair() && other.isPair()){
            for(int pair = 0; pair < selfPair.size(); pair++){
                if(selfPair.get(pair) > otherPair.get(pair)){
                    return 1;
                }
                else if(selfPair.get(pair) < otherPair.get(pair)){
                    return -1;
                }
                else if(Objects.equals(selfPair.get(pair), otherPair.get(pair))){
                    return this.compareHighCard(other);
                }
            }
        }
        return this.compareHighCard(other);
    }

    /**
     * gives a hand type a value, used to help determine which hand type is better when compared
     * @return int value of the hand type worth
     */
    private int handTypeWorth(){
        if(this.isFlush()){
            return FLUSH_VALUE;
        }
        if(this.isFourKind()){
            return TWO_PAIR_VALUE;
        }
        else if(this.isTwoPair()){
            return TWO_PAIR_VALUE;
        }
        else if(this.isThreeKind()){
            return PAIR_VALUE;
        }
        else if(this.isPair()){
            return PAIR_VALUE;
        }
        else{
            return HIGH_CARD_VALUE;
        }
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    public int compareTo(PokerHand other){
        int selfTypeWorth = this.handTypeWorth();
        int otherTypeWorth = other.handTypeWorth();

        if(selfTypeWorth > otherTypeWorth){
            return 1;
        }
        else if(selfTypeWorth < otherTypeWorth){
            return -1;
        }
        else{
            return this.compareHandSameType(other);
        }
    }

    /**
     * checks the equality of a PokerHand object
     * @param other another object to compare
     * @return True if same object, False if else
     */
    public boolean equals(PokerHand other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof PokerHand)) {
            return false;
        } else {
            PokerHand otherStudHand = (PokerHand) other;
            return this.hand == otherStudHand.hand;
        }
    }

    public String toString(){
        return String.valueOf(this.hand);
    }

}

