package no.ntnu.stud.larsjny.mobile_lab4.listeners;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import no.ntnu.stud.larsjny.mobile_lab4.Database;
import no.ntnu.stud.larsjny.mobile_lab4.MainActivity;
import no.ntnu.stud.larsjny.mobile_lab4.Message;

public class SendMessageListener implements View.OnClickListener {

    private EditText textField;

    public SendMessageListener(EditText textfield) {
        this.textField = textfield;
    }

    @Override
    public void onClick(View view) {

        Log.d("Lab4", "Sending message");

        String message = textField.getText().toString();

        if (!message.isEmpty()) {
            textField.setText("");
            Message newMessage = new Message(MainActivity.MYUSER.getUsername(), message);
            Database.addMessage(newMessage);
        }
    }
}
