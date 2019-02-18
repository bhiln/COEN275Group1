package Game;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private Input input;
    public KeyInput(Input input, JFrame frame) {
        this.input = input;
        frame.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) input.setKeyDown("Space");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) input.setKeyDown("Left");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) input.setKeyDown("Right");
        else if (e.getKeyCode() == KeyEvent.VK_UP) input.setKeyDown("Up");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) input.setKeyDown("Down");
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) input.setKeyDown("Escape");
        else input.setKeyDown(KeyEvent.getKeyText(e.getKeyCode()));

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) input.setKeyUp("Space");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) input.setKeyUp("Left");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) input.setKeyUp("Right");
        else if (e.getKeyCode() == KeyEvent.VK_UP) input.setKeyUp("Up");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) input.setKeyUp("Down");
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) input.setKeyUp("Escape");
        else input.setKeyUp(KeyEvent.getKeyText(e.getKeyCode()));
    }
}
