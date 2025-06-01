package Controller;

import Main.Main;
import View.Chat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private final Chat chat;

    public KeyHandler(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (!chat.inputString.isEmpty()) {
                if (keyEvent.isControlDown()) {
                    chat.inputString = "";
                } else {
                    chat.inputString = chat.inputString.substring(0, chat.inputString.length() - 1);
                    chat.cursorPos--;
                }

            }
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Ignore
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.running = false;
            System.exit(0);
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            chat.cursorPos++;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            chat.cursorPos--;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_HOME) {
            chat.cursorPos = 0;
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_END) {
            chat.cursorPos = chat.inputString.length();
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            chat.createMessage();
        }
        else {

            chat.append(keyEvent.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
