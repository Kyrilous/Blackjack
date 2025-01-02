package Objects;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;


public class BlackjackGame {
  private Deck gameDeck;
  private Player player;
  private Player dealer;

  public BlackjackGame() {
    gameDeck = new Deck();
    gameDeck.shuffle();
    player = new Player(false);
    dealer = new Player(true);

    // Initial Deal
    player.hit(gameDeck.drawCard());
    dealer.hit(gameDeck.drawCard());
    player.hit(gameDeck.drawCard());
    dealer.hit(gameDeck.drawCard());
  }

  public void playerHit() {
    player.hit(gameDeck.drawCard());
  }

  public boolean checkPlayerBust() {
    return player.calculateHandValue() > 21;
  }

  public void dealerTurn() {
    while (dealer.calculateHandValue() < 17) {
      dealer.hit(gameDeck.drawCard());
    }
  }

  public String determineWinner() {
    int playerTotal = player.calculateHandValue();
    int dealerTotal = dealer.calculateHandValue();

    if (dealerTotal > 21 || playerTotal > dealerTotal) {
      return "Player Wins!";
    } else if (playerTotal < dealerTotal) {
      return "Dealer Wins!";
    } else {
      return "It's a Draw!";
    }
  }

  public Player getPlayer() {
    return player;
  }

  public Player getDealer() {
    return dealer;
  }
}






//public class Blackjack {
//
//  public static void main(String[] args) throws InterruptedException {
//    Deck gameCards = new Deck();
//    gameCards.shuffle();
//    System.out.println("Welcome To BlackJack!");
//
//    Player dealer = new Player(true);
//    Player player1 = new Player(false);
//
//    Scanner scanner = new Scanner(System.in);
//
//    // Initial Deal
//    player1.hit(gameCards.drawCard());
//    dealer.hit(gameCards.drawCard());
//    player1.hit(gameCards.drawCard());
//    dealer.hit(gameCards.drawCard());
//
//    System.out.println("Your Hand: " + player1.hand);
//    System.out.println("Dealer Showing: " + dealer.dealerShowing());
//
//    // Check for Immediate Blackjack
//    if (player1.calculateHandValue() == 21) {
//      System.out.println("Blackjack! You win!");
//      return;
//    }
//    if (dealer.calculateHandValue() == 21) {
//      System.out.println("Dealer has Blackjack! Dealer wins!");
//      return;
//    }
//
//    // Player's Turn
//    boolean playerBust = false;
//    while (!playerBust) {
//      System.out.println("Your Total: " + player1.calculateHandValue());
//      System.out.println("Would you like to 'Hit' or 'Stand'? (H/S)");
//      String choice = scanner.nextLine().toLowerCase();
//
//      if (choice.equals("h")) {
//        player1.hit(gameCards.drawCard());
//        System.out.println("Your Hand: " + player1.hand);
//        if (player1.calculateHandValue() > 21) {
//          System.out.println("You Busted! Your Total: " + player1.calculateHandValue());
//          playerBust = true;
//        }
//      } else if (choice.equals("s")) {
//        break;
//      } else {
//        System.out.println("Invalid Input. Please type 'H' or 'S'.");
//      }
//    }
//
//    // Dealer's Turn
//    boolean dealerBust = false;
//    if (!playerBust) {
//      System.out.println("Dealer's Hand: " + dealer.hand);
//      while (dealer.calculateHandValue() < 17) {
//        dealer.hit(gameCards.drawCard());
//        System.out.println("Dealer Hits.");
//        System.out.println("Dealer's Hand: " + dealer.hand);
//        TimeUnit.SECONDS.sleep((2));
//
//        if (dealer.calculateHandValue() > 21) {
//          System.out.println("Dealer Busted! You win!");
//          dealerBust = true;
//          break;
//        }
//      }
//    }
//
//    // Compare Final Scores
//    if (!playerBust && !dealerBust) {
//      int playerTotal = player1.calculateHandValue();
//      int dealerTotal = dealer.calculateHandValue();
//
//      System.out.println("Your Total: " + playerTotal);
//      System.out.println("Dealer's Total: " + dealerTotal);
//
//      if (playerTotal > dealerTotal) {
//        System.out.println("You Win!");
//      } else if (playerTotal < dealerTotal) {
//        System.out.println("Dealer Wins!");
//      } else {
//        System.out.println("It's a Draw!");
//      }
//    }
//  }
//
//}






