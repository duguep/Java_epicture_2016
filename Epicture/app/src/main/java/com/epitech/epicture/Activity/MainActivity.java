package com.epitech.epicture.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.epitech.epicture.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void choseLogin(View v) {
        CharSequence[] items = new CharSequence[2];
        items[0] = findStringById(R.string.login_imgur);
        items[1] = findStringById(R.string.login_flickr);
        new AlertDialog.Builder(this)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lv = ((AlertDialog)dialog).getListView();
                        lv.setTag(which);
                    }
                })
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ListView lv = ((AlertDialog)dialog).getListView();
                        Integer selectedPosition = (Integer)lv.getTag();
                        if (selectedPosition == 0) {
                            Log.i(TAG, "Starting Imgur Login");
                            startActivity(new Intent(MainActivity.this, ImgurLoginActivity.class));
                        }
                        else if (selectedPosition == 1) {
                            Log.i(TAG, "Starting Flickr Login");
                        }
                        else {
                            Log.i(TAG, "Selected index : " + String.valueOf(selectedPosition));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();

    }

    private String findStringById(int id) {
        return getResources().getString(id);
    }

}