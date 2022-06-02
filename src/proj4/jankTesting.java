package proj4;
import java.util.ArrayList;

public class jankTesting {

    public static void main(String[] args) {
        Deck newDeck = new Deck();
        newDeck.shuffle();
        //System.out.println(newDeck);

        Card card = new Card("ten", "Clubs");
        //System.out.println(card);


        Card sh1 = new Card("10", "Clubs");
        Card sh2 = new Card("9", "Clubs");

        Card sh3 = new Card("2", "Spades");
        Card sh4 = new Card("5", "Clubs");

        Card cc1 = new Card("9", "Spades");
        Card cc2 = new Card("four", "Hearts");
        Card cc3 = new Card("7", "Spades");
        Card cc4 = new Card("2", "Clubs");
        Card cc5 = new Card("Queen", "Hearts");

        ArrayList<Card> shCardList1 = new ArrayList<>();
        shCardList1.add(sh1);
        shCardList1.add(sh2);
        ArrayList<Card> shCardList2 = new ArrayList<>();
        shCardList2.add(sh3);
        shCardList2.add(sh4);

        ArrayList<Card> ccCardList = new ArrayList<>();
        ccCardList.add(cc1);
        ccCardList.add(cc2);
        ccCardList.add(cc3);
        ccCardList.add(cc4);
        ccCardList.add(cc5);



        CommunityCardSet cc = new CommunityCardSet(ccCardList);
        StudPokerHand sph1 = new StudPokerHand(cc, shCardList1);
        StudPokerHand sph2 = new StudPokerHand(cc, shCardList2);

        //testing getAllCards✅
        //ArrayList<Card> allCards = sph1.getAllCards(); //get all cards from both hands
       // System.out.println(allCards);

        //testing getAllCombos✅
        //ArrayList<ArrayList<Card>> allCombos = sph1.getAllCombos(allCards, 5);
        //System.out.println(allCombos);

        //testing singleCombo


        int answer = sph1.compareTo(sph2);
        System.out.println(answer);

    }

}

