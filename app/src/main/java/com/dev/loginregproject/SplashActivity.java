package com.dev.loginregproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(this,5*1000);

    }

    @Override
    public void run() {
        String loginID = Util.getString(this,Util.LOGIN_ID,Util.NOT_LOGGED_IN);
        Intent intent;
        if (loginID.equals(Util.NOT_LOGGED_IN)){
            // not logged in
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else {
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
