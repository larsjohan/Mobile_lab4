package no.ntnu.stud.larsjny.mobile_lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Check if user has created a nickname

        // TODO: If not created, let user choose one and store in Private preferences

        // TODO: Authenticate the user anonymously in firebase

        // TODO: Display two tabs:
        //      TODO: Messages
        //          TODO: Use list-view
        //          TODO: Display all messages
        //          TODO: Sort messages by time
        //      TODO: Users
        //          TODO: List all users
        //          TODO: Sort alphabetically
        //          TODO: When a user is clicked, all messages from that person should be displayed in chronological order

        // TODO: Register a service that checks for messages with a specified frequency when the app is in the background

        // TODO: When the service finds new messages, a notification should be sent.

        // TODO: Clicking on the notification should open the activity
    }
}
