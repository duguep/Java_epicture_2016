package com.epitech.epicture.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.epitech.epicture.ChooseImageFragment;
import com.epitech.epicture.R;
import com.epitech.epicture.RefreshAccessTokenTask;

public class ImgurActivity extends AppCompatActivity {

    private static final String TAG = ImgurActivity.class.getSimpleName();

    private static final int REQ_CODE_PICK_IMAGE = 577;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgur);
        Log.i(TAG, "OnCreate");
    }

    public void pickImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQ_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case REQ_CODE_PICK_IMAGE:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    getChooseImageFragment().setImage(selectedImage);
                }
        }
    }

    private ChooseImageFragment getChooseImageFragment() {
        return (ChooseImageFragment) getSupportFragmentManager().findFragmentById(R.id.choose_image_fragment);
    }

    public void copyLink(View view) {
        getChooseImageFragment().copyLink(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        new RefreshAccessTokenTask(context).execute();
    }

}
