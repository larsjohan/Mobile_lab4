package no.ntnu.stud.larsjny.mobile_lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationService extends Service {

    public static final String CHANNEL_ID = "ChatMessageNotification";

    private static final DatabaseReference DATABASE = FirebaseDatabase.getInstance().getReference().child("message");

    private final newMessageNotificationListener MESSAGELISTENER = new newMessageNotificationListener();

    private long numberOfMessages;


    private long diff;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        this.diff = 0;
        this.numberOfMessages = intent.getIntExtra("size", 0);

        Log.d("Lab4", "Started NotificationService (" + numberOfMessages + ")");
        startListening();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void startListening() {
        DATABASE.addValueEventListener(MESSAGELISTENER);
    }

    private void stopListening() {
        DATABASE.removeEventListener(MESSAGELISTENER);
    }

    public void sendNotification() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        Intent openAppOnNotificationClick = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, openAppOnNotificationClick, PendingIntent.FLAG_UPDATE_CURRENT);

        CharSequence name = "NEW_MESSAGE_NOTIFICATION_CHANNEL";
        String description = "A Channel for sending notifications about new messages that has appeared";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        notificationManager.createNotificationChannel(channel);



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message_black_24dp)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("You have " + diff + " new messages")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pi);

        notificationManager.notify(0, notificationBuilder.build());
    }


    private class newMessageNotificationListener implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            long numberOfElementsOnServer = dataSnapshot.getChildrenCount();

            Log.d("Lab4", "Fetched update from NotificationService: New messages: " + numberOfElementsOnServer + ". existing: " + numberOfMessages);


            if (numberOfElementsOnServer > numberOfMessages) {

                Log.d("Lab4", "New messages");

                diff = numberOfElementsOnServer - numberOfMessages;
                numberOfMessages = numberOfElementsOnServer;
                sendNotification();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("Lab4", "Could not look for updates from service: " + databaseError.toException());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopListening();
        Log.d("Lab4", "Destroyed NotificationService");
    }


}
