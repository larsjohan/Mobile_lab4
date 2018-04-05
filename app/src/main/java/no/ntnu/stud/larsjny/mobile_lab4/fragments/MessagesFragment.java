package no.ntnu.stud.larsjny.mobile_lab4.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import no.ntnu.stud.larsjny.mobile_lab4.R;
import no.ntnu.stud.larsjny.mobile_lab4.listeners.SendMessageListener;

/**
 * A fragment that represents the global chatroom.
 * Displays a list of all sent messages, and provides the ability to send new messages.
 *
 * @author Lars Johan
 * @see Fragment
 */
public class MessagesFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        ListView messageList = view.findViewById(R.id.lv_messages);
        EditText newMessage = view.findViewById(R.id.et_new_message);
        ImageButton sendButton = view.findViewById(R.id.b_send_new_message);
        sendButton.setImageResource(R.drawable.ic_send_black_24dp);
        view.findViewById(R.id.b_send_new_message).setOnClickListener(new SendMessageListener());

        // TODO: Set the list-adapter

        return view;
    }

}
