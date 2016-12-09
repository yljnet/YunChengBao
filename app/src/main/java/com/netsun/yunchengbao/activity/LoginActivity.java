package com.netsun.yunchengbao.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.netsun.yunchengbao.R;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView userText;
    private EditText passwordText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        userText = (AutoCompleteTextView) findViewById(R.id.mobile);
        passwordText = (EditText) findViewById(R.id.password);
    }
}