package com.example.videorecordexample;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.CameraProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {


    private Camera camera;
    private MediaRecorder mediaRecorder;
    private Button btn_record;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean recording = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();


        btn_record = (Button)findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    camera.lock();
                    recording = false;

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                            try {

                                mediaRecorder = new MediaRecorder();
                                camera.unlock();
                                mediaRecorder.setCamera(camera);
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                                mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));
                                mediaRecorder.setOrientationHint(90);
                                mediaRecorder.setOutputFile("/sdcard/test.mp4");
                                mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                                recording = true;


                            } catch (Exception e){
                                e.printStackTrace();
                                mediaRecorder.release();
                            }

                        }
                    });
                }
            }
        });





    }

    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "권한허가", Toast.LENGTH_SHORT).show();

            camera = Camera.open();
            camera.setDisplayOrientation(90);
            surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(MainActivity.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한거부", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        
    }

    private void refreshCamers(Camera camera) {
        if (surfaceHolder.getSurface() == null){
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception e){
            e.printStackTrace();
        }

        setCamera(camera);

    }

    private void setCamera(Camera cam) {

        camera = cam;
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamers(camera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
