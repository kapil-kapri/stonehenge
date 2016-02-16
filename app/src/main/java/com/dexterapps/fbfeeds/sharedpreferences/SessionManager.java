package com.dexterapps.fbfeeds.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kapilkapri on 16/02/16.
 */
public class SessionManager {

    // Shared Preferences
    static SharedPreferences pref;

    // Editor for Shared preferences
    private static SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "FBFeeds_preferences";

    // All Shared Preferences Keys
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    // Password
    private static final String KEY_ACCESS_TOKEN = "access_token";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String access_token) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGGED_IN, true);
        // Storing access token in pref
        editor.putString(KEY_ACCESS_TOKEN, access_token);
        // commit changes
        editor.commit();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public static boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_IN, false);
    }

    /**
     * Clear session details
     **/
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        // editor.clear();
        editor.remove(IS_LOGGED_IN);
        editor.remove(KEY_ACCESS_TOKEN);
        editor.commit();

    }
}
