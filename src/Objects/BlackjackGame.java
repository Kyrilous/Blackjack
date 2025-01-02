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






