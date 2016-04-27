package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hubbox.collectpoint.app.util.ResourceUtils;

public class TextAndButtonActivity extends AppCompatActivity {
    public static final String STEP_NO = "STEP_NO";
    private static final String STEP_TEXT_ID_PREFIX = "text_step_";
    private static final String BTN_TEXT_ID_PREFIX = "text_btn_";
    private int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getIntent().getIntExtra(STEP_NO, 1);
        setContentView(R.layout.text_and_button);
        ((TextView) findViewById(R.id.text_step))
                .setText(Html.fromHtml(ResourceUtils.getStringResourceByName(this, STEP_TEXT_ID_PREFIX + step)));
        Button btn = (Button) findViewById(R.id.next_btn);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent next = null;
                                       if (step < 3) { // except last two steps
                                           next = new Intent(TextAndButtonActivity.this, TextAndButtonActivity.class);
                                           next.putExtra(STEP_NO, step + 1);
                                       } else if (step == 3) {
                                           next = new Intent(TextAndButtonActivity.this, TextAndCameraActivity.class);
                                           next.putExtra(TextAndCameraActivity.STEP_NO, 1);
                                       } else {
                                           next = new Intent(TextAndButtonActivity.this, MainActivity.class);
                                       }

                                       startActivity(next);
                                   }
                               }

        );
        btn.setText(ResourceUtils.getStringResourceByName(this, BTN_TEXT_ID_PREFIX + step));
    }


}
