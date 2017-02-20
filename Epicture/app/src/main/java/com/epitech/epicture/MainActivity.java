package com.epitech.epicture;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void choseLogin(View v) {
        AlertDialog.Builder loginDialog = new AlertDialog.Builder(this);

        final String[] strings = new String[] {
                findStringbyId(R.string.login_imgur),
                findStringbyId(R.string.login_flickr)
        };

        loginDialog.setTitle(findStringbyId(R.string.login_dialog_title));
        loginDialog.setPositiveButton("Login", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedPosition = ((AlertDialog)dialog).getListView().getSelectedItemPosition();
            }
        });
        loginDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        loginDialog.setSingleChoiceItems(strings, 0, null);
        AlertDialog alert = loginDialog.create();
        alert.show();
    }

    private String findStringbyId(int id) {
        return getResources().getString(id);
    }

}
