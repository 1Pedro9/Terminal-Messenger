

package Controller;

import Main.Main;
import Model.Contact;
import Model.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public ArrayList<Contact> contacts;
    public ArrayList<Message> messages;

    public Controller() {
        contacts = getContacts();
        messages = getMessages();

    }

    private ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            File file = new File("contacts.txt");
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("")) {
                    continue;
                }
                else if (line.charAt(0) == '#') {
                    continue;
                }
                Scanner scLine = new Scanner(line).useDelimiter(":");
                int id = scLine.nextInt();
                String username = scLine.next();
                String email = scLine.next();
                String number = scLine.next();

                contacts.add(new Contact(id, username, email, number));
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return contacts;
    }

    private ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        try {
            File file = new File("messages.txt");
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("")) {
                    continue;
                }
                else if (line.charAt(0) == '#') {
                    continue;
                }
                Scanner scLine = new Scanner(line).useDelimiter(":");
                int id = scLine.nextInt();
                int contact = scLine.nextInt();
                String message = "";
                while (scLine.hasNext()) {
                    message += scLine.next();
                }
                Contact c = getContact(contact);
                Message msg = new Message(id, c, message);
                if (c.getID() == Main.me.getID()) {
                    msg.currentUser = true;
                }
                messages.add(msg);
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public Contact getContact(int id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getID() == id) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public void addMessage(Message message) {
        message.currentUser = true;
        messages.add(message);
        try {
            FileWriter fw = new FileWriter(new File("messages.txt"), true);
            fw.write(message.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
