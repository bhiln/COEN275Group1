package Game;
import Asteroids.Asteroid;
import Ship.Ship;
import Stars.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener {
    private Game game;
    private GameState state;

    private int delay = 16;
    protected Timer timer;

    private long startTime;
    private long timeAlive = 0L;
    private long lastTimeAlive = 0L;

    JLabel lblLevel, lblDodge, lblTimeAlive;


    public Renderer(Game game, GameState state){
        this.game = game;
        this.state = state;

        timer = new Timer(delay, this);
        timer.start(); // start the timer

        lblLevel = new JLabel("Level " + state.level);
        lblLevel.setForeground(Color.WHITE);
        lblDodge = new JLabel("Dodged " + state.dodgeCount + " Asteroids!");
        lblDodge.setForeground(Color.WHITE);
        lblTimeAlive = new JLabel("Time alive: " + timeAlive);
        lblTimeAlive.setForeground(Color.WHITE);

        // add level and score labels to frame
        add(lblLevel, BorderLayout.NORTH);
        add(lblDodge, BorderLayout.NORTH);
        add(lblTimeAlive, BorderLayout.NORTH);

        // set background to dark gray
        setBackground(Color.DARK_GRAY);
        init();
        startTime = System.currentTimeMillis();

    }
    
    public void init() {
        // initialize start time
        startTime = System.currentTimeMillis();
    }
    
    public void actionPerformed(ActionEvent e) {
    	// will run when the timer fires
        repaint();
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g); // call superclass's paintComponent

        // calculate time alive. Only update label if it's a new second
        timeAlive = (System.currentTimeMillis() - startTime) / 1000L;
        if (timeAlive > lastTimeAlive) {
            lblTimeAlive.setText("Time alive: " + timeAlive);
            lastTimeAlive = timeAlive;
        }

        Ship ship = state.getShip();
        g.setColor(ship.getDrawColor());
        g.fillPolygon(ship.getShape());

        ArrayList<Star> stars = state.getStars();
        for (Star s : stars) {
            g.setColor(s.getDrawColor());
            g.fillPolygon(s.getShape());
        }

        ArrayList<Asteroid> asteroids = state.getAsteroids();
        for (Asteroid a : asteroids) {
            g.setColor(a.getDrawColor());
            g.fillPolygon(a.getShape());
        }

        lblDodge.setText("Dodged " + state.dodgeCount + " Asteroids!");
        lblLevel.setText("Level " + state.level);
    }
}
