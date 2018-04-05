package no.ntnu.stud.larsjny.mobile_lab4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class CreateUsername extends AppCompatActivity {

    private EditText editText;

    private boolean valid;

    private static final User newUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_username);

        this.valid = false;

        this.editText = findViewById(R.id.et_newUsername);
        this.editText.addTextChangedListener(new TextChangedListener());

        Button registerButton = findViewById(R.id.b_createUsername);
        registerButton.setOnClickListener(this::onClick);
    }


    private void onClick(View view){

        if(valid){

            newUser.setUsername(this.editText.getText().toString());

            Intent returnToApp = getIntent();
            returnToApp.putExtra("username", newUser.getUsername());
            setResult(Activity.RESULT_OK, returnToApp);

            Database.addUser(newUser);

            finish();
        } else {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }

    }



    private class TextChangedListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            newUser.setUsername(charSequence.toString());

            if(!Database.hasUser(newUser) && count > 5) {
                findViewById(R.id.et_newUsername).setBackgroundColor(getColor(R.color.validUsername));
                valid = true;
            } else {
                findViewById(R.id.et_newUsername).setBackgroundColor(getColor(R.color.invalidUsername));
                valid = false;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
