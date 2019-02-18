package Game;

import java.util.HashMap;
import java.util.Map;
import java.awt.Canvas;

public class Input {
    private Map<String, Boolean> keys = new HashMap<String, Boolean>();
    private Map<String, Boolean> keysDown = new HashMap<String, Boolean>();
    private Map<String, Boolean> keysUp = new HashMap<String, Boolean>();
    public Vector2 mouseMovement = new Vector2();
    private int mouseScrollRotation = 0;
    private int mouseX;
    private  int mouseY;
    private KeyInput keyInput;

    public Input(Canvas canvas){
        this.keyInput = new KeyInput(this,canvas);

    }

    public boolean getKey(String keyCode) {
        return keysDown.getOrDefault(keyCode, false);
    }

    public boolean getKeyDown(String keyCode) {
        return keysDown.getOrDefault(keyCode, false);
    }

    public boolean getKeyUp(String keyCode) {
        return keysUp.getOrDefault(keyCode, false);
    }

    public void setKeyDown(String keyCode) {
        keys.put(keyCode, true);
        keysDown.put(keyCode, true);
        keysUp.put(keyCode, false);
    }

    public void setKeyUp(String keyCode) {
        keys.put(keyCode, false);
        keysDown.put(keyCode, false);
        keysUp.put(keyCode, true);
    }

    public void resetKeysDown() {
        keysDown.clear();
        keysUp.clear();
    }

    public void setMouseButtonDown(int key) {
        int buttonNumber;
        if (key == 2) buttonNumber = 2;
        else if (key == 3) buttonNumber = 1;
        else buttonNumber = 0;

        keys.put("Mouse" + buttonNumber, true);
        keysDown.put("Mouse" + buttonNumber, true);
        keysUp.put("Mouse" + buttonNumber, false);
    }

    public void setMouseButtonUp(int key) {
        int buttonNumber;
        if (key == 2) buttonNumber = 2;
        else if (key == 3) buttonNumber = 1;
        else buttonNumber = 0;

        keys.put("Mouse" + buttonNumber, false);
        keysDown.put("Mouse" + buttonNumber, false);
        keysUp.put("Mouse" + buttonNumber, true);
    }

    public void resetMouseMovement() {
        mouseMovement = new Vector2();
    }

    public void addMouseMovement(Vector2 additionalMouseMovement) {
        mouseMovement.add(additionalMouseMovement);
    }


    public void setMouseX(int x) {
        mouseX = x;
    }

    public void setMouseY(int y) {
        mouseY = y;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
