package com.app.plugxrvuforia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import com.app.viatnam.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;


public class SplashScreenActivity extends AppCompatActivity {

    private int bgtype = 0;
    private int logotype = 0;
    private FrameLayout bgImage;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash_screen);

        findViews();


        // Add image background image or color
        if (bgtype == 0){
            bgImage.setBackgroundResource(R.drawable.bg);
        }else {
            bgImage.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

        // Add or remove logo here
        if (logotype == 0){
            logo.setBackgroundResource(R.drawable.logo);
        }else {
            logo.setVisibility(View.GONE);
        }




        if (isConnected()) {
            android.os.Handler handler=new android.os.Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Dexter.withActivity(SplashScreenActivity.this)
                            .withPermissions(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                            ).withListener(new MultiplePermissionsListener() {
                        @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()){
                                Intent intent = new Intent(getApplicationContext(), MainUnityActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                        @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                    }).check();


                }
            }, 3000);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void findViews() {
        bgImage = (FrameLayout)findViewById(R.id.bg);
        logo = (ImageView)findViewById(R.id.logo);
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

}
