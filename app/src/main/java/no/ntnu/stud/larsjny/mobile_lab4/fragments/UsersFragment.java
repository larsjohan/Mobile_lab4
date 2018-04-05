package no.ntnu.stud.larsjny.mobile_lab4.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.stud.larsjny.mobile_lab4.Database;
import no.ntnu.stud.larsjny.mobile_lab4.R;
import no.ntnu.stud.larsjny.mobile_lab4.User;
import no.ntnu.stud.larsjny.mobile_lab4.UserMessages;
import no.ntnu.stud.larsjny.mobile_lab4.adapters.UserListAdapter;

/**
 *
 */
public class UsersFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        ListView userList = view.findViewById(R.id.lv_users);

        UserListAdapter adapter = new UserListAdapter(view.getContext(), (ArrayList<User>) Database.USERLIST);

        Database.registerUserListAdapter(adapter);

        userList.setAdapter(adapter);

        userList.setOnItemClickListener((parent, clickedView, position, id) -> {
            User user = (User) userList.getAdapter().getItem(position);

            Intent displayMessagesFromUser = new Intent(view.getContext(), UserMessages.class);
            displayMessagesFromUser.putExtra("username", user.getUsername());
            startActivity(displayMessagesFromUser);
        });

        return view;
    }

}
