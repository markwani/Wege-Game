import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

public class WegeTest {
  private Wege wege;
  
  @Before
  public void setUp() {
    new JFXPanel();
    wege = new Wege();
  }
  
  @Test
  public void testSetUpBoard() {
    WegeButton[][] board = wege.setUpBoard();
    assertEquals(6, board.length);
    assertEquals(6, board[0].length);
    assertTrue(board[0][0].getOnMouseClicked() != null);
  }
  
  @Test
  public void testIsGameOver() {
    assertFalse(wege.isGameOver());
    // fill up the board
    for (int i = 0; i < wege.getRow(); i++) {
      for (int j = 0; j < wege.getColumns(); j++) {
        wege.setUpBoard();
      }
    }
    assertTrue(wege.isGameOver());
  }
  
  @Test
  public void testIsMoveLegal() {
    assertTrue(wege.isMoveLegal(wege.getButton()));
    WegeButton deckButton = wege.getDeck();
    deckButton.setCard(new WegeCard(WegeCard.CardType.BRIDGE, false, false));
    wege.nextCard(deckButton);
    assertFalse(wege.isMoveLegal(deckButton));
  }
  
  @Test
    public void testSetAndGetButton() {
        WegeButton button = new WegeButton(50, 50);
        Wege game = new Wege();
        game.setButton(button);
        assertEquals(button, game.getButton());
    }

    @Test
    public void testGetRow() {
        Wege game = new Wege();
        assertEquals(6, game.getRow());
    }
    
    @Test
    public void testGetColumn() {
        Wege game = new Wege();
        assertEquals(6, game.getColumns());
    }
}
