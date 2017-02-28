package com.epitech.epicture;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.epitech.epicture.TokenManipulation.ImgurAuthorization;

public class RefreshAccessTokenTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = RefreshAccessTokenTask.class.getSimpleName();

    private Context mContext;

    public RefreshAccessTokenTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        String accessToken = ImgurAuthorization.getInstance().requestNewAccessToken(mContext);
        if (!TextUtils.isEmpty(accessToken)) {
            Log.i(TAG, "Got new access token");
        }
        else {
            Log.i(TAG, "Could not get new access token");
        }
        return accessToken;
    }
}
