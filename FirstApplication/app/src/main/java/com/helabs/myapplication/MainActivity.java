package com.helabs.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    EditText editLogin;

    @ViewById(R.id.edit_password)
    EditText editPassword;

    @AfterViews
    public void init() {

    }

    @Click(R.id.button)
    public void doLogin(View view) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();

        if (login.equalsIgnoreCase("locaweb") && password.equals("1234")) {
            final Intent data = new Intent();
            data.putExtra("login", login);
            setResult(Activity.RESULT_OK, data);
            finish();
        } else {
            Toast.makeText(this, "Tente novamente!", Toast.LENGTH_SHORT).show();
            editPassword.getText().clear();
        }
    }
}