package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Game {
	private JFrame frame;
	private JPanel panel;
	private CardLayout cl;

	private GameState state;
	private Menu menu;
	private Renderer renderer;
	private Physics physics;
	private Thread physicsThread;

	public Game() {
		frame = new JFrame("Avoid the Asteroid!");
		frame.setSize(1280, 720); // set frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl = new CardLayout();
		panel = new JPanel(cl);

		state = new GameState(this);

		menu = new Menu(this, state);
		renderer = new Renderer(this, state);
		panel.add(menu, "Menu");
		panel.add(renderer, "Game");

		physics = new Physics(this, state);
		physicsThread = new Thread(physics);
		physicsThread.start();

		frame.add(panel);
		// add Graphics to frame
		frame.setVisible(true); // display frame
		frame.setResizable(false);
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
		physics.restartTimer();
		renderer.restartTimer();
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
		state.endGame();
		JButton btnRestart = new JButton("Restart");
		JButton btnExitToMenu = new JButton("Exit to menu");
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(btnRestart, BorderLayout.SOUTH);
		pnlButtons.add(btnExitToMenu, BorderLayout.SOUTH);

		btnExitToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.remove(pnlButtons);
				exitGame();
			}
		});
		
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.remove(pnlButtons);
				startGame();
			}
		});
		
		frame.add(pnlButtons, BorderLayout.SOUTH);
		frame.repaint();
		physics.stopTimer();
		renderer.stopTimer();
		
		//TODO: set stats on menu
	}

	// return to menu
	public void exitGame() {
		state.exitGame();
		cl.show(panel, "Menu");
	}
	
	public void setBackground(Color backgroundColor) {
		renderer.setBackground(backgroundColor);
	}
}
