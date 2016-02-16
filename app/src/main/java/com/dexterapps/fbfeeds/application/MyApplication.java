package com.dexterapps.fbfeeds.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.dexterapps.fbfeeds.BuildConfig;
import com.dexterapps.fbfeeds.R;
import com.dexterapps.fbfeeds.rest_client.RestAPIManager;
import com.dexterapps.fbfeeds.rest_client.RestClient;
import com.dexterapps.fbfeeds.sharedpreferences.SessionManager;
import com.dexterapps.fbfeeds.utility.Constants;

import net.gotev.uploadservice.UploadService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by shopsense on 12-02-2016.
 */
public class MyApplication extends Application {

    private static Context context;
    //private static MyApplication appInstance;
    // Session Manager Class
    private static SessionManager session;
    // Retrofit client
    private static RestClient restClient;
    //API MANAGER
    private static RestAPIManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // setup the broadcast action namespace string which will
        // be used to notify upload status.
        // Gradle automatically generates proper variable as below.
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        getHash();
        //appInstance = this;
         /*
        *  using for fonts
        */

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/montserratregular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
        /*
         *  Shared prefences - (Maintains login,checkin, checkout states)Session class instance
         */

        session = new SessionManager(getAppContext());
        /*
         *  using for retrofit network call
         */
       // restClient = new RestClient();

        //apiManager = new RestAPIManager();
    }

    public static Context getAppContext() {
        return context;
    }



    /* Method return SessionManager to use Shared Preferences
    **/
    public static SessionManager getSessionManager() {
        if (null != session) {
            return session;
        } else {
            return new SessionManager(getAppContext());
        }
    }

    /**
     * Method return RestClient to use retrofit
     **/
    public static RestClient getRestClient() {
        return restClient;
    }

    /**
     * Method to ----------
     **/
    public static RestAPIManager getRestAPIManager() {
        if (null != apiManager) {
            return apiManager;
        } else {
            return new RestAPIManager();
        }
    }


    private void getHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    Constants.APP_PACKAGE_NAME,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash: ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
