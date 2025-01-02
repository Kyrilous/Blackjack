package Objects;

import java.util.ArrayList;

public class Player {
  ArrayList<Card> hand;
  boolean isDealer;
  int handValue;
  public Player(boolean isDealer){
    this.hand = new ArrayList<Card>();
    this.isDealer = isDealer;

  }

  public void hit(Card card){
    hand.add(card);
  }


  public String decision(String input){
    if(input == "H".toLowerCase()){
      return  "H";
    }else if(input == "S"){
      return"S";
    }else
    return"Thats Not A Valid Decision !";
  }

  public void resetHand(){
    hand.clear();
  }

  public Card dealerShowing(){
    return hand.get(0);
  }

  public int calculateHandValue(){
    int total = 0;
    int aceCount = 0;
    //Add the value of each card
    for(Card card : hand){
      String rank = card.getRank();
      switch (rank){
        case  "Jack":
        case "Queen":
        case "King":
          total += 10;
          break;
        case "Ace":
          total += 11;
          aceCount++;
          break;
        default:
          total += Integer.parseInt(rank);
      }
    }
    while (total > 21 && aceCount > 0){
      total -= 10;
      aceCount--;
    }
    return total;
  }


}
