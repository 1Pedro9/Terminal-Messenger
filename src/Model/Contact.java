package Model;

public class Contact {

    private int id;
    private String username;
    private String email;
    private String number;

    public Contact(int id, String username, String email, String number) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.number = number;
    }

    public String toString() {
        String text = "" + id + ":" + username + ":" + email + ":" + number;
        return text;
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return id;
    }

}
