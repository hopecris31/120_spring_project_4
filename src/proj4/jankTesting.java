package proj4;
import java.util.ArrayList;

public class jankTesting {

    public static void main(String[] args) {
        Deck newDeck = new Deck();
        newDeck.shuffle();
        System.out.println(newDeck);

        Card card = new Card("Ten", "Clubs");
        System.out.println(card);
    }

}

