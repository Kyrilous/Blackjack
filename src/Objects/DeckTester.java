package Objects;

public class DeckTester {

    public static void main(String[] args) {
        Deck myDeck = new Deck();
        for(int i = 0; i < 4; i++) {
            System.out.println(myDeck.drawCard());
        }
        myDeck.shuffle();
        for(int i = 0; i < 4; i++) {
            System.out.println(myDeck.drawCard());
        }
        System.out.println(myDeck.getNumCards());

        // Draw top card and put it back
        Card topCard = myDeck.drawCard();
        myDeck.returnCardToTop(topCard);

        myDeck.restore();
        System.out.println(myDeck.getNumCards());

        myDeck.shuffle();
        Card firstCard = myDeck.drawCard();
        Card secondCard = myDeck.drawCard();
        System.out.println(firstCard + "\n" + secondCard);
        if(firstCard.hasSameRankAs(secondCard)) {
            System.out.println("These cards are a pair!");
        }else {
            System.out.println("These cards are not a pair.");
        }

        String[] mySuits = {"Circles", "Squares"};
        String[] myRanks = {"A", "B", "C", "D", "E", "F"};
        Deck deck2 = new Deck(mySuits, myRanks);
        deck2.shuffle();
        System.out.println(deck2.drawCard());

        Card c1 = new Card("heart", "5");

        Card c2 = new Card("spades", "6");

        Card c3 = new Card("heart", "7");



        Card [] cards = {c1, c2, c3};

        System.out.println(Card.isFlush(cards));

        System.out.println(myDeck.getNumCards());

        System.out.println(deck2.getNumCards());

        myDeck.shuffleIn(deck2);

        System.out.println(myDeck.getNumCards());

        System.out.println(deck2.getNumCards());



        System.out.println(deck2);

        System.out.println(myDeck);
    }

}