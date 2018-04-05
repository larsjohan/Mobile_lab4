package no.ntnu.stud.larsjny.mobile_lab4;

import android.content.Context;
import android.view.View;

import java.util.List;

public class User {

    private String username;


    public User(){
        this.username = "Guest";
    }

    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object user) {
        if(user instanceof User) {
            return this.username.equals(((User) user).getUsername());
        }

        return super.equals(user);
    }

}
