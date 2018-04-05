package no.ntnu.stud.larsjny.mobile_lab4.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.stud.larsjny.mobile_lab4.R;
import no.ntnu.stud.larsjny.mobile_lab4.fragments.MessagesFragment;
import no.ntnu.stud.larsjny.mobile_lab4.fragments.UsersFragment;

/**
 * An adapter for creating and managing the different fragments that should be
 * listed as tabs in the MainActivity.
 *
 * @author Lars Johan
 * @see no.ntnu.stud.larsjny.mobile_lab4.MainActivity
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * A List of fragments that should be available
     */
    private final List<Fragment> fragments;

    private final Context context;

    /**
     * Constructor.
     * Creates the fragments.
     * @param fm
     */
    public TabViewPagerAdapter(FragmentManager fm, final Context context) {
        super(fm);
        this.context = context;
        this.fragments = new ArrayList<>();
        this.fragments.add(new MessagesFragment());
        this.fragments.add(new UsersFragment());
    }

    /**
     * Retrieve a fragment.
     * @param position The position of the fragment
     * @return The requested fragment or null if not found
     */
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    /**
     * Get the number of fragments available
     * @return number of fragments
     */
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    /**
     * Retrieve the title to set for the requested fragment
     * @param position The position of the fragment
     * @return The title of the fragment
     */
    @Override
    public CharSequence getPageTitle(int position) {
        Fragment fragment = this.fragments.get(position);
        Drawable icon;
        String title;

        if (fragment instanceof MessagesFragment) {
            icon = this.context.getDrawable(R.drawable.ic_chat_black_24dp);
            title = "MESSAGES";
        }else {
            icon = this.context.getDrawable(R.drawable.ic_people_black_24dp);
            title = "USERS";
        }

        SpannableStringBuilder titleString = new SpannableStringBuilder("    " + title);
        icon.setBounds(5, 5, 100, 100);
        ImageSpan image = new ImageSpan(icon, DynamicDrawableSpan.ALIGN_BASELINE);
        titleString.setSpan(image, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return titleString;
    }


}
