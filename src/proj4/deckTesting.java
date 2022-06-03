/**
 * unit testing, tests all public methods in the Deck class
 */

package proj4;
public class deckTesting {
    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testCardClass(testSuite);
        testSuite.finishTests();

    }


    public static void testCardClass(Testing testSuite) {
        Deck deck = new Deck();
        Deck deck2 = new Deck(); //shuffled deck
        Deck deck3 = new Deck();//2 cards dealt

        Testing.assertEquals("testing enoughInDeck",true, deck.enoughInDeck(5));
        Testing.assertEquals("testing isEmpty",false, deck.isEmpty());
        Testing.assertEquals("testing enoughInDeck",true, deck.enoughInDeck(5));
        deck.deal();
        Testing.assertEquals("testing deal",51, deck.size());//test with unshuffled deck, should return Card card(2,0)
        deck2.shuffle();
        Testing.assertEquals("testing shuffle",false, deck.equals(deck2));
        Testing.assertEquals("testing size",52, deck2.size());
        deck3.deal();
        deck3.deal();
        deck3.gather();
        Testing.assertEquals("testing gather",52, deck3.size());

    }
}
