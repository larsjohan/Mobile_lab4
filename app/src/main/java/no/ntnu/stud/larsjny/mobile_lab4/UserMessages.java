package no.ntnu.stud.larsjny.mobile_lab4;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

import no.ntnu.stud.larsjny.mobile_lab4.adapters.MessageListAdapter;

public class UserMessages extends AppCompatActivity {

    private String username;

    private ArrayList<Message> messages;

    private MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_messages);

        Intent callingIntent = getIntent();
        this.username = callingIntent.getStringExtra("username");

        ListView messageList = findViewById(R.id.lv_userMessages);

        this.messages = new ArrayList<>();

        adapter = new MessageListAdapter(this, messages);

        filterList();

        messageList.setAdapter(adapter);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportActionBar().setTitle(this.username);
    }


    private void filterList(){

        for (Message message : Database.MESSAGELIST){
            if(message.getUsername().equals(this.username)) {
                this.messages.add(message);
                this.messages.sort(Comparator.comparing(Message::getTimestamp));
                this.adapter.notifyDataSetChanged();
            }
        }

    }


}
