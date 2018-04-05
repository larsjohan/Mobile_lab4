package no.ntnu.stud.larsjny.mobile_lab4;

public class Message {

    private final String timestamp;

    private final String username;

    private final String message;

    public Message () {
        timestamp = Long.toString(System.currentTimeMillis()/1000);
        username = "Guest";
        message = "";
    }

    public Message(String username, String message) {
        this.timestamp = Long.toString(System.currentTimeMillis()/1000);
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object message) {
        if(message instanceof Message) {
            return  this.timestamp.equals(((Message) message).timestamp) &&
                    this.username.equals(((Message) message).username) &&
                    this.message.equals(((Message) message).message);
        }

        return super.equals(message);
    }

}
