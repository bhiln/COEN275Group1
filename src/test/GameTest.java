package test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import Game.Game;
import Game.GameState;

public class GameTest {

  private Game game;
  
  @Before
  public void before() {
    game = new Game();
  }
  
  @Test
  public void testGame() {
    assertThat(game, notNullValue());
  }
  
  @Test
  public void testStartGame() {
    game.startGame();
    assertThat(game.getState().getState(), is(GameState.State.GAME));
  }
  
  @Test
  public void testPauseGame() {
    game.pauseGame();
    assertThat(game.getMenu().getDifficultyPanel().isVisible(), CoreMatchers.is(false));
  }
  
}
