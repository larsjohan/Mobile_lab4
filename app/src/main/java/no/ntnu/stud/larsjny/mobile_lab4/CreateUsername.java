package no.ntnu.stud.larsjny.mobile_lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUsername extends AppCompatActivity {

    private EditText editText;

    private Button registerButton;

    private boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_username);

        this.valid = false;

        this.editText = findViewById(R.id.et_newUsername);
        this.registerButton = findViewById(R.id.b_createUsername);

        this.editText.addTextChangedListener(new TextChangedListener());
        this.registerButton.setOnClickListener(this::onClick);
    }


    private void onClick(View view){

        if(valid){
            Intent returnToApp = getIntent();
            returnToApp.putExtra("username", this.editText.getText().toString());
            finish();
        }

    }



    private class TextChangedListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            // TODO: See if username is taken
            // TODO: EXTRA: RED underline if username is taken, green if OK
            // TODO: Add user to database
            if(count > 5) valid = true;
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
