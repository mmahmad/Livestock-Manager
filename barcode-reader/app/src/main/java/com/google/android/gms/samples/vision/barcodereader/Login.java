package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

    EditText editTextUsername;
    EditText editTextPassword;
    TextView textViewInvalidEntry;
    Button buttonLogin;


    // DO NOT IMPLEMENT FINISH(), OR ELSE FIND A NEW WAY TO IMPLEMENT THE LOGOUT FUNCTIONALITY!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewInvalidEntry = (TextView) findViewById(R.id.textViewInvalidEntry);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        textViewInvalidEntry.setVisibility(TextView.INVISIBLE);

    }

    // implement login button functionality

    public void Login (View view){

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if  (username.contentEquals("admin") && password.contentEquals("admin")){

            // go to main screen
//            textViewInvalidEntry.setText("Correct");
//            textViewInvalidEntry.setTextColor(Color.GREEN);
//            textViewInvalidEntry.setVisibility(TextView.VISIBLE);

            Intent goToNextActivity = new Intent(getApplicationContext(), SelectActivity.class);
            startActivity(goToNextActivity);

        } else {
            // make textViewInvalidEntry visible

            textViewInvalidEntry.setVisibility(TextView.VISIBLE);
            textViewInvalidEntry.setTextColor(Color.RED);
            textViewInvalidEntry.setText("Invalid username/password!");

        }

    }
}
