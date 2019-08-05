package com.dr.kode.tercodingsubmit2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView ivbg = findViewById(R.id.bgSplash);
        Glide.with(this).load(R.drawable.bg_spash).into(ivbg);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }, 3000);
    }
}
