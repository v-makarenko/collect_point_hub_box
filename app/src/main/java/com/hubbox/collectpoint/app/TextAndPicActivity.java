package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hubbox.collectpoint.app.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TextAndPicActivity extends SecureActivity {
    public static final String STEP_NO = "STEP_NO";
    public static final String PIC_PATH = "PIC_PATH";

    private static final String STEP_TEXT_ID_PREFIX = "text_and_cam_step_";
    private int step;

    private ImageView imageView;


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getIntent().getIntExtra(STEP_NO, 1);
        final String path = getIntent().getStringExtra(PIC_PATH);
        setContentView(R.layout.text_and_pic);
        ((TextView) findViewById(R.id.text_step))
                .setText(Html.fromHtml(ResourceUtils.getStringResourceByName(this, STEP_TEXT_ID_PREFIX + step)));
        imageView = ((ImageView) findViewById(R.id.photo_preview));
        imageView.post(new Runnable() {
            @Override
            public void run() {
                int w = imageView.getMeasuredWidth();
                int h = imageView.getMeasuredHeight();

                imageView.setImageBitmap(ResourceUtils.decodeSampledBitmapFromPath(path, w, h));
            }
        });


        // set listeners for btns
        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                if (step < 3) {
                    next = new Intent(TextAndPicActivity.this, TextAndCameraActivity.class);
                    next.putExtra(STEP_NO, step + 1);
                } else {
                    next = new Intent(TextAndPicActivity.this, TextAndButtonActivity.class);
                    next.putExtra(STEP_NO, 4);
                }
                startActivity(next);
            }
        });
        findViewById(R.id.del_shot_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next;
                next = new Intent(TextAndPicActivity.this, TextAndCameraActivity.class);
                next.putExtra(STEP_NO, step);
                startActivity(next);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
