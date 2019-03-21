package test;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import Game.Game;
import Game.GameState;

public class GameStateTest {

  private GameState gameState;


  @Before
  public void before() {
    gameState = new GameState(new Game());
  }

  @Test
  public void startGameTest() {
    gameState.startGame();
    assertTrue(gameState.dodgeCount == 0);
  }

  @Test
  public void endGametest() {
    gameState.endGame();
    assertTrue(gameState.getState() == GameState.State.DEATH);
  }

  @Test
  public void pauseGameTest() {
    gameState.startGame();
    gameState.pauseGame();
    assertTrue(gameState.getState() == GameState.State.PAUSED);
  }

}
