/**
 * represents a card object
 */
package proj4;
import java.util.HashMap;


public class Card {

    private int rank;
    private String suit;
    HashMap<String, Integer> strToInt = textToNumber();


    /**
     * constructor, initializes the card
     */
    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suitString(suit);
    }

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