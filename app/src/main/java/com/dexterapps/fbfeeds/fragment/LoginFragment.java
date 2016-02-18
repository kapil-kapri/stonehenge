package com.dexterapps.fbfeeds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexterapps.fbfeeds.R;
import com.dexterapps.fbfeeds.application.MyApplication;
import com.facebook.login.widget.LoginButton;


/**
 * Created by kapilkapri on 14/01/16.
 */
public class LoginFragment extends BaseFragment {
    public static String TAG = "LoginFragment";
    private LoginButton loginButton;

    public static LoginFragment newInstance(Bundle bundle) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(MyApplication.getMyFacebookManager().getCallbackManager(), MyApplication.getMyFacebookManager().getCallback());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult LoginFragment");
        MyApplication.getMyFacebookManager().getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*accessTokenTracker.stopTracking();
        profileTracker.stopTracking();*/
    }

    @Override
    public void onResume() {
        super.onResume();
       // Profile profile = Profile.getCurrentProfile();
       // displayMessage(profile);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }
}