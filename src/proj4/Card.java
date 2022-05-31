/**
 * represents a card object
 */
package proj4;

public class Card {

    private int rank;
    private String suit;


    /**
     * constructor, initializes the card
     */
    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suitString(suit);
    }

    public Card(String rank, String suit){
        this.rank = rankInt(rank); // helper to make royals return the
        this.suit = suit;

    }

    public static String suitString(int suit){
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

    public static int rankInt(String rank){
        int rankInt;
        if(rank.equals("Jack")){
            rankInt = 11;
        }
        else if(rank.equals("Queen")){
            rankInt = 12;
        }
        else if(rank.equals("King")){
            rankInt = 13;
        }
        else if(rank.equals("Ace")){
            rankInt = 14;
        }
        else if(rank.equals("ten")){
            rankInt = 10;
        }
        else if(rank.equals("nine")){
            rankInt = 9;
        }
        else if(rank.equals("eight")){
            rankInt = 8;
        }
        else if(rank.equals("seven")){
            rankInt = 7;
        }
        else if(rank.equals("six")){
            rankInt = 6;
        }
        else if(rank.equals("five")){
            rankInt = 5;
        }
        else if(rank.equals("four")){
            rankInt = 4;
        }
        else if(rank.equals("three")){
            rankInt = 3;
        }
        else if(rank.equals("two")){
            rankInt = 2;
        }
        else{
            rankInt = Integer.parseInt(rank);
        }
        return rankInt;
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
        //rank = getRank();
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