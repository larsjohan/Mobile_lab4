package no.ntnu.stud.larsjny.mobile_lab4.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.stud.larsjny.mobile_lab4.R;
import no.ntnu.stud.larsjny.mobile_lab4.User;

/**
 *
 */
public class UsersFragment extends Fragment {

    private final List<User> userList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        // TODO: Set the adapter

        return view;
    }

}
