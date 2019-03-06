package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import Game.GameState.State;

public class Menu extends JPanel implements ActionListener {

	private Game game;
	private GameState state;
	private JButton startButton;
	private JButton stopButton;
	private JButton creditsButton;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;

	public Menu(Game game, GameState state) {
		this.game = game;
		this.state = state;

		this.setSize(1280, 720);
		
		JPanel titlePanel = new JPanel();
		JLabel tittle = new JLabel("A S T E R O I D S !!");
		titlePanel.add(tittle, BorderLayout.CENTER);
		

		JPanel menuOptionPanel = new JPanel();
		BoxLayout layoutManagerMenu = new BoxLayout(menuOptionPanel, BoxLayout.Y_AXIS);
		menuOptionPanel.setLayout(layoutManagerMenu);
        startButton = new JButton("S T A R T");
        stopButton = new JButton("S T O P");
        creditsButton = new JButton("C R E D I T S");
        menuOptionPanel.add(startButton);
        menuOptionPanel.add(stopButton);
        menuOptionPanel.add(creditsButton);
        
        JPanel difficultyPanel = new JPanel();
//        BoxLayout boxLayout = new BoxLayout(menuOptionPanel, BoxLayout.Y_AXIS);
        menuOptionPanel.setLayout(layoutManagerMenu);
        easy = new JRadioButton();
        medium = new JRadioButton();
        hard = new JRadioButton();
        difficultyPanel.add(easy);
        difficultyPanel.add(stopButton);
        difficultyPanel.add(creditsButton);
        
        this.add(titlePanel, BorderLayout.CENTER);
        this.add(menuOptionPanel,  BorderLayout.WEST);
        this.add(difficultyPanel,  BorderLayout.EAST);
        Border thinBorder = LineBorder.createBlackLineBorder();
        this.setBorder(thinBorder);

        
        JLabel easyLabel = new JLabel("easy");
        this.add(easyLabel);
        this.add(easy);
        
        JLabel mediumLabel= new JLabel("medium");
        this.add(mediumLabel);
        this.add(medium);
        
        JLabel hardLabel = new JLabel("hard");
        this.add(hardLabel);
        this.add(hard);
        
        stopButton.addActionListener(this);
        startButton.addActionListener(this);
        creditsButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stopButton) {
		  System.exit(0);
		} 
		if (e.getSource() == creditsButton) {
          
        }
		
	  if (e.getSource() == startButton) {
			if (game.getState().getState() == State.MENU) {
				game.startGame();
			}
			else if (game.getState().getState() == State.PAUSED) {
				game.resumeGame();
			}
		}
	}
}
