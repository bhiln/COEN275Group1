package Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class KeyInput.
 */
public class KeyInput implements KeyListener {
    
    /** The keys down. */
    private Map<String, Boolean> keysDown = new HashMap<String, Boolean>();

    /**
     * Instantiates a new key input.
     *
     * @param frame the frame
     */
    public KeyInput(JFrame frame){
        frame.addKeyListener(this);

    }
    
    /**
     * Gets the key.
     *
     * @param keyCode the key code
     * @return the key
     */
    public boolean getKey(String keyCode) {
        return keysDown.getOrDefault(keyCode, false);
    }

    /**
     * Sets the key down.
     *
     * @param keyCode the new key down
     */
    public void setKeyDown(String keyCode) {
        keysDown.put(keyCode, true);
    }

    /**
     * Sets the key up.
     *
     * @param keyCode the new key up
     */
    public void setKeyUp(String keyCode) {

        keysDown.put(keyCode, false);

    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent e) {

    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) this.setKeyDown("Space");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) this.setKeyDown("Left");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) this.setKeyDown("Right");
        else if (e.getKeyCode() == KeyEvent.VK_UP) this.setKeyDown("Up");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) this.setKeyDown("Down");
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) this.setKeyDown("Escape");
        else this.setKeyDown(KeyEvent.getKeyText(e.getKeyCode()));

    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) this.setKeyUp("Space");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) this.setKeyUp("Left");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) this.setKeyUp("Right");
        else if (e.getKeyCode() == KeyEvent.VK_UP) this.setKeyUp("Up");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) this.setKeyUp("Down");
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) this.setKeyUp("Escape");
        else this.setKeyUp(KeyEvent.getKeyText(e.getKeyCode()));
    }
}
