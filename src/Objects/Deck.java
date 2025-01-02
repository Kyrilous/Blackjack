package Objects;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Card> originalCards = new ArrayList<Card>();

    public Deck(String[] suits, String[] ranks) {
        for(int i = 0; i < suits.length; i++) {
            for(int j = 0; j < ranks.length; j++) {
                Card card = new Card(suits[i], ranks[j]);
                cards.add(card);
                originalCards.add(card);
            }
        }
    }

    public Deck() {
        this(getStandardSuits(), getStandardRanks()); // Calls other constructor in this class
    }

    private static String[] getStandardSuits() {
        String[] suits = {"Hearts",  "Diamonds", "Spades", "Clubs"};
        return suits;
    }

    private static String[] getStandardRanks() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        return ranks;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public int getNumCards() {
        return cards.size();
    }

    public void returnCardToTop(Card card) {
        cards.add(0, card);
    }

    public void returnCardToBottom(Card card) {
        cards.add(card);
    }

    public void restore() {
        cards = new ArrayList<Card>(originalCards); // Makes copy to preserve original list
    }

    public void shuffleIn(Deck otherDeck) {
        cards.addAll(otherDeck.cards);
        Collections.shuffle(cards);
        otherDeck.cards.removeAll(cards);
    }


    public String toString() {
        return "Deck: " + cards.size() + " cards.";
    }
}