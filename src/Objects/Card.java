package Objects;

public class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public String getImagePath() {
        String formattedRank = rank.toLowerCase();
        String formattedSuit = suit.toLowerCase();
        return "/images/" + formattedRank + "_of_" + formattedSuit + ".png"; // Resource path starts with '/'
    }


    public boolean hasSameRankAs(Card otherCard) {
        return rank.equals(otherCard.rank);
    }

    public static boolean isFlush(Card[] cards){
        String suit = cards[0].getSuit();
        int count = 0;
        for(int i = 0; i < cards.length; i++){
            if(cards[i].getSuit().equals(suit)){
                count++;
            }
        }
        return count == cards.length;
    }

}
