package no.ntnu.stud.larsjny.mobile_lab4;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import no.ntnu.stud.larsjny.mobile_lab4.adapters.TabViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    /**
     * Adapter for managing tabs
     */
    private TabViewPagerAdapter tabAdapter;

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup viewpager
        this.tabAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), this);
        this.pager = findViewById(R.id.vp_pager);
        this.pager.setAdapter(this.tabAdapter);

        PagerTabStrip tabs = findViewById(R.id.pts_title_strip);
        tabs.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        tabs.setTextColor(getColor(R.color.colorTabText));
        tabs.setTabIndicatorColor(getColor(R.color.colorAccent));
        tabs.setDrawFullUnderline(true);

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
