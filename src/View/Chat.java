package View;

import Controller.Controller;
import Controller.WebHandler;
import Main.Main;
import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chat extends JPanel {

    private String text = "1Pedro9 >:  ";
    public String inputString = "";
    private boolean cursorShown = true;
    private int cursorFreq = 3;
    public int cursorPos = inputString.length();
    private Controller controller;
    public ArrayList<Message> messages;
    private WebHandler webHandler;

    public Chat() {
        System.out.println("Starting chat");
        init();

        controller = new Controller();
        messages = controller.messages;
        webHandler = new WebHandler();

    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // ✅ Vertical layout
        setBackground(Color.DARK_GRAY);
    }

    public void render() {
        removeAll();

        for (int i = Math.max(messages.size() - 50, 0); i < messages.size(); i++) {
            JLabel l = new JLabel(messages.get(i).line());
            if (messages.get(i).currentUser) {
                l.setForeground(new Color(200, 255, 255));
            } else {
                l.setForeground(Color.LIGHT_GRAY);
            }

            l.setBorder(BorderFactory.createEmptyBorder(10, 5, 2, 5)); // Adds vertical space
            add(l);
        }

        String line = text;
        if (cursorPos > (inputString.length())) cursorPos = inputString.length() - 1;
        if (cursorPos < 0) cursorPos = 0;

        if (cursorFreq == 2) { cursorFreq = 0; cursorShown = !cursorShown; }
        if (cursorShown) {
            line += inputString.substring(0, cursorPos);
            line += "|";
            line += inputString.substring(cursorPos, inputString.length());
        } else {
            line += inputString;
        }


        JLabel label = new JLabel(line, JLabel.LEFT);
        label.setForeground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(20, 5, 2, 5));
        add(label);
        // add(scroll);
        revalidate();
        repaint();
        cursorFreq++;


    }


    public void append(char s) {
        inputString = inputString.substring(0, cursorPos) + s + inputString.substring(cursorPos, inputString.length());
        cursorPos++;
    }

    public void createMessage() {
        System.out.println("Sending message");
        controller.addMessage(new Message(controller.messages.size(), controller.getContact(Main.me.getID()), inputString));
        webHandler.sendMessage(Main.me.getID(), 2, inputString);
        inputString = "";
        cursorPos = 0;
    }

}
