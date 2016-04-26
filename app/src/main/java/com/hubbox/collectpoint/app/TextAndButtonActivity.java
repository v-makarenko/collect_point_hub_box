package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextAndButtonActivity extends AppCompatActivity {
    public static final String STEP_NO = "STEP_NO";
    private static final String STEP_TEXT_ID_PREFIX = "text_step_";
    private static final String BTN_TEXT_ID_PREFIX = "text_btn_";
    private int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getIntent().getIntExtra(STEP_NO, 0);
        setContentView(R.layout.text_and_button);
        ((TextView) findViewById(R.id.text_step))
                .setText(Html.fromHtml(getStringResourceByName(STEP_TEXT_ID_PREFIX + step)));
        Button btn = (Button) findViewById(R.id.next_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = null;
                if (step < 4) {
                    next = new Intent(TextAndButtonActivity.this, TextAndButtonActivity.class);
                    next.putExtra(STEP_NO, step + 1);
                } else {
                    next = new Intent(TextAndButtonActivity.this, MainActivity.class);
                }
                startActivity(next);
            }
        });
        btn.setText(getStringResourceByName(BTN_TEXT_ID_PREFIX + step));
    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
}
