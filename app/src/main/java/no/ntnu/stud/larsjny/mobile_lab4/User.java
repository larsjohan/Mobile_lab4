package no.ntnu.stud.larsjny.mobile_lab4;

import java.util.List;

public class User {

    private String username;

    private List<Message> messages;

    public User(String username, List<Message> messages){
        this.username = username;
        this.messages = messages;
    }
}
