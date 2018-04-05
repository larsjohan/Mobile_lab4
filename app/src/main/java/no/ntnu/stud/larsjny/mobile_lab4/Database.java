package no.ntnu.stud.larsjny.mobile_lab4;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Database {

    private static final DatabaseReference DATABASE = FirebaseDatabase.getInstance().getReference();

    private static final DatabaseReference USERS = DATABASE.child("user");

    private static final DatabaseReference MESSAGES = DATABASE.child("message");



    public static final List<User> USERLIST = new ArrayList<>();

    public static final List<Message> MESSAGELIST = new ArrayList<>();


    private static ArrayAdapter userListAdapter = null;

    private static ArrayAdapter messageListAdapter = null;




    public static void registerUserListAdapter(ArrayAdapter adapter) {
        userListAdapter = adapter;
    }

    public static void registerMessageListAdapter(ArrayAdapter adapter) {
        messageListAdapter = adapter;
    }



    public static boolean hasUser(User user) {
        return USERLIST.contains(user);
    }

    public static boolean hasMessage(Message message){
        return MESSAGELIST.contains(message);
    }

    public static void addUser(User user) {
        USERS.push().setValue(user);
    }

    public static void addMessage(Message message) {
        MESSAGES.push().setValue(message);
    }

    public static void initListeners(){

        Log.d("Lab4", "Setting database change listeners");

        USERS.addValueEventListener(new OnUserDatabaseChangeListener());
        MESSAGES.addValueEventListener(new OnMessageDatabaseChangeListener());
    }


    public static class OnUserDatabaseChangeListener implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for (DataSnapshot data : dataSnapshot.getChildren()) {
                User currentUser = data.getValue(User.class);
                if (!hasUser(currentUser)) {
                    Log.d("Lab4", "Added user: " + currentUser.getUsername());
                    USERLIST.add(currentUser);

                    USERLIST.sort(Comparator.comparing(User::getUsername));

                    if (userListAdapter != null)
                        userListAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("Lab4", "Database change was cancelled: " + databaseError.toException());
        }
    }


    public static class OnMessageDatabaseChangeListener implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                Message currentMessage = data.getValue(Message.class);
                if (!hasMessage(currentMessage)) {
                    Log.d("Lab4", "Added message: " + currentMessage.getMessage());
                    MESSAGELIST.add(currentMessage);


                    MESSAGELIST.sort((message1, message2) -> {

                        long m1 = Long.parseLong(message1.getTimestamp());
                        long m2 = Long.parseLong(message2.getTimestamp());

                        return Long.compare(m1, m2);
                    });

                    if (messageListAdapter != null)
                        messageListAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("Lab4", "Database change was cancelled: " + databaseError.toException());
        }
    }

}
