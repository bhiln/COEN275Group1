package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import Game.Game;
import Game.GameState;
import Game.Physics;

public class GameStateTest {

  private GameState gameState;


  @Before
  public void before() {
    gameState = new GameState(new Game());
  }

  @Test
  public void testStartGame() {
    gameState.startGame();
    assertTrue(gameState.dodgeCount == 0);
  }

  @Test
  public void testEndGame() {
    gameState.endGame();
    assertTrue(gameState.getState() == GameState.State.DEATH);
  }

  @Test
  public void testPauseGame() {
    gameState.startGame();
    gameState.pauseGame();
    assertTrue(gameState.getState() == GameState.State.PAUSED);
  }
  
  @Test
  public void testBasicDifficulty() {
    gameState.startGame();
    gameState.beginnerDifficulty();
    assertThat(gameState.getShip().getHealth(), is(100));
    assertThat(gameState.getShip().getCollisionDamage(), is(5));
    assertThat(gameState.getShip().getLevelIncreaseAmmo(), is(50));
    assertThat(Physics.randomDyMax, is(3));
    assertThat(Physics.randomDyMin, is(1));
    assertThat(Physics.difficultyMult, is(5));
    assertThat(gameState.getDifficulty(), is(100));
  }
  
  @Test
  public void testAceDifficulty() {
    gameState.startGame();
    gameState.regularDifficulty();
    assertThat(gameState.getShip().getHealth(), is(100));
    assertThat(gameState.getShip().getCollisionDamage(), is(10));
    assertThat(gameState.getShip().getLevelIncreaseAmmo(), is(25));
    assertThat(Physics.randomDyMax, is(4));
    assertThat(Physics.randomDyMin, is(1));
    assertThat(Physics.difficultyMult, is(10));
    assertThat(gameState.getDifficulty(), is(75));
  }
  
  @Test
  public void testProDifficulty() {
    gameState.startGame();
    gameState.proDifficulty();
    assertThat(gameState.getShip().getHealth(), is(100));
    assertThat(gameState.getShip().getCollisionDamage(), is(25));
    assertThat(gameState.getShip().getLevelIncreaseAmmo(), is(10));
    assertThat(Physics.randomDyMax, is(6));
    assertThat(Physics.randomDyMin, is(2));
    assertThat(Physics.difficultyMult, is(15));
    assertThat(gameState.getDifficulty(), is(50));
  }
}
