package Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyInput implements KeyListener {
    private Map<String, Boolean> keysDown = new HashMap<String, Boolean>();

    public KeyInput(JFrame frame){
        frame.addKeyListener(this);

    }
    public boolean getKey(String keyCode) {
        return keysDown.getOrDefault(keyCode, false);
    }

    public void setKeyDown(String keyCode) {
        keysDown.put(keyCode, true);
    }

    public void setKeyUp(String keyCode) {

        keysDown.put(keyCode, false);

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) this.setKeyDown("Space");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) this.setKeyDown("Left");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) this.setKeyDown("Right");
        else if (e.getKeyCode() == KeyEvent.VK_UP) this.setKeyDown("Up");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) this.setKeyDown("Down");
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) this.setKeyDown("Escape");
        else this.setKeyDown(KeyEvent.getKeyText(e.getKeyCode()));

    }

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
