package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HubBoxApplication)getApplication()).login();
                Intent next = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(next);
            }
        });
    }
}
