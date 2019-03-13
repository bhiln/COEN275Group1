package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.json.JSONArray;
import org.json.JSONObject;
import Game.GameState.State;

/**
 * The Class Leaderboard.
 */
public class Leaderboard extends JPanel implements ActionListener {

    /** The game. */
    private Game game;
    
    /** The return to menu. */
    private JButton returnToMenu;
    
    /** The url. */
    private URL url;
    
    /** The url base. */
    private String urlBase = "https://blog.jacksonjw.com";//a subdomain jackson no longer uses
    
    /** The url string. */
    private String urlString = urlBase + "/getleaderboard";
    
    /** The record score URL. */
    private String recordScoreURL = urlBase + "/recordScore?";
    
    /** The good URL. */
    private boolean goodURL;
    
    /** The valid leaderboard. */
    private boolean validLeaderboard;
    
    /** The leaderboard. */
    private JSONObject leaderboard;
    
    /** The connecting. */
    private JLabel connecting;

    /** The leaderboard body. */
    private JPanel leaderboardBody;
    
    /** The leaderboard table. */
    private JPanel leaderboardTable;
    
    /** The leaderboard form. */
    private JPanel leaderboardForm;
    
    /** The leaderboard header. */
    private JPanel leaderboardHeader;

    /** The title font. */
    private static Font titleFont = new Font("Helvetica", Font.BOLD, 35);
    
    /** The header font. */
    private static Font headerFont = new Font("Helvetica", Font.BOLD, 18);
    
    /** The score font. */
    private static Font scoreFont = new Font("Helvetica", Font.PLAIN, 16);


    /** The name. */
    private JTextField name;
    
    /** The submit score. */
    private JButton submitScore;

    /** The back ground. */
    private Image backGround;



