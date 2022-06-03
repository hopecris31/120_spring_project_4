/**
 * unit testing, tests all public methods in the CommunityCardSet Class
 */

package proj4;

import java.util.ArrayList;

public class CommunityCardSetTesting {
    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testCommunityCardSetClass(testSuite);
        testSuite.finishTests();

    }

    private static CommunityCardSet makeHandFromArray(String[] ranksAndSuits) {
        ArrayList<Card> handCards = new ArrayList<>();
        for (int i = 0; i < ranksAndSuits.length; i += 2){
            Card card = new Card(ranksAndSuits[i], ranksAndSuits[i+1]);
            handCards.add(card);
        }
        return new CommunityCardSet(handCards);
    }

    public static void testCommunityCardSetClass(Testing testSuite) {
        String[] communityCardList = {"2", "Spades", "3", "Spades", "5", "Spades", "7", "Spades"};
        CommunityCardSet communityCards = makeHandFromArray(communityCardList);
        Card cardToAdd = new Card("6", "Hearts");
        communityCards.addCard(cardToAdd);

        Testing.assertEquals("testing addCard, add a card to a hand of 4 cards", cardToAdd, communityCards.getIthCard(4));
        Testing.assertEquals("testing getIthCard, getting the first card in the hand", "2 of Spades", String.valueOf(communityCards.getIthCard(0)));
    }
}
