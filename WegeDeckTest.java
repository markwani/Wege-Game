import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class WegeDeckTest {
  
  private WegeDeck deck;
  
  @Before
  public void setUp() {
    deck = new WegeDeck();
  }
  
  @Test
  public void testReset() {
    deck.reset();
    assertEquals(42, deck.getCardsInDeck());
  }
  
  @Test
  public void testShuffle() {
    LinkedList<WegeCard> initialCards = new LinkedList<>(deck.cards);
    deck.shuffle();
    assertNotEquals(initialCards, deck.cards);
  }
  
  @Test
  public void testDrawCard() {
    assertEquals(42, deck.getCardsInDeck());
    deck.drawCard();
    assertEquals(41, deck.getCardsInDeck());
  }
  
  @Test
  public void testGetCurrentCard() {
    WegeCard currentCard = deck.getCurrentCard();
    assertNotNull(currentCard);
  }
  
  @Test
  public void testToString() {
    String cardString = deck.toString();
    assertNotNull(cardString);
  }
  
  @Test
  public void testRemove() {
    int initialSize = deck.cards.size();
    deck.remove();
    assertEquals(initialSize - 1, deck.cards.size());
  }
  
  @Test
  public void testGetNextCard() {
    WegeCard nextCard = deck.getNextCard();
    assertNotNull(nextCard);
  }

}
