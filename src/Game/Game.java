package Game;

import java.awt.*;

import javax.swing.JPanel;

import javax.swing.JFrame;

public class Game {
	private JFrame frame;
	private JPanel panel;
	private CardLayout cl;

	private GameState state;
	private Menu menu;
	private Renderer renderer;
	private Thread physics;
	private KeyInput input;

	public Game() {
		frame = new JFrame("Avoid the Asteroid!");
		frame.setSize(1280, 720); // set frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input = new KeyInput(frame);


		cl = new CardLayout();
		panel = new JPanel(cl);

		state = new GameState(this);

		menu = new Menu(this, state);
		renderer = new Renderer(this, state);
		panel.add(menu, "Menu");
		panel.add(renderer, "Game");

		physics = new Thread(new Physics(this, state, input));
		physics.start();

		frame.add(panel);
		// add Graphics to frame
		frame.setVisible(true); // display frame
		frame.setResizable(false);
		frame.setFocusable(true);

	}

	public GameState getState() {
		return this.state;
	}

	public Dimension getSize() {
		return frame.getSize();
	}

	// starts a new game
	public void startGame() {
		state.startGame();
		cl.show(panel, "Game");
	}

	// if game is paused, resume game
	public void resumeGame() {

	}

	// game has been won, switch to win state
	public void finishGame() {

	}

	// game has been lost, switch to lose state
	public void endGame() {

	}

	// return to menu
	public void exitGame() {

	}
}
