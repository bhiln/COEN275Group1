package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;

import Asteroids.AsteroidWall;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Game {
	private JFrame frame;
	private JPanel panel;
	private CardLayout cl;

	private GameState state;
	private Menu menu;
	private Renderer renderer;
	private Leaderboard leaderboard;
	// private Thread physics;
	private KeyInput input;
	private Physics physics;
	private Thread physicsThread;

	private Random rand = new Random();
	private Sound sound;
	Thread soundThread;

	public Game() {
		frame = new JFrame("Asteroids!");
		frame.setSize(1280, 720); // set frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input = new KeyInput(frame);

		cl = new CardLayout();
		panel = new JPanel(cl);

		state = new GameState(this);

		menu = new Menu(this, state);
		renderer = new Renderer(this, input);
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
		
		//https://www.dl-sounds.com/royalty-free/power-bots-loop/
		sound = new Sound("assets/PowerBotsLoop.wav", true);
		try {
			soundThread = new Thread(sound);
			soundThread.start();
		}
		catch(Exception e) {
			System.out.println("SOUND ERROR");
		}

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
		resumeGame();
	}

	public void pauseGame() {
		menu.pause();
		cl.show(panel, "Menu");
		frame.repaint();
		physics.stopTimer();
		renderer.stopTimer();
	}

	// if game is paused, resume game
	public void resumeGame() {
		menu.resume();
		state.resumeGame();
		physics.restartTimer();
		renderer.restartTimer();
		cl.show(panel, "Game");
	}

	// game has been won, switch to win state
	public void finishGame() {

	}

	// game has been lost, switch to lose state
	public void endGame() {
		state.endGame();

		physics.stopTimer();
		renderer.stopTimer();

		leaderboard.refresh();
		cl.show(panel, "Leaderboard");
	}

	public void showLeaderboard() {
		if (getState().getState() == GameState.State.MENU) {
			leaderboard.refresh();
			cl.show(panel, "Leaderboard");
		}

	}
	// return to menu

	public void exitGame() {
		state.exitGame();
		cl.show(panel, "Menu");
	}

	public void setBackground(Color backgroundColor) {
		renderer.setBackground(backgroundColor);
	}

	public void passLevel() {
		state.setLevel(state.getLevel() + 1);
		state.getShip().setAmmo(state.getShip().getAmmo() + state.getShip().getLevelIncreaseAmmo());
	}

	public void evaluateWall() {
		// 10 dodges = 1 level increase
		if (state.dodgeCount % 10 == 0) {
			state.getAsteroids().addAll(new AsteroidWall(rand.nextInt(40 - state.getLevel()), state.getLevel()));
			if (state.getLevel() == 10) {
				finishGame();
			}
		}
	}
}
