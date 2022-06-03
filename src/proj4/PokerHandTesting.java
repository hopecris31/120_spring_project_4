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
        for (int i = 0; i < ranksAndSuits.length; i += 2){
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
        String[] flush1List = {"2", "Spades", "3", "Spades", "5", "Spades", "7", "Spades", "9", "Spades"};
        PokerHand flush1 = makeHandFromArray(flush1List);
        String[] flush2List = {"8", "Hearts", "9", "Hearts", "10", "Hearts", "11", "Hearts", "12", "Hearts"};
        PokerHand flush2 = makeHandFromArray(flush2List);
        String[] twoPair1List = {"8", "Hearts", "9", "Spades", "8", "Diamonds", "9", "Hearts", "12", "Hearts"};
        PokerHand twoPair1 = makeHandFromArray(twoPair1List);
        String[] twoPair2List = {"2", "Hearts", "3", "Spades", "2", "Diamonds", "3", "Hearts", "5", "Hearts"};
        PokerHand twoPair2 = makeHandFromArray(twoPair2List);
        String[] twoPair3List = {"2", "Hearts", "3", "Spades", "2", "Diamonds", "3", "Hearts", "6", "Hearts"};
        PokerHand twoPair3 = makeHandFromArray(twoPair3List);
        String[] pair1List = {"5", "Hearts", "5", "Spades", "2", "Diamonds", "3", "Hearts", "6", "Hearts"};
        PokerHand pair1 = makeHandFromArray(pair1List);
        String[] pair2List = {"7", "Hearts", "7", "Spades", "2", "Diamonds", "3", "Hearts", "6", "Hearts"};
        PokerHand pair2 = makeHandFromArray(pair2List);
        String[] pair3List = {"7", "Hearts", "7", "Spades", "2", "Diamonds", "3", "Hearts", "14", "Hearts"};
        PokerHand pair3 = makeHandFromArray(pair3List);
        String[] highCard1List = {"2", "Hearts", "3", "Spades", "4", "Diamonds", "5", "Hearts", "6", "Hearts"};
        PokerHand highCard1 = makeHandFromArray(highCard1List);
        String[] highCard2List = {"2", "Hearts", "3", "Spades", "4", "Diamonds", "5", "Hearts", "14", "Hearts"};
        PokerHand highCard2 = makeHandFromArray(highCard2List);
        String[] addCardList = {"2", "Hearts", "3", "Spades", "4", "Diamonds", "5", "Hearts"};
        PokerHand addCardHand = makeHandFromArray(addCardList);

        Testing.assertEquals("testing flush to flush, one with higher high card", -1, flush1.compareTo(flush2));
        Testing.assertEquals("testing flush to flush, both same exact ranks", 0, flush1.compareTo(flush1));
        Testing.assertEquals("testing two pair to two pair, both same exact ranks", 0, twoPair1.compareTo(twoPair1));
        Testing.assertEquals("testing two pair to two pair, one with lower ranks", 1, twoPair1.compareTo(twoPair2));
        Testing.assertEquals("testing two pair to two pair, one with higher high card", -1, twoPair2.compareTo(twoPair3));
        Testing.assertEquals("testing two pairs, one with higher ranks", -1, pair1.compareTo(pair2));
        Testing.assertEquals("testing two pairs, one with higher high card", -1, pair1.compareTo(pair3));
        Testing.assertEquals("testing two pairs, all exact same ranks", 0, pair1.compareTo(pair1));
        Testing.assertEquals("testing high card", -1, highCard1.compareTo(highCard2));
        Card cardToAdd = new Card("5", "Hearts");
        addCardHand.addCard(cardToAdd);
        Testing.assertEquals("testing addCard, adding a card to a 4 card hand", cardToAdd, addCardHand.getIthCard(4));
        Testing.assertEquals("testing getIthCard", cardToAdd, addCardHand.getIthCard(4));
    }
}