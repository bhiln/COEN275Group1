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
	//private Thread physics;
	private KeyInput input;
	private Physics physics;
	private Thread physicsThread;
	
	private Random rand = new Random();

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
		cl.show(panel, "Menu");
		frame.repaint();
		physics.stopTimer();
		renderer.stopTimer();
	}

	// if game is paused, resume game
	public void resumeGame() {
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
//		JButton btnRestart = new JButton("Restart");
//		JButton btnExitToMenu = new JButton("Exit to menu");
//		JPanel pnlButtons = new JPanel();
//		pnlButtons.setLayout(new FlowLayout());
//		pnlButtons.add(btnRestart, BorderLayout.SOUTH);
//		pnlButtons.add(btnExitToMenu, BorderLayout.SOUTH);
//
//		btnExitToMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				frame.remove(pnlButtons);
//				exitGame();
//			}
//		});
//
//		btnRestart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				frame.remove(pnlButtons);
//				startGame();
//			}
//		});

		//frame.add(pnlButtons, BorderLayout.SOUTH);
		//frame.repaint();
		physics.stopTimer();
		renderer.stopTimer();

		leaderboard.refresh();
		cl.show(panel, "Leaderboard");


		//TODO: set stats on menu
	}

	public void showLeaderboard(){
		if(getState().getState() == GameState.State.MENU){
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
		int newLevel = state.getLevel() + 1;
		state.setLevel(newLevel);
		//toggle new color for background
		
		switch(newLevel) {
			case 1:	setBackground(new Color(0,0,100));
					break;
			case 2: setBackground(new Color(0,0,130));
					break;
			case 3: setBackground(new Color(0,0,160));
					break;
			case 4: setBackground(new Color())
		
		}
		
		//setBackground(new Color(0,0,128));
	}
	
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
