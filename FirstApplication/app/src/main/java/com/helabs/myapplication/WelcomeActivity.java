package com.helabs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 12/11/15.
 */
@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {

    public static final String EXTRA_LOGIN = "EXTRA_LOGIN";

    @Extra(EXTRA_LOGIN)
    String login;

    @ViewById
    TextView textWelcomeMessage;

    @AfterViews
    public void init() {
        if (login != null) {
            final String text = getString(R.string.welcome_x, login);
            textWelcomeMessage.setText(text);
        } else {
            finish();
        }
    }
}