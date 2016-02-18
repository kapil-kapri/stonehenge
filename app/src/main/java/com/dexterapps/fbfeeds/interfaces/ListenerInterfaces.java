package com.dexterapps.fbfeeds.interfaces;

/**
 * Created by kapilkapri on 18/02/16.
 */
public class ListenerInterfaces {
    public interface MyFbCallbacks
    {
        public void onLoginSuccess();
        public void onLoginError();
        public void onLogoutSuccess();
        public void onLogoutError();
    }
}
