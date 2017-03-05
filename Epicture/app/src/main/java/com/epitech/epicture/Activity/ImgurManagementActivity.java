package com.epitech.epicture.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.epitech.epicture.R;
import com.epitech.epicture.RefreshAccessTokenTask;
import com.epitech.epicture.TokenManipulation.ImgurAuthorization;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ImgurManagementActivity extends AppCompatActivity {

    private static final String TAG = ImgurManagementActivity.class.getSimpleName();

    private static final String GALLERY_URL = "https://api.imgur.com/account/" + ImgurAuthorization.getmUsername() + "/images/0";

    private static final String IMAGE_URL = "https://api.imgur.com/3/image/R0um7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgur_management);
    }

    public void uploadImage(View v) {
        startActivity(new Intent(ImgurManagementActivity.this, ImgurUploadActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new RefreshAccessTokenTask(getApplicationContext()).execute();
        new ImgurRetrieveTask(getApplicationContext()).execute();
    }

    private class ImgurRetrieveTask extends AsyncTask {

        private Context mContext;

        private ImgurRetrieveTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(IMAGE_URL).openConnection();
                try {
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    ImgurAuthorization.getInstance().addToHttpURLConnection(getApplicationContext(), conn);

                    InputStreamReader input = new InputStreamReader(conn.getInputStream());
                    BufferedReader in = new BufferedReader(input);

                } finally {
                    conn.disconnect();
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        private String readStream(InputStream in) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            try {
                line = reader.readLine();
            } catch (Exception ex) {
                line = null;
            }
            while(line != null) {
                try {
                    line = reader.readLine();
                    result.append(line);
                } catch (Exception ex) {
                    line = null;
                }
            }
            return result.toString();
        }

    }

}
