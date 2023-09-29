/*
 * This class represents a deck of WegeCards used in the Wege game.
 * The deck contains a certain number of regular and special cards of different types.
 * It can be reset and shuffled, and cards can be drawn from it.
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;

public class WegeDeck {
  public LinkedList<WegeCard> cards; // List of WegeCards in the deck
  private int cardsInDeck; // Number of cards currently in the deck
  private int regularCards; // Number of regular cards in the deck
  private int specialCards; // Number of special cards in the deck
  private Wege wege; // The Wege instance this deck belongs to
  
  /**
   * Constructs a new WegeDeck object.
   * Initializes the cards list and calls the reset method to fill the deck with cards.
   */
  public WegeDeck() {
    cards = new LinkedList<>();
    reset();
  }
  
  /**
   * Resets the deck by removing all cards and filling it with a new set of cards.
   * The number of regular and special cards of each type is predetermined.
   */
  public void reset() {
    cardsInDeck = 0;
    
    for (int i = 0; i <= 12; i++) {
      cards.add(new WegeCard(WegeCard.CardType.WATER, false, false));
      cardsInDeck++;
      cards.add(new WegeCard(WegeCard.CardType.LAND, false, false));
      cardsInDeck++;
    }
    
    for (int j = 0; j <= 3; j++) {
      cards.add(new WegeCard(WegeCard.CardType.WATER, true, true));
      cardsInDeck++;
      cards.add(new WegeCard(WegeCard.CardType.LAND, true, true));
      cardsInDeck++;
    }
    
    for (int k = 0; k <= 2; k++) {
      cards.add(new WegeCard(WegeCard.CardType.WATER, true, false));
      cardsInDeck++;
      cards.add(new WegeCard(WegeCard.CardType.LAND, true, false));
      cardsInDeck++;
    }
    
    for (int l = 0; l <= 3; l++) {
      cards.add(new WegeCard(WegeCard.CardType.BRIDGE, false, false));
      cardsInDeck++;
      cards.add(new WegeCard(WegeCard.CardType.COSSACK, false, false));
      cardsInDeck++;
    }
    
    shuffle();
  }
  
  /**
   * Shuffles the cards in the deck randomly.
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }
  
  /**
   * Draws a card from the deck.
   * If the deck is empty, prints a message to the console and returns null.
   * Otherwise, removes the top card from the deck and returns it.
   * @return The WegeCard that was drawn from the deck.
   */
  public WegeCard drawCard() {
    if (cards.isEmpty()) {
      System.out.println("Deck is empty!");
      return null;
    }
    cardsInDeck--;
    System.out.println("Card drawn: " + cards.get(0).toString());
    return cards.get(0);
  }
  
  /**
   * Returns the number of cards currently in the deck.
   * @return The number of cards currently in the deck.
   */
  public int getCardsInDeck() {
    return cardsInDeck;
  }
  
  /**
   * Returns the current card that is ontop of the deck pile.
   * @return the current card on top of deck.
   */
  public WegeCard getCurrentCard(){
    return cards.getFirst();
  }
  
  /*
   * Returns the current card's location in memory
   * @return the current card's location in memory
   */
  public String toString(){
    return getCurrentCard().toString();
  }
  
  /**
   * Removes the first card in the pile
   */
  public void remove(){
    cards.removeFirst();
  }
  
  /*
   * Returns the next card inside of the deck.
   * @return the next card inside of the deck.
   */
  public WegeCard getNextCard(){
    return cards.get(0);
  }
}
