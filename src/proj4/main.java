/**
 * Main runs the game
 */
package proj4;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    /**
     * creates the list of cards for a hand
     * @param handSize number of cards per hand
     * @param deck a deck of cards
     * @return a list of dealt cards of said quantity
     */
    private static ArrayList<Card> getHandCards(int handSize, Deck deck){
        ArrayList<Card> handCards = new ArrayList<>();
        for(int i = 0; i < handSize; i++){
            Card newCard = deck.deal();
            handCards.add(newCard);
        }
        return handCards;
    }

    /**
     * plays the game
     */
    public static void playGame() {
        final int COMMUNITY_CARD_SIZE = 5;
        final int HAND_SIZE = 2;
        int correctGuesses = 0;
        boolean game = true;

        Deck deck = new Deck();
        deck.shuffle();
        ArrayList<Card> communityCardList = getHandCards(COMMUNITY_CARD_SIZE, deck);
        CommunityCardSet communityCards = new CommunityCardSet(communityCardList);

        System.out.println("ENTER 1 IF HAND 1 IS BETTER😤,  2 IF HAND 2 IS BETTER🤩, OR 0 IF ITS A TIE😱");

        while(game && deck.enoughInDeck(HAND_SIZE)){

            ArrayList<Card> cardList1 = getHandCards(HAND_SIZE, deck);
            ArrayList<Card> cardList2 = getHandCards(HAND_SIZE, deck);

            StudPokerHand hand1 = new StudPokerHand(communityCards, cardList1);
            StudPokerHand hand2 = new StudPokerHand(communityCards, cardList2);


            System.out.println(" ");
            System.out.println("😩Community Cards: " + communityCards);
            System.out.println("🥵HAND 1: "+ hand1);
            System.out.println("🥶HAND 2: "+ hand2);
            System.out.println(" ");

            int correctAnswer = hand1.compareTo(hand2);
            System.out.println("⬇️ ENTER YOUR GUESS BELOW ⬇️: ");
            Scanner userAnswer = new Scanner(System.in);
            int userAnswerInt =  userAnswer.nextInt(); // converts user answer from scanner to int, use this in compare

            userAnswer.nextLine();

            if(correctAnswer == -1){
                correctAnswer = 2;
            }
            if(userAnswerInt == -1){
                userAnswerInt = 2;
            }
            if(correctAnswer == userAnswerInt){
                correctGuesses += 1;
            }
            else{
                game = false;
            }

            System.out.println("correct answer🤔😳: " + "HAND " + correctAnswer + " | your answer🤡😵🤮: " + "HAND " + userAnswerInt);
            System.out.println("correct guesses: " + correctGuesses);

            if(!game){
                System.out.println("INCORRECT GUESS, GAME OVER ☠️☠️☠️");
            }
            if(!deck.enoughInDeck(HAND_SIZE) && correctGuesses == 5){
                System.out.println("YOU WIN, ALL HANDS HAVE BEEN DEALT!👏🏼👏🏼👏🏼");
            }
        }
    }
    public static void main(String[] args) {
        playGame();
    }
}

