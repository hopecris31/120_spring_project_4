/**
 * represents a card object
 */
package proj4;
import java.util.HashMap;
import java.util.Objects;


public class Card {

    private int rank;
    private String suit;
    HashMap<String, Integer> strToInt = textToNumber();


    /**
     * constructor, initializes the card with int parameters
     * @param rank a card rank, as an int
     * @param suit a card suit, as an int
     */
    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suitString(suit);
    }

    /**
     * constructor, initializes the card with String parameters
     * @param rank a card rank, as a String
     * @param suit a card suit, as a String
     */
    public Card(String rank, String suit){
        if(strToInt.containsKey(rank)){ // if the rank is a word, as a string
            this.rank = strToInt.get(rank);
        }
        else{ // if the rank is an int, as a string
            this.rank = Integer.parseInt(rank);
        }
        this.suit = suit;
    }

    private HashMap<String, Integer> textToNumber (){
        String[] textNumbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "Jack", "Queen", "King", "Ace"};
        HashMap<String, Integer> textToIntRank = new HashMap<>();
        for(int i = 0; i < textNumbers.length; i++){
            textToIntRank.put(textNumbers[i], i+2);
        }
        return textToIntRank;
    }

    private static String suitString(int suit){
        String suitString;
        if(suit == 0){
            suitString = "Spades";
        }
        else if(suit == 1){
            suitString = "Hearts";
        }
        else if(suit == 2){
            suitString = "Clubs";
        }
        else {
            suitString = "Diamonds";
        }
        return suitString;
    }

    /**
     * returns the rank of the card
     */
    public int getRank(){
        return this.rank;
    }

    /**
     * returns the suit of the card
     */
    public String getSuit(){
        return this.suit;
    }

    /**
     * checks the equality of a Card object
     * @param other another object to compare
     * @return True if same object, False if else
     */
    public boolean equals(Card other) {
        if (other == this) {
            return true;
        }
        else if (other == null) {
            return false;
        }
        else if (!(other instanceof Card)) {
            return false;
        }
        else {
            Card otherCard = (Card) other;
            return this.rank == otherCard.rank && Objects.equals(this.suit, otherCard.suit);
        }
    }

    /**
     * take numeric rank and turn it into
     * a printable string where 11-14 are
     * turned into Face card values
     */
    public String toString(){
        String rankString = String.valueOf(getRank());
        if(rankString.equals("11")){
            rankString = "Jack";
        }
        else if(rankString.equals("12")){
            rankString = "Queen";
        }
        else if(rankString.equals("13")){
            rankString = "King";
        }
        else if(rankString.equals("14")){
            rankString = "Ace";
        }
        return rankString + " of "  + getSuit();
    }
}