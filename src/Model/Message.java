package Model;

public class Message {

    private int id;
    private String message;
    private Contact contact;
    public boolean currentUser = false;

    public Message(int id, Contact contact, String message) {
        this.id = id;
        this.contact = contact;
        this.message = message;
    }

    public String toString() {
        String text = "" + id + ":" + contact.getID() + ":" + message;
        return text;
    }

    public String line() {
        return "" + contact.getUsername() + " >:  " + message;
    }

}
