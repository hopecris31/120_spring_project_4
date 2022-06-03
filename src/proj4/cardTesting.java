/**
 * unit testing, tests all public methods in the Card Class
 */

package proj4;
public class cardTesting {
    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testCardClass(testSuite);
        testSuite.finishTests();

    }

    public static void testCardClass(Testing testSuite){

        Card card1 = new Card(6, 2);
        Card card1Copy = new Card(6, 2);
        Card card2 = new Card(11, 0);
        Card emptyCard = new Card(0, -1);
        Deck deck = new Deck();


        Testing.assertEquals("testing getRank", 6, card1.getRank());
        Testing.assertEquals("testing getSuit", "Clubs", card1.getSuit());
        Testing.assertEquals("testing equals with the same card", true, card1.equals(card1Copy));
        Testing.assertEquals("testing equals with a different card", false, card1.equals(card2));
        Testing.assertEquals("testing equals with a null card", false, card1.equals(emptyCard));
        Testing.assertEquals("testing equals with a different object", false, card1.equals(deck));


    }

}
