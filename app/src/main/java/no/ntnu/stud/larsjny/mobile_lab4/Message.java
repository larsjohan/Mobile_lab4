package no.ntnu.stud.larsjny.mobile_lab4;

import java.time.LocalDateTime;

public class Message {

    private final LocalDateTime timestamp;

    private final String username;

    private final String message;


    public Message(String username, String message) {
        this.timestamp = LocalDateTime.now();
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public boolean equals(Object message) {
        if(message instanceof Message) {
            return this.timestamp.equals(((Message) message).timestamp) &&
                    this.username.equals(((Message) message).username);
        }

        return super.equals(message);
    }
}
