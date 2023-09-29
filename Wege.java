import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.ClipboardContent;
import javafx.application.Application;
import javafx.scene.transform.Rotate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import javafx.stage.Stage;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import java.io.File;

/**
 * The Wege class represents the main class of the Wege game.
 * It extends Application and defines the behavior of the game.
 * It initializes and sets up the board, grid, deck, stockpile, and the players.
 * @author Mark Wani
 */
public class Wege extends Application{
  private WegeButton[][] board; //A 2D array of WegeButton objects that represents the game board.
  private int currentPlayer; //An integer variable that stores the index of the current player.
  private WegeDeck deck; //An object of the WegeDeck class that represents the deck of cards used in the game.
  private WegeCard currentCard; //An object of the WegeCard class that represents the current card being used.
  private ArrayList<WegeCard> stockpile; //An ArrayList of WegeCard objects that represents the pile of cards used by players when they select a special card.
  private boolean isGameOver = false; // A boolean variable that indicates whether the game is over or not.
  private int rows; //An integer variable that stores the number of rows in the game board.
  private int columns; //An integer variable that stores the number of columns in the game board.
  private int specialCards; //An integer variable that stores the number of special cards in the game.
  private int regularCards; //An integer variable that stores the number of regular cards in the game.
  private Button next; // A Button object that represents the "Next" button used to progress the game.
  private GridPane grid; //A GridPane object that represents the game board.
  private WegeButton deckButton; //A WegeButton object that represents the deck of cards used in the game.
  private WegeButton button; //A WegeButton object that represents the current button selected by a player.
  private Scene scene; //A Scene object that represents the game window.
  private Label labelB; //A Label object that displays the current player's turn.
  
  /**
   * Constructs a new Wege object.
   * Initializes the game board, deck, stockpile, and the players.
   */
  public Wege(){
    rows = 6;
    columns = 6;
    deck = new WegeDeck();
    deck.shuffle();
    stockpile = new ArrayList<WegeCard>();
    currentPlayer = 0;
    isGameOver = false;
    board = setUpBoard();
  }
  
