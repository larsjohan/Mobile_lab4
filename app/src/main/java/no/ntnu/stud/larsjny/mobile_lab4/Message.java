package no.ntnu.stud.larsjny.mobile_lab4;

public class Message {

    private final String username;

    private final String message;


    public Message(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

}
