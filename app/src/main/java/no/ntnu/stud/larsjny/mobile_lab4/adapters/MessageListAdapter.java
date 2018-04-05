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

import no.ntnu.stud.larsjny.mobile_lab4.Message;
import no.ntnu.stud.larsjny.mobile_lab4.R;

public class MessageListAdapter extends ArrayAdapter<Message> {


    public MessageListAdapter(@NonNull Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message message = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_messages, parent, false);
        }

        TextView timestampView = convertView.findViewById(R.id.tv_messageList_timestamp);
        TextView userView = convertView.findViewById(R.id.tv_messageList_user);
        TextView messageView = convertView.findViewById(R.id.tv_messageList_message);

        timestampView.setText(message.getTimestamp());
        userView.setText(message.getUsername());
        messageView.setText(message.getMessage());

        return convertView;
    }

}
