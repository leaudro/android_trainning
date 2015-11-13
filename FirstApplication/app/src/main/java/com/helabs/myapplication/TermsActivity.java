package com.helabs.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.HtmlRes;

@EActivity(R.layout.activity_terms)
public class TermsActivity extends AppCompatActivity {

    public static final int REQUEST_LOGIN = 1234;

    @ViewById
    TextView textTerms;

    //Html.fromHtml(getString(R.string.terms));
    @HtmlRes
    CharSequence terms;

    @AfterViews
    public void init() {
        textTerms.setText(terms);
        textTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Click
    public void buttonCancel() {
        Toast.makeText(this, "Volte quando você mudar de ideia", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Click
    public void buttonAccept() {
        MainActivity_.intent(this).startForResult(REQUEST_LOGIN);
    }

    private void goToWelcomeActivity(String login) {
        WelcomeActivity_.intent(this).login(login).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            if (data != null) {
                goToWelcomeActivity(data.getStringExtra("login"));

                // finaliza a própria activity,
                // deixando apenas uma activity na pilha.
                finish();
            }
        }
    }
}