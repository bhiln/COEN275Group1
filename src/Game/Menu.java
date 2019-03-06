package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
	private ButtonGroup selectDifficultyButtons;


	public Menu(Game game, GameState state) {
		this.game = game;
		this.state = state;


		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("A S T E R O I D S !!");
		title.setFont(new Font("Helvetica", Font.BOLD, 35));
		titlePanel.add(title);
		titlePanel.setMaximumSize(new Dimension(game.getSize().width,100));
		this.add(titlePanel);



		JPanel body = new JPanel();
		body.setSize(game.getSize().width*2/3, 240);
		body.setPreferredSize(new Dimension(game.getSize().width*2/3, 240));
		body.setMaximumSize(new Dimension(game.getSize().width*2/3, 240));
		body.setLayout(new BoxLayout(body,BoxLayout.LINE_AXIS));
		body.add(Box.createHorizontalGlue());
		JPanel menuOptionPanel = new JPanel();

		menuOptionPanel.setLayout(new BoxLayout(menuOptionPanel, BoxLayout.PAGE_AXIS));

		startButton = new JButton("S T A R T");
		startButton.setAlignmentX(menuOptionPanel.CENTER_ALIGNMENT);
        stopButton = new JButton("S T O P");
		stopButton.setAlignmentX(menuOptionPanel.CENTER_ALIGNMENT);
        creditsButton = new JButton("C R E D I T S");
		creditsButton.setAlignmentX(menuOptionPanel.CENTER_ALIGNMENT);
		menuOptionPanel.add(Box.createVerticalGlue());
        menuOptionPanel.add(startButton);
        menuOptionPanel.add(stopButton);
        menuOptionPanel.add(creditsButton);
		menuOptionPanel.add(Box.createVerticalGlue());
        menuOptionPanel.setSize(new Dimension(game.getSize().width/4,240));
		menuOptionPanel.setPreferredSize(new Dimension(game.getSize().width/4,240));
		menuOptionPanel.setMaximumSize(new Dimension(game.getSize().width/4,240));

		menuOptionPanel.setAlignmentX(menuOptionPanel.CENTER_ALIGNMENT);
		body.add(menuOptionPanel);


		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.PAGE_AXIS));

//        BoxLayout boxLayout = new BoxLayout(menuOptionPanel, BoxLayout.Y_AXIS);

		JLabel easyLabel = new JLabel("easy");
		easyLabel.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);
		easy = new JRadioButton();
		easy.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);
		JLabel mediumLabel= new JLabel("medium");
		mediumLabel.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);
		medium = new JRadioButton();
		medium.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);
		JLabel hardLabel = new JLabel("hard");
		hardLabel.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);
		hard = new JRadioButton();
		hard.setAlignmentX(difficultyPanel.CENTER_ALIGNMENT);

		selectDifficultyButtons = new ButtonGroup();
		selectDifficultyButtons.add(easy);
		selectDifficultyButtons.add(medium);
		selectDifficultyButtons.add(hard);
		easy.setSelected(true);


		difficultyPanel.add( Box.createVerticalGlue() );
		difficultyPanel.add(easyLabel);
		difficultyPanel.add(easy);
		difficultyPanel.add(mediumLabel);
		difficultyPanel.add(medium);
		difficultyPanel.add(hardLabel);
		difficultyPanel.add(hard);
		difficultyPanel.add(Box.createVerticalGlue());


		difficultyPanel.setSize(new Dimension(game.getSize().width/4,240));
		difficultyPanel.setPreferredSize(new Dimension(game.getSize().width/4,240));
		difficultyPanel.setMaximumSize(new Dimension(game.getSize().width/4,240));
		body.add(difficultyPanel);

		body.setBorder(LineBorder.createBlackLineBorder());
		body.add(Box.createHorizontalGlue());
        this.add(body);







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
