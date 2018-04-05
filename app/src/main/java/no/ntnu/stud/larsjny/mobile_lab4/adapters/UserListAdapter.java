package no.ntnu.stud.larsjny.mobile_lab4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import no.ntnu.stud.larsjny.mobile_lab4.R;
import no.ntnu.stud.larsjny.mobile_lab4.User;

public class UserListAdapter extends ArrayAdapter<User> {




    public UserListAdapter(@NonNull Context context, ArrayList<User> list) {
        super(context, 0, list);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_users, parent, false);
        }

        TextView username = convertView.findViewById(R.id.tv_userList_username);
        username.setText(user.getUsername());

        return convertView;
    }
}