    /**
     * Instantiates a new leaderboard.
     *
     * @param game the game
     */
    public Leaderboard(Game game) {
        this.game = game;
        validLeaderboard = false;
        backGround=new ImageIcon("assets/game2.png").getImage();



        JLabel leaderboardTitle = new JLabel("Leaderboard");
        leaderboardTitle.setFont(titleFont);
        leaderboardTitle.setForeground(Color.red);
        returnToMenu = new JButton("Back to menu");
        this.add(leaderboardTitle);



        leaderboardBody = new JPanel();
        leaderboardBody.setPreferredSize(new Dimension(game.getSize().width,game.getSize().height/2+75));
        //leaderboardBody.setBackground(new Color(Color.TRANSLUCENT));
        leaderboardBody.setOpaque(false);


        leaderboardTable = new JPanel();
        leaderboardTable.setPreferredSize(new Dimension(game.getSize().width/2+20,game.getSize().height/2+75));
        leaderboardTable.setOpaque(false);
        leaderboardHeader = getRowPanel();
        leaderboardHeader.setOpaque(false);
        leaderboardHeader.setPreferredSize(new Dimension(game.getSize().width/2,50));
        leaderboardHeader.setMinimumSize(leaderboardHeader.getPreferredSize());
        JLabel nameHeader = new JLabel("Name");
        nameHeader.setForeground(Color.red);
        JLabel scoreHeader = new JLabel("Score");
        scoreHeader.setForeground(Color.red);
        nameHeader.setFont(headerFont);
        scoreHeader.setFont(headerFont);

        leaderboardHeader.add(nameHeader);
        leaderboardHeader.add(Box.createHorizontalGlue());
        leaderboardHeader.add(scoreHeader);
        leaderboardHeader.add(Box.createHorizontalGlue());

        leaderboardBody.add(leaderboardTable);



        connecting = new JLabel("Connecting...");
        leaderboardTable.add(connecting);






        this.add(leaderboardBody);
        leaderboardForm = new JPanel();
        leaderboardForm.setPreferredSize(new Dimension(game.getSize().width,game.getSize().height/8));

        leaderboardForm.setVisible(game.getState().getState() == State.DEATH);

        leaderboardForm.setLayout(new BoxLayout(leaderboardForm,BoxLayout.LINE_AXIS));

        leaderboardForm.add(Box.createHorizontalGlue());
        leaderboardForm.add(new JLabel("Name: "));
        name = new JTextField(3);
        name.setMaximumSize(name.getPreferredSize());
        name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (name.getText().length() >= 4 ) // limit to 4 characters
                    e.consume();
            }
        });

        leaderboardForm.add(name);

        submitScore = new JButton("Submit Score");
        submitScore.addActionListener(this);
        leaderboardForm.add(submitScore);
        leaderboardForm.add(Box.createHorizontalGlue());

        this.add(leaderboardForm);

        this.add(returnToMenu);
        returnToMenu.addActionListener(this);



        refresh();


    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this);
    }
    
    /**
     * Gets the row panel.
     *
     * @return the row panel
     */
    private JPanel getRowPanel(){
        JPanel row = new JPanel();
        row.setFont(scoreFont);

        row.setLayout(new BoxLayout(row,BoxLayout.LINE_AXIS));
        row.add(Box.createHorizontalGlue());

        return row;
    }

    /**
     * Refresh.
     */
    public void refresh(){
        leaderboardForm.setVisible(game.getState().getState() == State.DEATH);

        try {
            url = new URL(urlString);
            goodURL = true;
            leaderboard = getRequest(url);
            validLeaderboard = true;
            this.setup();
            leaderboardTable.remove(connecting);
        } catch (Exception e) {
            System.out.println("bad leaderboard url");
            goodURL = false;
            validLeaderboard = false;
            this.remove(connecting);
            JLabel unable = new JLabel("Unable to Connect to Leaderboard Server");
            leaderboardTable.removeAll();
            leaderboardTable.add(unable);

        }
    }
    
    /**
     * Record score.
     */
    private void recordScore(){
        String scoreRecordString = recordScoreURL + "name=" + name.getText() +"&score=" + game.getState().getTimeAlive();

        try {
            url = new URL(scoreRecordString);

            leaderboard = getRequest(url);
            validLeaderboard = true;
            this.setup();
            leaderboardTable.remove(connecting);
        } catch (Exception e) {
            System.out.println("bad record score url: " + scoreRecordString);

            validLeaderboard = false;
            this.remove(connecting);
            JLabel unable = new JLabel("Unable to Connect to Leaderboard Server");
            leaderboardTable.removeAll();
            leaderboardTable.add(unable);

        }
    }
    
    /**
     * Setup.
     */
    private void setup(){

        leaderboardTable.removeAll();
        leaderboardTable.add(leaderboardHeader);

        JPanel scores = new JPanel();
        scores.setLayout(new BoxLayout(scores,BoxLayout.Y_AXIS));

//        scores.setMinimumSize(scores.getPreferredSize());

        JSONArray scoresArray = leaderboard.getJSONArray("scores");

        for(int s = 0; s< scoresArray.length(); s++){
            scores.add(Box.createRigidArea(new Dimension(10,10)));

            JPanel row = getRowPanel();
            JSONObject score = scoresArray.getJSONObject(s);

            JLabel nameField = new JLabel(score.getString("name"));
            nameField.setFont(scoreFont);
            row.add(nameField);
            row.add(Box.createHorizontalGlue());
            JLabel scoreField = new JLabel(""+score.getInt("score"));
            scoreField.setFont(scoreFont);
            row.add(scoreField);
            row.add(Box.createHorizontalGlue());
            scores.add(row);

        }



        JScrollPane scoresScroll = new JScrollPane(scores);
        scoresScroll.setPreferredSize(new Dimension(game.getSize().width/2+20,game.getSize().height/2));

        //scoresScroll.setViewportView(scores);

        //scoresScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        scoresScroll.setBorder(null);

        leaderboardTable.add(scoresScroll);


    }
    
    /**
     * Read JSON.
     *
     * @param rd the rd
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static String readJSON(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    
    /**
     * Gets the request.
     *
     * @param url the url
     * @return the request
     * @throws Exception the exception
     */
    private JSONObject getRequest(URL url) throws Exception{
        if(!goodURL){
            return new JSONObject();
        }
        URLConnection urlConn = url.openConnection();
        BufferedReader rd;

        if(urlConn instanceof HttpURLConnection){
            HttpURLConnection conn = (HttpURLConnection) urlConn;
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        else{
            HttpsURLConnection conn = (HttpsURLConnection) urlConn;
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        String jsonText = readJSON(rd);
        JSONObject json = new JSONObject(jsonText);

        rd.close();

        return json;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenu) {

            game.exitGame();

        }
        else if(e.getSource() == submitScore){
            if(name.getText().length() != 0){
                leaderboardForm.setVisible(false);
                recordScore();

            }

        }
    }
}
