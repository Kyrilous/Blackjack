package Objects;


import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BlackJackUI {
  public static void main(String[] args){
    Deck gameDeck = new Deck();
    gameDeck.shuffle();

    Player player = new Player(false);
    Player dealer = new Player(true);

    JFrame frame = new JFrame("Blackjack Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,600);
    frame.setLayout(null);

    //Create Layered Pane
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setBounds(0,0,800,600);
    frame.add(layeredPane);

    //Add background image
    ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\kyril\\Downloads\\CardDeck\\src\\images\\table.jpg");
    Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800,600,Image.SCALE_SMOOTH);
    ImageIcon scaledBackground = new ImageIcon(backgroundImage);
    JLabel backgroundLabel = new JLabel(scaledBackground);
    backgroundLabel.setBounds(0,0,800,600);
    layeredPane.add(backgroundLabel, Integer.valueOf(0));

    //Add Welcome Label

    JLabel welcomeLabel = new JLabel("Welcome To Blackjack!");
    welcomeLabel.setBounds(370,10,170,50);
    layeredPane.add(welcomeLabel, Integer.valueOf(1));

    // Add player panel
    JPanel playerPanel = new JPanel();
    playerPanel.setBounds(100, 350, 600, 120);
    playerPanel.setOpaque(false);
    layeredPane.add(playerPanel, Integer.valueOf(1));

    // Add dealer panel
    JPanel dealerPanel = new JPanel();
    dealerPanel.setBounds(100, 50, 600, 120);
    dealerPanel.setOpaque(false);
    layeredPane.add(dealerPanel, Integer.valueOf(1));

    // Deal initial cards to the dealer
    Card initialDealerCard = gameDeck.drawCard();
    dealer.hit(initialDealerCard);

    JLabel initialDealerCardLabel = createCardLabel(initialDealerCard);
    dealerPanel.add(initialDealerCardLabel);
    dealerPanel.revalidate();
    dealerPanel.repaint();


    // Add Stand button
    JButton standButton = new JButton("Stand");
    standButton.setBounds(300, 525, 210, 30);
    standButton.setBackground(Color.RED);

    standButton.addActionListener(e -> {
      new Thread(() -> {
        while (dealer.calculateHandValue() < 17) {
          SwingUtilities.invokeLater(() -> {
            Card dealerCard = gameDeck.drawCard();
            dealer.hit(dealerCard);

            JLabel dealerCardLabel = createCardLabel(dealerCard);
            dealerPanel.add(dealerCardLabel);
            dealerPanel.revalidate();
            dealerPanel.repaint();
          });

          try {
            Thread.sleep(2000);  // Pause for 2 seconds between displaying cards
          } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
          }
        }

        // Calculate totals and display results after dealer finishes
        SwingUtilities.invokeLater(() -> {
          int playerTotal = player.calculateHandValue();
          int dealerTotal = dealer.calculateHandValue();

          System.out.println("Your Total: " + playerTotal);
          System.out.println("Dealer's Total: " + dealerTotal);

          if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You Win!");
          } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer Wins!");
          } else {
            System.out.println("It's a Draw!");
          }
        });
      }).start();
    });

    layeredPane.add(standButton, Integer.valueOf(2));

    // Add Hit button
    JButton hitButton = new JButton("Hit");
    hitButton.setBounds(300, 500, 210, 30);
    hitButton.setBackground(Color.GREEN);

    hitButton.addActionListener(e -> {
      Card drawnCard = gameDeck.drawCard();
      player.hit(drawnCard);

      JLabel cardLabel = createCardLabel(drawnCard);
      playerPanel.add(cardLabel);
      playerPanel.revalidate();
      playerPanel.repaint();

      if (player.calculateHandValue() > 21) {
        System.out.println("You Busted! Total: " + player.calculateHandValue());
      }
    });
    layeredPane.add(hitButton, Integer.valueOf(2));

    frame.setVisible(true);

  }


  private static JLabel createCardLabel(Card card) {
    String imagePath = card.getImagePath();
    System.out.println("Attempting to load: " + imagePath);
    java.net.URL imageURL = BlackJackUI.class.getResource(imagePath);

    if (imageURL == null) {
      System.out.println("Error: Could not load image from path: " + imagePath);
      return new JLabel("Image Not Found");
    }

    ImageIcon originalIcon = new ImageIcon(imageURL);
    Image scaledImage = originalIcon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
    return new JLabel(new ImageIcon(scaledImage));
  }

}