  /**
   * The start method is called when the application is started.
   * Initializes the game UI and sets up the event handlers.
   * @param primaryStage The primary stage for the game
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      primaryStage.setTitle("Wege Game");
      
      // Create the title label
      Label titleLabel = new Label("Wege Game");
      titleLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: white; -fx-font-weight: bold;");
      
      // Set the icon for the application
      String imagePath = "H:/Programming Project 4/Wege.png";
      File imageFile = new File(imagePath);
      String imageUrl = imageFile.toURI().toString();
      primaryStage.getIcons().add(new Image(imageUrl));
      
      // Create the background
      BorderPane background = new BorderPane();
      background.setStyle("-fx-background-color: #4D4D4D;");
      
      // Add the title to the top of the background
      background.setTop(titleLabel);
      BorderPane.setAlignment(titleLabel, Pos.CENTER);
      
      // Create the grid
      grid = setUpGrid();
      grid.setStyle("-fx-background-color: white; -fx-border-color: #4D4D4D; -fx-border-width: 2px;");
      
      // Create the deck button
      deckButton = new WegeButton(75, 75);
      deckButton.setStyle("-fx-background-color: white; -fx-border-color: #4D4D4D; -fx-border-width: 2px;");
      deckButton.setAlignment(Pos.CENTER);
      
      // Create the turn label
      labelB = new Label("LAND's turn");
      labelB.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");
      
      // Create the "Next" button
      next = new Button("Next");
      next.setStyle("-fx-background-color: #4D4D4D; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
      
      // Create the bottom pane
      FlowPane bottomPane = new FlowPane(deckButton, next, labelB);
      bottomPane.setStyle("-fx-background-color: #4D4D4D; -fx-padding: 10px;");
      bottomPane.setAlignment(Pos.CENTER);
      bottomPane.setHgap(20);
      
      // Add the grid and the bottom pane to the background
      background.setCenter(grid);
      background.setBottom(bottomPane);
      
      // Create the scene and add the background to it
      scene = new Scene(background, 1280, 800);
      
      // Set the current card on the deck button and add the mouse click event handler
      deckButton.setCard(deck.getCurrentCard());
      deckButton.setOnMouseClicked(mouseClicked(deckButton));
      
      // Add the event handler for the "Next" button
      next.setOnAction(event -> {
        if (labelB.getText().equals("LAND's turn")) {
          labelB.setText("WATERS's turn");
        } else {
          labelB.setText("LAND's turn");
        }
      });
      
      // Show the scene
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  /**
   * Sets up the game board.
   * Initializes each button in the board with the WegeButton class.
   * Sets up the mouse click event handler for each button.
   * @return The initialized game board
   */
  public WegeButton[][] setUpBoard(){
    WegeButton[][] board = new WegeButton[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        board[i][j] = new WegeButton((90), (90));
        board[i][j].setOnMouseClicked(mouseClicked(board[i][j]));
      }
    }
    return board;
  }
  
  /**
   * Creates a new GridPane object and sets up the board by adding each WegeButton to the grid in its corresponding position.
   * @return the created GridPane object
   */
  public GridPane setUpGrid(){
    GridPane grid = new GridPane();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid.add(board[i][j], j, i);
      }
    }
    return grid;
  }
  
  /**
   * Retrieves the deck button.
   * @return the WegeButton object representing the deck button
   */
  public WegeButton getDeck(){
    return deckButton;
  }
  
  /**
   * Creates a MouseEvent EventHandler for WegeButton objects.
   * @param button the WegeButton object to which the EventHandler will be applied
   * @return the created EventHandler object
   */
  private EventHandler<MouseEvent> mouseClicked(WegeButton button) {
    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
      @Override 
      public void handle(MouseEvent e) {
        if(e.getSource() == getDeck()){
          deckButton.rotate();
        }
        else if(e.getSource() == button){
          if(isMoveLegal(button) == true){
            if (button.getCard() == null) {
              WegeCard deckCard = deckButton.getCard();
              if (deckCard != null) {
                button.setCard(deck.getCurrentCard());
                stockpile.add(deck.getCurrentCard());
                deck.remove();
                deckButton.setCard(deck.getNextCard());
              }
            }
            else if(isSpecialCard(deckButton) == true){
              stockpile.add(button.getCard());
              button.setCard(deck.getCurrentCard());
              deck.remove();
            }
            else
              System.out.println("Move not legal!");
          }
        }
      }
    };
    return eventHandler;
  }
  
  /**
   * Sets a given WegeButton object as the current button.
   * @param button the WegeButton object to set as current
   */
  public void setButton(WegeButton button){
    this.button = button;
  }
  
  /**
   * Retrieves the number of rows in the board.
   * @return the number of rows in the board
   */
  public int getRow(){
    return rows;
  }
  
  /**
   * Retrieves the number of columns in the board.
   * @return the number of columns in the board
   */
  public int getColumns(){
    return columns;
  }
  
  /**
   * Retrieves the current button.
   * @return the WegeButton object representing the current button
   */
  public WegeButton getButton(){
    return button;
  }
  
  /**
   * Checks whether the game is over by iterating through the board and checking if any of the positions are null.
   * @return true if the board is full, false otherwise
   */
  public boolean isGameOver() {
    boolean full = true;
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == null) {
          full = false;
          break;
        }
      }
      if (!full) {
        break;
      }
    }
    return full;
  }
  
  /**
   * Retrieves the number of regular cards.
   * @return the number of regular cards
   */
  public int getRegularCards(){
    return this.regularCards;
  }
  
  /**
   * Retrieves the number of special cards.
   * @return the number of special cards
   */
  public int getSpecialCards(){
    return this.specialCards;
  }
  
  /**
   * Retrieves the current card.
   * @return the WegeCard object representing the current card
   */
  public WegeCard getCurrentCard(){
    return currentCard;
  }
  
  /**
   * Draws a new card from the deck and sets it to the provided WegeButton. 
   * The card is then rotated by 90 degrees clockwise.
   * @param button the WegeButton to set the new card on
   */
  public void nextCard(WegeButton button){
    WegeCard card = deck.drawCard();
    button.setCard(card);
    Rotate rotate = new Rotate();
    rotate.setAngle(90);
    button.getTransforms().add(rotate);
  } 
  
  /**
   * Checks if a move to the provided WegeButton is legal based on the game rules.
   * A move is illegal if the button is the deck button, or if the button already has a card
   * and the card is not a BRIDGE or a COSSACK card. If the move is illegal, returns false.
   * 
   * @param button the WegeButton to check for legality
   * @return true if the move is legal, false otherwise
   */
  public boolean isMoveLegal(WegeButton button){
    boolean move = true;
    if(button.getCard() == null)
      ;
    else if(button.getCard() == deckButton.getCard())
      ;
    else if(button == deckButton)
      move = false;
    else if(button.getCard() != null){
      if(button.getCard().getCardType() != WegeCard.CardType.BRIDGE){
        if(button.getCard().getCardType() != WegeCard.CardType.COSSACK)
          move = false;
        else if(button.getCard().getCardType() == WegeCard.CardType.COSSACK)
          ;
      }
      else if(button.getCard().getCardType() == WegeCard.CardType.BRIDGE)
        ;
      else if(button.getCard().getCardType() == WegeCard.CardType.COSSACK)
        ;
    }
    return move;
  }
  
  /**
   * Checks if the card on the provided WegeButton is a special card, which includes
   * BRIDGE and COSSACK cards.
   * 
   * @param button the WegeButton to check for special card
   * @return true if the card on the button is a special card, false otherwise
   */
  public boolean isSpecialCard(WegeButton button){
    boolean sc = false;
    if(button.getCard().getCardType() == WegeCard.CardType.BRIDGE)
      sc = true;
    else if(button.getCard().getCardType() == WegeCard.CardType.COSSACK)
      sc = true;
    return sc;
  }
  
  /**
   * This method is the main entry point for the Wege game. It sets the number of rows, columns, and special cards
   * for the game by parsing the arguments passed to it. If no arguments are passed, default values of 6 for rows
   * and columns and 3 for special cards are used. It then launches the Wege application with the specified arguments.
   * @param args the command-line arguments passed to the program
   */
  public static void main(String[] args) {
    int rows = 6;
    int columns = 6;
    int specialCards = 3;
    
    if(args.length > 0) {
      rows = Integer.parseInt(args[0]);
    }
    if(args.length > 1) {
      columns = Integer.parseInt(args[1]);
    }
    if(args.length > 2) {
      specialCards = Integer.parseInt(args[2]);
    }
    
    String[] strArgs = { String.valueOf(rows), String.valueOf(columns), String.valueOf(specialCards) };
    Application.launch(Wege.class, strArgs);
  }
}




    
    
      
      
  
  
  
    
  
  