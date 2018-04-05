package no.ntnu.stud.larsjny.mobile_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import no.ntnu.stud.larsjny.mobile_lab4.adapters.TabViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int CREATE_USN_REQUEST_CODE = 1;

    /**
     * Adapter for managing tabs
     */
    private TabViewPagerAdapter tabAdapter;

    private ViewPager pager;

    private FirebaseAuth auth;

    private FirebaseUser user;

    public static final User MYUSER = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.auth = FirebaseAuth.getInstance();
        this.user = this.auth.getCurrentUser();

        loginUser();

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


    /**
     * Displays the UI-elements
     */
    private void initUI(){
        this.tabAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), this);
        this.pager = findViewById(R.id.vp_pager);
        this.pager.setAdapter(this.tabAdapter);

        PagerTabStrip tabs = findViewById(R.id.pts_title_strip);
        tabs.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        tabs.setTextColor(getColor(R.color.colorTabText));
        tabs.setTabIndicatorColor(getColor(R.color.colorAccent));
        tabs.setDrawFullUnderline(true);
    }

    /**
     * Authenticate the user anonymously in firebase
     */
    private void loginUser(){

        Log.d("Lab4", "Logging in anonymously");

        this.auth.signInAnonymously().addOnCompleteListener(task -> {
           if(task.isSuccessful()) {
               Log.d("Lab4", "Login successful");
               Database.initListeners();

               String username = getUsername();

               // Check if user has created a nickname
               // If not created, let user choose one and store in Private preferences
               if(username.equals("Guest")){
                   Log.d("Lab4", "Username not set");
                   createUser();
               } else {
                   Log.d("Lab4", "Username Set to " + username);
                   MYUSER.setUsername(username);
                   initUI();
               }
           } else {
               Log.e("Lab4", "Unable to log in anonymously");
               Toast.makeText(this, "Uable to contact database", Toast.LENGTH_LONG).show();
           }
        });
    }


    /**
     * Fetches the username from Private preferences
     * @return the username or "Guest" if not set
     */
    public String getUsername(){
        return this.getPreferences(MODE_PRIVATE).getString("username", "Guest");
    }


    /**
     * Creates a new user
     */
    public void createUser(){
        Intent createUsername = new Intent(this, CreateUsername.class);
        startActivityForResult(createUsername, CREATE_USN_REQUEST_CODE);
    }


    /**
     * {@inheritDoc}
     *
     * Saves the username to preferences and loads GUI
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_USN_REQUEST_CODE && resultCode == RESULT_OK){
            String usn = data.getStringExtra("username");

            this.getPreferences(MODE_PRIVATE).edit().putString("username", usn).apply();
            Toast.makeText(this, usn, Toast.LENGTH_SHORT).show();
            initUI();
        } else {
            Toast.makeText(this, "A Username must be provided to use this application", Toast.LENGTH_LONG).show();
            createUser();
        }
    }
}
