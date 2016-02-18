package com.dexterapps.fbfeeds.helper;

import android.util.Log;

import com.dexterapps.fbfeeds.activity.HomeActivity;
import com.dexterapps.fbfeeds.application.MyApplication;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by shopsense on 16-02-2016.
 */
public class MyFacebookManager {
    private static CallbackManager callbackManager;
    private FacebookCallback<LoginResult> callback;
    private final String TAG = "MyFacebookManager";


    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public MyFacebookManager() {
        init();
        LoginManager.getInstance().registerCallback(callbackManager, callback);
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public FacebookCallback<LoginResult> getCallback() {
        return callback;
    }

    public void setCallback(FacebookCallback<LoginResult> callback) {
        this.callback = callback;
    }

    private void init() {
        FacebookSdk.sdkInitialize(MyApplication.getAppContext());
        callbackManager = CallbackManager.Factory.create();
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "SUCCESS");
                HomeActivity.myFbCallbacks.onLoginSuccess();

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "CANCEL");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d(TAG, "ERROR- "+e.getMessage());
                e.printStackTrace();
                HomeActivity.myFbCallbacks.onLoginError();
            }
        };

    }


}
