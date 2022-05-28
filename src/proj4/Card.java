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
        this.suit = String.valueOf(suit);
    }

    public Card(String rank, String suit){
        this.rank = Integer.parseInt(rank);
        this.suit = suit;

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