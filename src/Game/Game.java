package Game;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Asteroids.AsteroidWall;

/**
 * The Class Game.
 */
public class Game {
	
	/** The frame. */
	private JFrame frame;
	
	/** The panel. */
	private JPanel panel;
	
	/** The cl. */
	private CardLayout cl;

	/** The state. */
	private GameState state;
	
	/** The menu. */
	private Menu menu;
	
	/** The renderer. */
	private Renderer renderer;
	
	/** The leaderboard. */
	private Leaderboard leaderboard;
	
	/** The input. */
	//private Thread physics;
	private KeyInput input;
	
	/** The physics. */
	private Physics physics;
	
	/** The physics thread. */
	private Thread physicsThread;
	
	/** The rand. */
	private Random rand = new Random();

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		frame = new JFrame("Avoid the Asteroid!");
		frame.setSize(1280, 720); // set frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input = new KeyInput(frame);


		cl = new CardLayout();
		panel = new JPanel(cl);

		state = new GameState(this);

		menu = new Menu(this, state);
		renderer = new Renderer(this, state, input);
		leaderboard = new Leaderboard(this);
		panel.add(menu, "Menu");
		panel.add(renderer, "Game");
		panel.add(leaderboard, "Leaderboard");

		physics = new Physics(this, input);
		physicsThread = new Thread(physics);
		physicsThread.start();

		frame.add(panel);
		// add Graphics to frame
		frame.setVisible(true); // display frame
		frame.setResizable(false);
		frame.setFocusable(true);

	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public GameState getState() {
		return this.state;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Dimension getSize() {
		return frame.getSize();
	}
	

	/**
	 * Start game.
	 */
	// starts a new game
	public void startGame() {
		state.startGame();
		resumeGame();
	}
	
	/**
	 * Pause game.
	 */
	public void pauseGame() {
		menu.pause();
		cl.show(panel, "Menu");
		frame.repaint();
		physics.stopTimer();
		renderer.stopTimer();
	}

	/**
	 * Resume game.
	 */
	// if game is paused, resume game
	public void resumeGame() {
		menu.resume();
		state.resumeGame();
		physics.restartTimer();
		renderer.restartTimer();
		cl.show(panel, "Game");
	}

	/**
	 * Finish game.
	 */
	// game has been won, switch to win state
	public void finishGame() {

	}

	/**
	 * End game.
	 */
	// game has been lost, switch to lose state
	public void endGame() {
		state.endGame();
		physics.stopTimer();
		renderer.stopTimer();

		leaderboard.refresh();
		cl.show(panel, "Leaderboard");


		//TODO: set stats on menu
	}

	/**
	 * Show leaderboard.
	 */
	public void showLeaderboard(){
		if(getState().getState() == GameState.State.MENU){
			leaderboard.refresh();
			cl.show(panel, "Leaderboard");
		}
	}
	

	
	
	/**
	 * Exit game.
	 */
	public void exitGame() {
		state.exitGame();
		cl.show(panel, "Menu");
	}

	/**
	 * Sets the background.
	 *
	 * @param backgroundColor the new background
	 */
	public void setBackground(Color backgroundColor) {
		renderer.setBackground(backgroundColor);
	}
	
	/**
	 * Pass level.
	 */
	public void passLevel() {
		state.setLevel(state.getLevel() + 1);
		state.getShip().setAmmo(state.getShip().getAmmo() + state.getShip().getLevelIncreaseAmmo());
	}
	
	/**
	 * Evaluate wall.
	 */
	public void evaluateWall() {
		// 10 dodges = 1 level increase
		if (state.dodgeCount % 10 == 0) {
			state.getAsteroids().addAll(new AsteroidWall(rand.nextInt(40-state.getLevel()), state.getLevel()));
			if (state.getLevel() == 10) {
				finishGame();
			}
		}
	}
}
