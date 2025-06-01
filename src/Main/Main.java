package Main;

import Controller.KeyHandler;
import Model.Contact;
import View.Chat;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame implements Runnable {

    private int WIDTH = 700, HEIGHT = 700;
    public static boolean running = false;
    private Thread thread;
    private JScrollPane scroll;

    public enum STATE {
        home, chat
    }

    public STATE state = STATE.chat;

    private Chat chat;
    private KeyHandler keyHandler;
    public static Contact me = new Contact(1, "1Pedro9", "", "");

    public Main() {
        init();
        keyEvents();
        setVisible(true);
    }

    private void init() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);

        setTitle("Terminal Messenger ~ Chari");

        chat = new Chat(); // ✅ Create Chat before JScrollPane
        scroll = new JScrollPane(chat); // ✅ Correct way to initialize
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);

        scroll.addMouseWheelListener(e -> {
            JScrollBar bar = scroll.getVerticalScrollBar();
            int rotation = e.getWheelRotation();
            int amount = 3 * bar.getUnitIncrement(); // Make it scroll 3x faster
            bar.setValue(bar.getValue() + rotation * amount);
        });

        add(scroll, BorderLayout.CENTER); // ✅ Add scroll pane (not chat)

        pack();     // Move this here
        start();    // Start render loop after everything is ready
    }


    private void keyEvents() {
        keyHandler = new KeyHandler(chat);
        this.addKeyListener(keyHandler);
    }

    public void run() {

        while(running) {
            if (chat != null && scroll != null) {
                render();
                update();
            }
            // Dealay 16ms = 60 fps
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Stopped running");
        stop();

    }

    private void render() {
        getContentPane().setBackground(Color.DARK_GRAY);

        switch (state) {
            case STATE.chat:
                chat.render(); // ✅ Only update chat content
                break;
            case STATE.home:
                // Implement if needed
                break;
        }

        revalidate();
        repaint();
    }


    private void update() {

    }

    public static void main(String[] args) {
        System.out.println("Starting Terminal Messenger");
        new Main();
    }

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            running = false;
            thread.join();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}