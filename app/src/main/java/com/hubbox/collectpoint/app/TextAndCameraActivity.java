package com.hubbox.collectpoint.app;

import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hubbox.collectpoint.app.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextAndCameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    public static final String STEP_NO = "STEP_NO";
    private static final String STEP_TEXT_ID_PREFIX = "text_and_cam_step_";
    private int step;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getIntent().getIntExtra(STEP_NO, 1);
        setContentView(R.layout.text_and_camera);
        ((TextView) findViewById(R.id.text_step))
                .setText(Html.fromHtml(ResourceUtils.getStringResourceByName(this, STEP_TEXT_ID_PREFIX + step)));

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    File file = ResourceUtils.constructPicName();
                    outStream = new FileOutputStream(file);
                    outStream.write(data);
                    outStream.close();
                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
                    Intent next = new Intent(TextAndCameraActivity.this, TextAndPicActivity.class);
                    next.putExtra(TextAndPicActivity.PIC_PATH, file.getAbsolutePath());
                    next.putExtra(TextAndPicActivity.STEP_NO, step);
                    startActivity(next);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TextAndCameraActivity.this, "Problems with camera.", Toast.LENGTH_LONG ).show();
                }
            }

        };


        findViewById(R.id.take_shot_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        camera.takePicture(null,null, jpegCallback);
                    }
                });
            }
        });
    }

    public void captureImage(View v) throws IOException {
        //take the picture
        camera.takePicture(null, null, jpegCallback);
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        refreshCamera();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // open the camera
            camera = Camera.open();
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();

        // modify parameter
//        param.setPreviewSize(surfaceView.getWidth()/4, surfaceView.getHeight()/4);
        Camera.Size size = param.getSupportedPictureSizes().get(1);
        param.setPreviewSize(size.width, size.height);

        size = param.getSupportedPictureSizes().get(param.getSupportedPictureSizes().size()-1);
        param.setPictureSize(size.width, size.height);
        param.setRotation(90);
        camera.setDisplayOrientation(90); // FIXME ONLY PORTRAIT MODE WORKING
        camera.setParameters(param);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
    }

    private void freeCamera(){
        if(camera!=null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
        }
        camera = null;
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // stop preview and release camera
        freeCamera();
    }

}
