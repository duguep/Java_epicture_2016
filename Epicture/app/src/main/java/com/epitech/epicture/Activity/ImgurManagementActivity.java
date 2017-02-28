package com.epitech.epicture.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.epitech.epicture.R;

public class ImgurManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgur_management);
    }

    public void uploadImage(View v) {
        startActivity(new Intent(ImgurManagementActivity.this, ImgurUploadActivity.class));
    }

}
