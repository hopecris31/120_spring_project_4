package proj4;
/**
 * Unit testing, tests the compareTo method
 */
import java.util.ArrayList;

public class PokerComparisonTests {
    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testCompareTo(testSuite);
        testSuite.finishTests();

    }

    public static void testCompareTo(Testing testSuite) {

        Card c2 = new Card(2, "Clubs");
        Card c3 = new Card(3, "Clubs");
        Card c4 = new Card(4, "Clubs");
        Card c5 = new Card(5, "Clubs");
        Card c6 = new Card(6, "Clubs");
        Card c7 = new Card(7, "Clubs");
        Card c8 = new Card(8, "Clubs");
        Card c9 = new Card(9, "Clubs");
        Card c10 = new Card(10, "Clubs");
        Card c11 = new Card(11, "Clubs");
        Card c12 = new Card(12, "Clubs");
        Card c13 = new Card(13, "Clubs");
        Card c14 = new Card(14, "Clubs");

        Card h2 = new Card(2, "Hearts");
        Card h3 = new Card(3, "Hearts");
        Card h4 = new Card(4, "Hearts");
        Card h5 = new Card(5, "Hearts");
        Card h6 = new Card(6, "Hearts");
        Card h7 = new Card(7, "Hearts");
        Card h8 = new Card(8, "Hearts");
        Card h9 = new Card(9, "Hearts");
        Card h10 = new Card(10, "Hearts");
        Card h11 = new Card(11, "Hearts");
        Card h12 = new Card(12, "Hearts");
        Card h13 = new Card(13, "Hearts");
        Card h14 = new Card(14, "Hearts");

        Card s2 = new Card(2, "Spades");
        Card s3 = new Card(3, "Spades");
        Card s4 = new Card(4, "Spades");
        Card s5 = new Card(5, "Spades");
        Card s6 = new Card(6, "Spades");
        Card s7 = new Card(7, "Spades");
        Card s8 = new Card(8, "Spades");
        Card s9 = new Card(9, "Spades");
        Card s10 = new Card(10, "Spades");
        Card s11 = new Card(11, "Spades");
        Card s12 = new Card(12, "Spades");
        Card s13 = new Card(13, "Spades");
        Card s14 = new Card(14, "Spades");

        ArrayList<Card> cardList = new ArrayList<Card>();

        PokerHand flush1 = new PokerHand(cardList);
        flush1.addCard(s2);
        flush1.addCard(s3);
        flush1.addCard(s5);
        flush1.addCard(s7);
        flush1.addCard(s9);
        PokerHand flush2 = new PokerHand(cardList);
        flush2.addCard(h8);
        flush2.addCard(h9);
        flush2.addCard(h10);
        flush2.addCard(h11);
        flush2.addCard(h12);

        PokerHand highCard1 = new PokerHand(cardList);
        highCard1.addCard(s2);
        highCard1.addCard(s3);
        highCard1.addCard(c5);
        highCard1.addCard(h7);
        highCard1.addCard(c14);
        PokerHand highCard2 = new PokerHand(cardList);
        highCard2.addCard(s2);
        highCard2.addCard(s3);
        highCard2.addCard(c5);
        highCard2.addCard(h7);
        highCard2.addCard(c8);


        PokerHand pair1 = new PokerHand(cardList);
        pair1.addCard(s12);
        pair1.addCard(s4);
        pair1.addCard(c5);
        pair1.addCard(h12);
        pair1.addCard(c7);
        PokerHand pair2 = new PokerHand(cardList);
        pair2.addCard(s11);
        pair2.addCard(s5);
        pair2.addCard(c11);
        pair2.addCard(h8);
        pair2.addCard(c9);
        PokerHand pair3 = new PokerHand(cardList);
        pair3.addCard(s12);
        pair3.addCard(s4);
        pair3.addCard(c5);
        pair3.addCard(h12);
        pair3.addCard(c14);
        PokerHand pair4 = new PokerHand(cardList); // three of a kind
        pair4.addCard(s12);
        pair4.addCard(s4);
        pair4.addCard(c5);
        pair4.addCard(h12);
        pair4.addCard(c12);


        PokerHand twoPair1 = new PokerHand(cardList);
        twoPair1.addCard(s2);
        twoPair1.addCard(s5);
        twoPair1.addCard(c5);
        twoPair1.addCard(h7);
        twoPair1.addCard(c7);
        PokerHand twoPair2 = new PokerHand(cardList);
        twoPair2.addCard(s3);
        twoPair2.addCard(s13);
        twoPair2.addCard(c13);
        twoPair2.addCard(h14);
        twoPair2.addCard(c14);
        PokerHand twoPair3 = new PokerHand(cardList);
        twoPair3.addCard(s5);
        twoPair3.addCard(s13);
        twoPair3.addCard(c13);
        twoPair3.addCard(h14);
        twoPair3.addCard(c14);
        PokerHand twoPair4 = new PokerHand(cardList); //four of a kind
        twoPair4.addCard(s5);
        twoPair4.addCard(s13);
        twoPair4.addCard(c13);
        twoPair4.addCard(h14);
        twoPair4.addCard(c14);


        System.out.println("Flush Tests");
        Testing.assertEquals("testing flush to flush, one with higher high card", -1, flush1.compareTo(flush2));
        Testing.assertEquals("testing flush to two pair", 1, flush1.compareTo(twoPair2));
        Testing.assertEquals("testing flush to pair", 1, flush1.compareTo(pair1));
        Testing.assertEquals("testing flush to high card", 1, flush1.compareTo(highCard1));
        Testing.assertEquals("testing flush to flush, both same exact ranks", 0, flush1.compareTo(flush1));
        Testing.assertEquals("testing flush to four of a kind", 1, flush1.compareTo(twoPair4));

        System.out.println(" ");
        System.out.println("Two Pair Tests");
        Testing.assertEquals("testing two pair to pair", 1, twoPair1.compareTo(pair1));
        Testing.assertEquals("testing two pair to two pair, one w higher ranks", -1, twoPair1.compareTo(twoPair2));
        Testing.assertEquals("testing two pair to pair, compare high card", -1, twoPair2.compareTo(twoPair3));
        Testing.assertEquals("testing two pair to two pair, both exact same ranks", 0, twoPair2.compareTo(twoPair2));
        Testing.assertEquals("testing two pair to high card, compare high card", -1, highCard1.compareTo(twoPair3));

        System.out.println(" ");
        System.out.println("Pair Tests");
        Testing.assertEquals("testing pair and high card", -1, highCard1.compareTo(pair1));
        Testing.assertEquals("testing pair queen to pair jack", 1, pair1.compareTo(pair2));
        Testing.assertEquals("testing pair queen to pair queen, compare high card", -1, pair1.compareTo(pair3));
        Testing.assertEquals("testing pair to two pair", -1, pair1.compareTo(twoPair1));
        Testing.assertEquals("testing pair to high card", 1, pair2.compareTo(highCard1));
        Testing.assertEquals("testing pair to pair of exact same ranks", 0, pair2.compareTo(pair2));
        Testing.assertEquals("testing pair to three of a kind of higher ranks", -1, pair2.compareTo(pair4));

        System.out.println(" ");
        System.out.println("High Card Tests");
        Testing.assertEquals("testing high card to high card", 1, highCard1.compareTo(highCard2));
        Testing.assertEquals("testing high card to high card, both same high card, compare second highest card", 1, highCard1.compareTo(highCard2));
        Testing.assertEquals("testing high card to high card, both hands exact same ranks", 0, highCard2.compareTo(highCard2));
    }
}
