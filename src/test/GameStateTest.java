package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import Game.Game;
import Game.GameState;
import Game.Physics;
import Projectiles.Bullet;

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
  
  @Test
  public void basicDifficultyTest() {
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
  public void aceDifficultyTest() {
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
  public void proDifficultyTest() {
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
