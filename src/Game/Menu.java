package Game;

import javax.swing.JPanel;

import Game.GameState.State;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener {

	private Game game;
	private GameState state;
	private JButton startGameButton;
	private JButton showLeaderboard;

	public Menu(Game game, GameState state) {
		this.game = game;
		this.state = state;

		JLabel dummyMenuItem = new JLabel("Menu");
		startGameButton = new JButton("start the game");
		showLeaderboard = new JButton("Show leaderboard");
		showLeaderboard.addActionListener(this);
		startGameButton.addActionListener(this);
		this.add(dummyMenuItem);
		this.add(startGameButton);
		this.add(showLeaderboard);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startGameButton) {
			if (game.getState().getState() == State.MENU) {
				game.startGame();
			}
			else if (game.getState().getState() == State.PAUSED) {
				game.resumeGame();
			}
		}
		else if(e.getSource() == showLeaderboard){
			game.showLeaderboard();
		}
	}
}
