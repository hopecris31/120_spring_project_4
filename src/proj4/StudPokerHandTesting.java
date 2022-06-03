/**
 * unit testing, tests all public methods in StudPokerHand
 */
package proj4;

import java.util.ArrayList;


public class StudPokerHandTesting {
    public static void main(String[] args) {
        Testing testSuite = new Testing();
        Testing.setVerbose(true);
        testStudPokerHandClass(testSuite);
        testSuite.finishTests();

    }

    private static CommunityCardSet makeCommunityHand(String[] ranksAndSuits) {
        ArrayList<Card> handCards = new ArrayList<>();
        for (int i = 0; i < ranksAndSuits.length; i += 2) {
            Card card = new Card(ranksAndSuits[i], ranksAndSuits[i + 1]);
            handCards.add(card);
        }
        return new CommunityCardSet(handCards);
    }

    private static ArrayList<Card> makeStudHand(String[] ranksAndSuits) {
        ArrayList<Card> handCards = new ArrayList<>();
        for (int i = 0; i < ranksAndSuits.length; i += 2) {
            Card card = new Card(ranksAndSuits[i], ranksAndSuits[i + 1]);
            handCards.add(card);
        }
        return handCards;
    }

    public static void testStudPokerHandClass(Testing testSuite) {
        String[] communityCardList1 = {"2", "Spades", "3", "Clubs", "4", "Spades", "6", "Spades", "6", "Diamonds"};
        CommunityCardSet cc1 = makeCommunityHand(communityCardList1);
        String[] studHandList1 = {"7", "Spades", "8", "Hearts"};
        ArrayList<Card> sh1cards = makeStudHand(studHandList1);
        StudPokerHand sh1 = new StudPokerHand(cc1, sh1cards);

        String[] studHandList2 = {"9", "Diamonds", "10", "Spades"};
        ArrayList<Card> sh2cards = makeStudHand(studHandList2);
        StudPokerHand sh2 = new StudPokerHand(cc1, sh2cards);

        String[] studHandList3 = {"6", "Spades", "10", "Hearts"};
        ArrayList<Card> sh3cards = makeStudHand(studHandList3);
        StudPokerHand sh3 = new StudPokerHand(cc1, sh3cards);
        String[] studHandList4 = {"6", "Diamonds", "11", "Spades"};
        ArrayList<Card> sh4cards = makeStudHand(studHandList4);
        StudPokerHand sh4 = new StudPokerHand(cc1, sh4cards);
        String[] studHandList5 = {"9", "Spades", "5", "Clubs"};
        ArrayList<Card> sh5cards = makeStudHand(studHandList5);
        StudPokerHand sh5 = new StudPokerHand(cc1, sh5cards);

        String[] communityCardList2 = {"14", "Spades", "13", "Spades", "4", "Spades", "6", "Spades", "5", "Spades"};
        CommunityCardSet cc2 = makeCommunityHand(communityCardList2);
        String[] studHandList7 = {"14", "Spades", "14", "Spades"};
        ArrayList<Card> sh7cards = makeStudHand(studHandList7);
        String[] studHandList6 = {"7", "Spades", "8", "Spades"};
        ArrayList<Card> sh6cards = makeStudHand(studHandList6);
        StudPokerHand sh6 = new StudPokerHand(cc2, sh6cards);
        StudPokerHand sh7 = new StudPokerHand(cc2, sh7cards);


        Testing.assertEquals("testing compareTo, one has higher card", -1, sh1.compareTo(sh2));
        Testing.assertEquals("testing compareTo, one has pair", 1, sh1.compareTo(sh3));
        Testing.assertEquals("testing compareTo, both pair, so test high card", 1, sh3.compareTo(sh4));
        Testing.assertEquals("testing compareTo, one pair, one high card", 1, sh4.compareTo(sh5));
        Testing.assertEquals("testing compareTo, one pair, one high card", -1, sh6.compareTo(sh7));


        Card cardToAdd = new Card("14", "Hearts");
        String[] communityCardList10 = {"2", "Spades", "3", "Spades", "4", "Spades", "5", "Spades", "6", "Spades"};
        CommunityCardSet cc10 = makeCommunityHand(communityCardList10);
        String[] studHandList10 = {"7", "Spades"};
        ArrayList<Card> sh10cards = makeStudHand(studHandList10);
        StudPokerHand sh10 = new StudPokerHand(cc10, sh10cards);
        sh10.addCard(cardToAdd);
        Testing.assertEquals("testing addCard", cardToAdd, sh10.getIthCard(1));
        Testing.assertEquals("testing getIthCard", "7 of Spades", String.valueOf(sh10.getIthCard(0)));



    }
}
