/**
 * Unit testing, tests the compareTo method
 */
package proj4;

import javax.crypto.spec.PSource;
import java.util.ArrayList;

//make helper that does this, make new list for ech separate hand
//list ranks for hand1
//list suits for hand1
//for loop, call card constructor
//add card to arraylist
//call constructor on returned arraylist

public class PokerHandTesting {

    public static PokerHand makeHandFromArray(String[] ranksAndSuits) {
        ArrayList<Card> handCards = new ArrayList<>();
        for (int i = 0; i < 10; i += 2){
                Card card = new Card(ranksAndSuits[i], ranksAndSuits[i+1]);
                handCards.add(card);
            }
        return new PokerHand(handCards);
    }

    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testPokerHand(testSuite);
        testSuite.finishTests();

    }

    public static void testPokerHand(Testing testSuite) {
        String[] flush1 = {"2", "Spades", "3", "Spades", "5", "Spades", "7", "Spades", "9", "Spades"};
        PokerHand flush = makeHandFromArray(flush1);
        System.out.println(flush);


    }
}